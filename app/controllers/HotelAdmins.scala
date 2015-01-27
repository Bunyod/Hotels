package controllers

import models.JsonFormats._
import models._
import play.api.Play.current
import play.api.db.slick.Config.driver.simple._
import play.api.db.slick._
import play.api.libs.json.Json._
import play.api.mvc._

import scala.slick.lifted.TableQuery

/**
 * Created by bunyod on 1/19/15.
 */

class HotelAdmins extends Controller {

  val admins = TableQuery[HotelAdminsTable]

  def index() = DBAction { implicit rs =>

    Ok(toJson(admins.list))
  }

  def saveAdmin = DBAction(parse.json) { implicit rs =>
    rs.request.body.validate[HotelAdmin].map { hotelAdmin =>
      val adminId = (admins returning admins.map(_.id)) += hotelAdmin
      val r = Map("id" -> adminId)
      Ok(toJson(r))
    }.recoverTotal { errors =>
      BadRequest(errors.toString)
    }
  }

}