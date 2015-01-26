package controllers

import play.api.db.slick.Config.driver.simple._
import play.api.db.slick._
import play.api.Play.current
import models._
import play.api.libs.json.Json._
import play.api.mvc._
import models.JsonFormats._
import scala.slick.lifted.TableQuery

/**
 * Created by bunyod on 1/19/15.
 */

class Hotels extends Controller {

  val hotels = TableQuery[HotelsTable]
  val reviews = TableQuery[ReviewsTable]
  val rooms = TableQuery[RoomsTable]
  val roomTypes = TableQuery[RoomTypesTable]
  val hotelTypes = TableQuery[HotelTypesTable]


  def allHotels = DBAction { implicit rs =>
    Ok(toJson(hotels.list))
  }

  def addHotel = DBAction(parse.json) { implicit rs =>
    rs.request.body.validate[Hotel].map { hotel =>
      val hotelId = (hotels returning hotels.map(_.id)) += hotel
      val r = Map("id" -> hotelId)
      Ok(toJson(r))
    }.recoverTotal { errors =>
      BadRequest(errors.toString)
    }
  }

  def hotelReviews(id: Int) = DBAction { implicit rs =>
    Ok(toJson(reviews.filter(_.hotelId === id).list))
  }

  def addReview(id: Int) = DBAction(parse.json) { implicit rs =>
    rs.request.body.validate[Review].map { review =>
      val reviewId = (reviews returning reviews.map(_.id)) += review
      val r = Map("id" -> reviewId)
      Ok(toJson(r))
    }.recoverTotal { errors =>
      BadRequest(errors.toString)
    }
  }

  def hotelRooms(id: Int) = DBAction { implicit rs =>
    Ok(toJson(rooms.filter(_.hotelId === id).list))
  }

  def addRoom(id: Int) = DBAction(parse.json) { implicit rs =>
    rs.request.body.validate[Room].map { room =>
      val roomId = (rooms returning rooms.map(_.id)) += room
      val r = Map("id" -> roomId)
      Ok(toJson(r))
    }.recoverTotal { errors =>
      BadRequest(errors.toString)
    }
  }

  def roomTypeList = DBAction { implicit rs =>
    Ok(toJson(roomTypes.list))
  }

  def addRoomType = DBAction(parse.json) { implicit rs =>
    rs.request.body.validate[Room].map { room =>
      val roomId = (rooms returning rooms.map(_.id)) += room
      val r = Map("id" -> roomId)
      Ok(toJson(r))
    }.recoverTotal { errors =>
      BadRequest(errors.toString)
    }
  }

  def hotelTypesList = DBAction { implicit rs =>
      Ok(toJson(hotelTypes.list))
  }

  def addHotelType = DBAction(parse.json) { implicit rs =>
    rs.request.body.validate[HotelType].map { hotelType =>
      val hotelTypeId = (hotelTypes returning hotelTypes.map(_.id)) += hotelType
      val r = Map("id" -> hotelTypeId)
      Ok(toJson(r))
    }.recoverTotal { errors =>
      BadRequest(errors.toString)
    }
  }

}
