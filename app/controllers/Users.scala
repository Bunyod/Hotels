package controllers

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
    rs.request.body.validate[User].map { user =>
      val userId = (users returning users.map(_.id)) += user
      val r = Map("id" -> userId)
      Ok(toJson(r))
    }.recoverTotal { errors =>
      BadRequest(errors.toString)
    }
  }

}