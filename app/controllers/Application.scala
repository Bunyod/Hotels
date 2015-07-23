package controllers

import com.typesafe.scalalogging.slf4j.LazyLogging
import jp.t2v.lab.play2.auth.{OptionalAuthElement, LoginLogout}
import models.UserRoleEnum.UserRole
import models._

import play.api.mvc._
import play.api.libs.json._
import play.api.Play.current
import play.api.db.slick.Config.driver.simple._
import play.api.db.slick.DB
import play.api.libs.concurrent.Execution.Implicits.defaultContext

import models.JsonFormats._
import views.html

import scala.concurrent.Future
import scala.slick.lifted.TableQuery

/**
 * Created by bunyod on 1/19/15.
 */

object Application extends Controller with LoginLogout with OptionalAuthElement with HotelAuthConfig with LazyLogging {
  val users = TableQuery[UsersTable]

  def _getUserPermission(userRole: UserRole) = {
    val isAdministrator = userRole == UserRoleEnum.ADMINISTATOR
    val isAdministratorOrAdmin = isAdministrator || userRole == UserRoleEnum.ADMIN
    val permission = Permission(
      Hotel = PermHotel(isAdministratorOrAdmin, isAdministratorOrAdmin),
      User = PermUser(isAdministrator, isAdministrator)
    )
    Json.toJson(permission)
  }

  def index() = StackAction { implicit request =>
    loggedIn match {
      case Some(user) =>
        Ok(html.index("main"))
      case _ =>
        Ok(html.index("login"))
    }
  }

  def signIn = Action.async(parse.json) { implicit request =>
    DB.withSession { implicit session =>
      logger.info(s"authenticate $request")

      request.body.validate[Credential].map { credential =>
        val found = users.filter { r =>
          r.email === credential.login && r.password === credential.password
        }

        found.list.headOption match {
          case Some(user) =>
            gotoLoginSucceeded(user.id.get)
          case _ =>
            Future.successful(BadRequest("Incorrect login or password"))
        }
      }.recoverTotal { errors =>
        Future.successful(BadRequest(errors.toString))
      }
    }
  }

  def signOut() = Action.async { implicit request =>
    gotoLogoutSucceeded.map(_.flashing(
      "success" -> "You've been logged out"
    ))
  }

}