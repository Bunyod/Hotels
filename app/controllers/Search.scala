package controllers

import models.JsonFormats._
import models._
import play.api.Logger
import play.api.Play.current
import play.api.db.slick.Config.driver.simple._
import play.api.db.slick._
import play.api.libs.json.Json
import play.api.libs.json.Json._
import play.api.mvc._

import scala.slick.lifted.TableQuery


/**
 * Created by bunyod on 1/19/15.
 */

class Search extends Controller with HotelAuth {

  val hotels = TableQuery[HotelsTable]
  val reviews = TableQuery[ReviewsTable]
  val rooms = TableQuery[RoomsTable]
  val roomTypes = TableQuery[RoomTypesTable]
  val hotelTypes = TableQuery[HotelTypesTable]
  val cities = TableQuery[CitiesTable]


  def allHotels = DBAction { implicit rs =>
    Ok(toJson(hotels.list))
  }

  def findHotelsByParams = DBAction(parse.json) { implicit rs =>
    rs.request.body.validate[SearchParams].map { searchParams =>
      val searchResult = (for {
        (h, c) <- hotels leftJoin cities on (_.cityId === _.id) if h.cityId == searchParams.location
      }yield (h, c.name)).list
        .map { case (hotels, cityName) => SearchResult(hotels, cityName)}

      Ok(Json.obj(
        "rows" -> searchResult
      ))
    }.recoverTotal { errors =>
      BadRequest(errors.toString)
    }
  }

  def hotelReviews(id: Int) = DBAction { implicit rs =>
    Ok(toJson(reviews.filter(_.hotelId === id).list))
  }

  def hotelRooms(id: Int) = DBAction(parse.json) { implicit res =>
    Ok(toJson(rooms.filter(_.hotelId === id).list))
  }

  def roomTypeList = DBAction { implicit rs =>
    Ok(toJson(roomTypes.list))
  }

  def hotelTypesList = DBAction { implicit rs =>
      Ok(toJson(hotelTypes.list))
  }

}
