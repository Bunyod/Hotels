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
  val prices = TableQuery[PriceIntervalsTable]

  def allHotels = DBAction { implicit rs =>
    Ok(toJson(hotels.list))
  }

  def getCities = DBAction { implicit rs =>
    Ok(toJson(cities.list))
  }

  def findHotelsByParams(cityId: Int) = DBAction(parse.json) { implicit rs =>
    rs.request.body.validate[SearchParams].map { searchParams =>
      Logger.info(s"SearchParams:= $searchParams")

//      val searchJoin = hotels.leftJoin(cities).leftJoin(hotelTypes).leftJoin(prices).on {
//        case (((hotel, city), hotelType), price) =>
//          hotel.cityId === city.id &&
//          hotel.hotelTypeId === hotelType.id &&
//          hotel.priceId === price.id
//      }

      val searchResult = (for {
//        (((hotel, city), hotelType), price) <- searchJoin
        (((hotel, city), hotelType), price) <- hotels leftJoin cities on (_.cityId === _.id) leftJoin hotelTypes on (_._1.hotelTypeId === _.id) leftJoin prices on (_._1._1.priceId === _.id)
//       in above shown comment in expression is equal to searchJoin
           if (hotel.cityId === searchParams.cityId &&
               hotel.hotelTypeId === searchParams.hotelTypeId &&
               hotel.star === searchParams.starRating)
      } yield (hotel, city.name, hotelType.name, price.bottom, price.top)).list
        .map { case (hotels, cityName, hotelTypeName, bottom, top) =>
                 SearchResult(hotels, cityName, hotelTypeName, bottom + " - " + top)
             }

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
