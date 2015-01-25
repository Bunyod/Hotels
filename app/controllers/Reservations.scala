package controllers

import models.JsonFormats._
import models._
import play.api.Play.current
import play.api.db.slick.Config.driver.simple._
import play.api.db.slick._
import play.api.libs.json.Json._
import play.api.mvc._

import scala.slick.lifted.TableQuery


class Reservations extends Controller {

  val hotels = TableQuery[HotelsTable]
  val reviews = TableQuery[ReviewsTable]
  val rooms = TableQuery[RoomsTable]
  val roomTypes = TableQuery[RoomTypesTable]
  val reservations= TableQuery[ReservationsTable]


  def reservationsList(userId: Int) = DBAction { implicit rs =>
    Ok(toJson(reservations.filter(_.userId === userId).list))
  }

  def addReservation = DBAction(parse.json) { implicit rs =>
    rs.request.body.validate[Reservation].map { reservation =>
      val reservationId = (reservations returning reservations.map(_.id)) += reservation
      val r = Map("id" -> reservationId)
      Ok(toJson(r))
    }.recoverTotal { errors =>
      BadRequest(errors.toString)
    }
  }

}
