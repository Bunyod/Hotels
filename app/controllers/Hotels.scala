package controllers

import play.api.Logger
import play.api.db.slick.Config.driver.simple._
import play.api.db.slick._
import play.api.Play.current
import models._
import play.api.libs.json.Json
import play.api.libs.json.Json._
import play.api.mvc._
import models.JsonFormats._
import scala.slick.lifted.TableQuery
import com.typesafe.scalalogging.slf4j.LazyLogging
import java.io.File
import org.apache.commons.io.FileUtils


/**
 * Created by bunyod on 1/19/15.
 */

class Hotels extends Controller with HotelAuth {

  val hotels = TableQuery[HotelsTable]
  val reviews = TableQuery[ReviewsTable]
  val rooms = TableQuery[RoomsTable]
  val roomTypes = TableQuery[RoomTypesTable]
  val hotelTypes = TableQuery[HotelTypesTable]
  val cities = TableQuery[CitiesTable]
  val prices = TableQuery[PriceIntervalsTable]

  def uploadFile = Action(parse.multipartFormData) { request =>
    Logger.info(s"FileUploading = ${request.body.files}")
    val images = request.body.files.foreach {
      img =>
        img.ref.moveTo(new File("/tmp/" + img.filename))
        FileUtils.moveFile(new File("/tmp/" + img.filename), new File("/home/comp17/webapp", img.filename));
    }
    Ok("File has been uploaded")
  }

  def allHotels = DBAction { implicit rs =>

    val searchResult = (for {
      (((hotel, city), hotelType), price) <- hotels leftJoin cities on (_.cityId === _.id) leftJoin hotelTypes on (_._1.hotelTypeId === _.id) leftJoin prices on (_._1._1.priceId === _.id)
    } yield (hotel, city.name, hotelType.name, price.name, price.bottom, price.top)).list
      .map {
      case (hotel, cityName, hotelTypeName, priceName, bottom, top) =>
        HotelsDisplay(hotel, cityName, hotelTypeName, priceName.toString, bottom + " - " + top)
    }

    Logger.info(s"HOTELS = $searchResult")

    Ok(toJson(searchResult))
  }

  def premiumHotels = DBAction { implicit rs =>

    val searchResult = (for {
      (((hotel, city), hotelType), price) <- hotels leftJoin cities on (_.cityId === _.id) leftJoin hotelTypes on (_._1.hotelTypeId === _.id) leftJoin prices on (_._1._1.priceId === _.id)
      if(hotel.premium)
    } yield (hotel, city.name, hotelType.name, price.name, price.bottom, price.top)).list
      .map {
      case (hotel, cityName, hotelTypeName, priceName, bottom, top) =>
        HotelsDisplay(hotel, cityName, hotelTypeName, priceName.toString, bottom + " - " + top)
    }

    Logger.info(s"HOTELS = $searchResult")
    Ok(toJson(searchResult))
  }

  def addHotel = AuthJsAction(AuthorityKey -> hasRole(UserRoleEnum.ADMIN)) { implicit rs =>
    DBAction(parse.json) { implicit req =>
      req.request.body.validate[Hotel].map { hotel =>
        Logger.info(s"CreateHotel Hotel:$hotel")
        val hotelId = (hotels returning hotels.map(_.id)) += hotel
        val r = Map("id" -> hotelId)
        Ok(toJson(r))
      }.recoverTotal { errors =>
        BadRequest(errors.toString)
      }
    }
  }

  def hotelReviews(id: Int) = DBAction { implicit rs =>
    Ok(toJson(reviews.filter(_.hotelId === id).list))
  }

  def addReview(id: Int) = AuthJsAction(AuthorityKey -> hasRole(UserRoleEnum.USER)) { implicit rs =>
    DBAction(parse.json) { implicit req =>
      req.request.body.validate[Review].map { review =>
        val reviewId = (reviews returning reviews.map(_.id)) += review
        val r = Map("id" -> reviewId)
        Ok(toJson(r))
      }.recoverTotal { errors =>
        BadRequest(errors.toString)
      }
    }
  }

  def hotelRooms(id: Int) = DBAction(parse.json) { implicit res =>
    Ok(toJson(rooms.filter(_.hotelId === id).list))
  }

  def addRoom(id: Int) = AuthJsAction(AuthorityKey -> hasRole(UserRoleEnum.ADMIN)) { implicit rs =>
    DBAction(parse.json) { implicit req =>
      req.request.body.validate[Room].map { room =>
        val roomId = (rooms returning rooms.map(_.id)) += room
        val r = Map("id" -> roomId)
        Ok(toJson(r))
      }.recoverTotal { errors =>
        BadRequest(errors.toString)
      }
    }
  }

  def roomTypeList = DBAction { implicit rs =>
    Ok(toJson(roomTypes.list))
  }

  def priceIntervals = DBAction { implicit rs =>
    Ok(toJson(prices.list))
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

  def addHotelType = AuthJsAction(AuthorityKey -> hasRole(UserRoleEnum.USER)) { implicit rs =>
    DBAction(parse.json) { implicit req =>
      req.request.body.validate[HotelType].map { hotelType =>
        val hotelTypeId = (hotelTypes returning hotelTypes.map(_.id)) += hotelType
        val r = Map("id" -> hotelTypeId)
        Ok(toJson(r))
      }.recoverTotal { errors =>
        BadRequest(errors.toString)
      }
    }
  }

  def showHotelDetails(hotelId: Int) = DBAction { implicit rs =>
    val hotel = hotels.filter(_.id === hotelId)
    Ok(toJson(hotel.list))
  }

}
