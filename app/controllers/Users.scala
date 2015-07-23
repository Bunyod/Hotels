package controllers

import com.typesafe.scalalogging.slf4j.LazyLogging
import play.api.Logger
import play.api.mvc._
import play.api.db.slick._
import play.api.Play.current
import play.api.db.slick.Config.driver.simple._
import play.api.libs.json.Json._
import services.MessageManager.SendEmail
import scala.slick.lifted.TableQuery
import play.api._
import akka.pattern.ask
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import play.api.libs.json.Json._


/**
 * Created by bunyod on 1/19/15.
 */

class Users extends Controller with HotelAuth with LazyLogging {


  import models._
  import models.JsonFormats._
  import services.AppContext._

  val users = TableQuery[UsersTable]

  def findUsers = DBAction { implicit rs =>
    Logger.info(s"Hiiiii im here =${users.list}")
    Ok(toJson(users.list))
  }

  def authors = DBAction { implicit rs =>
    Logger.info(s"Hiiiii im here")
    Ok(toJson(users.list))
  }

  def addUser = DBAction(parse.json) { implicit rs =>
    rs.request.body.validate[Account].map { user =>
      val userId = (users returning users.map(_.id)) += user
      val addedUser = users.findBy(_.id).applied(userId.toInt).first
      Ok(toJson(addedUser))
    }.recoverTotal { errors =>
      BadRequest(errors.toString)
    }
  }

  def deleteUser(id: Int) = AuthAction(AuthorityKey -> hasRole(UserRoleEnum.ADMIN)) { implicit rs =>
    DBAction { implicit req =>
      findById(id) match {
        case Some(entity) => {
          users.filter(_.id === id).delete;
          Ok("User deleted")
        }
        case None => Ok("")
      }
    }
  }

  def findById(id: Int)(implicit session: play.api.db.slick.Config.driver.simple.Session): Option[Account] = {
    val byId = users.findBy(_.id)
    byId(id).list.headOption
  }

  def updateUser(id: Int) = DBAction(parse.json) { implicit rs =>
    rs.request.body.validate[Account].map { user =>
      users.filter(_.id === id).update(user)
      Ok("User successfully updated")
    }.recoverTotal { errors =>
      BadRequest(errors.toString)
    }
  }

  def sendPlainMailWithDefaultConfig(address: String, text: String, emailSubject: String) = DBAction { implicit rs =>
    logger.debug("entry")
    messageManager ! SendEmail(address, text, emailSubject)
    Ok("Email successfully was sent")
  }

}