package controllers

import play.api.Logger
import play.api.mvc._
import play.api.db.slick._
import play.api.Play.current
import play.api.db.slick.Config.driver.simple._
import play.api.libs.json.Json._
import scala.slick.lifted.TableQuery

/**
 * Created by bunyod on 1/19/15.
 */

class Users extends Controller {


  import models._
  import models.JsonFormats._

  val users = TableQuery[UsersTable]

  def index() = DBAction { implicit rs =>

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

  def deleteUser(id: Int) = DBAction { implicit rs =>
    findById(id) match {
      case Some(entity) => {
        users.filter(_.id === id).delete;
        Ok("")
      }
      case None => Ok("")
    }
  }

  def findById(id: Int)(implicit session: play.api.db.slick.Config.driver.simple.Session): Option[Account] = {
    val byId = users.findBy(_.id)
    byId(id).list.headOption
  }

  def signIn = DBAction(parse.json) { implicit rs =>
    Logger.info(s"signIn")
    rs.request.body.validate[Credential].map { credential =>
      val found = users.filter { r => r.email === credential.login && r.password === credential.password}
      Logger.info(s"FOUND_USER = $found")
      found.list.headOption match {
        case Some(user) =>
          Logger.info(s"Hiiiiiiiiii welcomeeee  = ${user.role}")

          Ok("").withSession(
            "loggedIn" -> "true"
          )
        case _ =>
          BadRequest("Incorrect login or password")
      }
    }.recoverTotal { errors =>
      BadRequest(errors.toString)
    }
  }

  def signOut() = {
    Logger.info("SingOut")
    Redirect(routes.Hotels.allHotels()).withSession(
      request.session - "loggedIn"
    )
  }

  def updateUser(id: Int) = DBAction(parse.json) { implicit rs =>
    rs.request.body.validate[Account].map { user =>
      users.filter(_.id === id).update(user)
      Ok("User successfully updated")
    }.recoverTotal { errors =>
      BadRequest(errors.toString)
    }
  }

}