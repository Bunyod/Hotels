package models

import play.api.db.slick.Config.driver.simple._
import play.api.libs.functional.syntax._
import play.api.libs.json._
import java.util.Date

trait Date2SqlDate {
  implicit val date2SqlDate = MappedColumnType.base[Date, java.sql.Timestamp](
    d => new java.sql.Timestamp(d.getTime),
    d => new java.util.Date(d.getTime)
  )
}

case class Hotel(id: Option[Int],
                 name: String,
                 phone: String,
                 fax: String,
                 email: String,
                 webSite: String,
                 rate: String,
                 image: String,
                 star: Int,
                 distCenter: String,
                 hotelType: Int,
                 entrance: String,
                 exit: String,
                 regionId: Int,
                 cityId: Int,
                 address: String,
                 latitude: Double,
                 longitude: Double
                  ) {
}


class HotelsTable(tag: Tag) extends Table[Hotel](tag, "HOTEL") {

  val hotelTypes = TableQuery[HotelTypesTable]

  def id = column[Int]("ID", O.PrimaryKey, O.AutoInc)

  def name = column[String]("NAME", O.Default(""))

  def phone = column[String]("phone", O.Default(""))

  def fax = column[String]("FAX", O.Default(""))

  def email = column[String]("EMAIL", O.Default(""))

  def webSite = column[String]("WEB_SITE", O.Default(""))

  def rate = column[String]("RATE", O.Default(""))

  def image = column[String]("IMAGE", O.Default(""))

  def star = column[Int]("STAR", O.NotNull)

  def distCenter = column[String]("DIST_CENTER", O.Default(""))

  def hotelType = column[Int]("HOTEL_TYPE", O.Default(0))

  def entrance = column[String]("ENTRANCE", O.Default("12:00"))

  def exit = column[String]("EXIT", O.Default("12:00"))

  def regionId = column[Int]("REGION_ID", O.NotNull)

  def cityId = column[Int]("CITY_ID", O.NotNull)

  def address = column[String]("ADDRESS", O.Default(""))

  def latitude = column[Double]("LATITUDE", O.NotNull)

  def longitude = column[Double]("LONGITUDE", O.NotNull)

  def * = (id.?, name, phone, fax, email, webSite, rate, image, star, distCenter, hotelType, entrance, exit, regionId,
           cityId, address, latitude, longitude) <>(Hotel.tupled, Hotel.unapply _)

  def hotelTypeId = foreignKey("HOTEL_FK_HOTEL_TYPE_ID", hotelType, hotelTypes)(_.id)

}

case class User(id: Option[Int],
                username: String,
                email: String,
                age: Int) {
}

class UsersTable(tag: Tag) extends Table[User](tag, "USER") {

  def id = column[Int]("ID", O.PrimaryKey, O.AutoInc)

  def username = column[String]("USERNAME", O.NotNull)

  def email = column[String]("EMAIL", O.NotNull)

  def age = column[Int]("AGE", O.NotNull)

  def * = (id.?, username, email, age) <>(User.tupled, User.unapply _)

}

case class HotelAdmin(id: Option[Int],
                      hotelId: Int,
                      firstName: String,
                      lastName: String,
                      login: String,
                      password: String,
                      email: String,
                      phone: String) {
}

class HotelAdminsTable(tag: Tag) extends Table[HotelAdmin](tag, "HOTEL_ADMIN") {

  val hotels = TableQuery[HotelsTable]

  def id = column[Int]("ID", O.PrimaryKey, O.AutoInc)

  def hotelId = column[Int]("HOTEL_ID", O.NotNull)

  def firstName = column[String]("FIRST_NAME", O.NotNull)

  def lastName = column[String]("LAST_NAME", O.NotNull)

  def login = column[String]("LOGIN", O.NotNull)

  def password = column[String]("PASSWORD", O.NotNull)

  def email = column[String]("EMAIL", O.NotNull)

  def phone = column[String]("PHONE", O.NotNull)

  def * = (id.?, hotelId, firstName, lastName, login, password, email, phone) <>(HotelAdmin.tupled, HotelAdmin.unapply _)

  def hotel = foreignKey("HOTEL_ADMIN_FK_HOTEL_ID", hotelId, hotels)(_.id)

}

case class Review(id: Option[Int],
                      hotelId: Int,
                      userId: Int,
                      content: String,
                      isActive: Boolean,
                      createdAt: Date = new Date()) {
}

class ReviewTable(tag: Tag) extends Table[Review](tag, "REVIEW")  with Date2SqlDate {

  val hotels = TableQuery[HotelsTable]
  val users = TableQuery[UsersTable]

  def id = column[Int]("ID", O.PrimaryKey, O.AutoInc)

  def hotelId = column[Int]("HOTEL_ID", O.NotNull)

  def userId = column[Int]("USER_ID", O.NotNull)

  def content = column[String]("LAST_NAME", O.NotNull)

  def isActive= column[Boolean]("IS_ACTIVE", O.NotNull)

  def createdAt = column[Date]("CREATED_AT")

  def * = (id.?, hotelId, userId, content, isActive, createdAt) <>(Review.tupled, Review.unapply _)

  def hotel = foreignKey("REVIEW_FK_HOTEL_ID", hotelId, hotels)(_.id)
  def user = foreignKey("REVIEW_FK_USER_ID", userId, users)(_.id)

}

case class RoomType(id: Option[Int],
                  name: String) {
}

class RoomTypesTable(tag: Tag) extends Table[RoomType](tag, "ROOM_TYPE") {

  def id = column[Int]("ID", O.PrimaryKey, O.AutoInc)

  def name = column[String]("NAME", O.NotNull)

  def * = (id.?, name) <>(RoomType.tupled, RoomType.unapply _)

}

case class Room(id: Option[Int],
                 hotelId: Int,
                 roomType: Int,
                 count: Int,
                 price: Double,
                 discount: Double,
                 wifi: Boolean,
                 breakfast: String,
                 parking: Boolean,
                 fitness: Boolean,
                 pool: Boolean,
                 transport: String,
                 barRest: String,
                 bathroom: String) {
}

class RoomsTable(tag: Tag) extends Table[Room](tag, "ROOM") {

  val hotels = TableQuery[HotelsTable]
  val roomTypes = TableQuery[RoomTypesTable]

  def id = column[Int]("ID", O.PrimaryKey, O.AutoInc)

  def hotelId = column[Int]("HOTEL_ID", O.NotNull)

  def roomType = column[Int]("ROOM_TYPE", O.NotNull)

  def count = column[Int]("COUNT", O.NotNull)

  def price = column[Double]("PRICE", O.NotNull)

  def discount = column[Double]("DISCOUNT", O.Default(0.0))

  def wifi = column[Boolean]("WIFI", O.Default(false))

  def breakfast = column[String]("BREAKFAST", O.Default(""))

  def parking = column[Boolean]("PARKING", O.Default(false))

  def fitness = column[Boolean]("FITNESS", O.Default(false))

  def pool = column[Boolean]("POOL", O.Default(false))

  def transport = column[String]("TRANSPORT", O.Default(""))

  def barRest= column[String]("BAR_REST", O.Default(""))

  def bathroom = column[String]("BATHROOM", O.Default(""))

  def * = (id.?, hotelId, roomType, count, price, discount, wifi, breakfast, parking, fitness, pool, transport, barRest,
    bathroom) <>(Room.tupled, Room.unapply _)


  def hotel = foreignKey("ROOM_FK_HOTEL_ID", hotelId, hotels)(_.id)
  def roomTypeId = foreignKey("ROOM_FK_ROOM_TYPE", roomType, roomTypes)(_.id)

}

case class HotelType(id: Option[Int],
                name: String) {
}

class HotelTypesTable(tag: Tag) extends Table[HotelType](tag, "HOTEL_TYPE") {

  def id = column[Int]("ID", O.PrimaryKey, O.AutoInc)

  def name = column[String]("NAME", O.Default(""))

  def * = (id.?, name) <>(HotelType.tupled, HotelType.unapply _)
}

object JsonFormats {

  import play.api.libs.json.Json

  implicit val userWrites = Json.writes[User]
  implicit val userReads: Reads[User] = (
    ( JsPath \ "id").readNullable[Int] and
      (JsPath \ "username").read[String] and
      (JsPath \ "email").read[String] and
      (JsPath \ "age").read[Int]
    )(User)

  implicit val hotelWrites = Json.writes[Hotel]
  implicit val hotelReads: Reads[Hotel] = (
    ( JsPath \ "id").readNullable[Int] and
      (JsPath \ "name").read[String]  and
      (JsPath \ "phone").read[String]  and
      (JsPath \ "fax").read[String]  and
      (JsPath \ "email").read[String] and
      (JsPath \ "webSite").read[String] and
      (JsPath \ "rate").read[String] and
      (JsPath \ "image").read[String] and
      (JsPath \ "star").read[Int] and
      (JsPath \ "distCenter").read[String] and
      (JsPath \ "hotelType").read[Int] and
      (JsPath \ "entrance").read[String] and
      (JsPath \ "exit").read[String] and
      (JsPath \ "regionId").read[Int] and
      (JsPath \ "cityId").read[Int] and
      (JsPath \ "address").read[String] and
      (JsPath \ "latitude").read[Double] and
      (JsPath \ "longitude").read[Double]
    )(Hotel)

  implicit val hotelAdminWrites = Json.writes[HotelAdmin]
  implicit val hotelAdminReads: Reads[HotelAdmin] = (
    ( JsPath \ "id").readNullable[Int] and
      (JsPath \ "hotelId").read[Int]  and
      (JsPath \ "firstName").read[String]  and
      (JsPath \ "lastName").read[String]  and
      (JsPath \ "login").read[String] and
      (JsPath \ "password").read[String] and
      (JsPath \ "email").read[String] and
      (JsPath \ "phone").read[String]
    )(HotelAdmin)


  implicit val reviewWrites = Json.writes[Review]
  implicit val reviewReads: Reads[Review] = (
    ( JsPath \ "id").readNullable[Int] and
      (JsPath \ "hotelId").read[Int]  and
      (JsPath \ "userId").read[Int]  and
      (JsPath \ "content").read[String]  and
      (JsPath \ "isActive").read[Boolean] and
      (JsPath \ "createdAt").read[Date](Format(Reads.dateReads("MM/dd/yyyy HH:mm"), Writes.dateWrites("mm/dd/yyyy HH:mm")))
    )(Review)

  implicit val roomTypeWrites = Json.writes[RoomType]
  implicit val roomTypeReads: Reads[RoomType] = (
    ( JsPath \ "id").readNullable[Int] and
      (JsPath \ "name").read[String]
    )(RoomType)

  implicit val roomWrites = Json.writes[Room]
  implicit val roomReads: Reads[Room] = (
    ( JsPath \ "id").readNullable[Int] and
      (JsPath \ "hotelId").read[Int] and
      (JsPath \ "roomType").read[Int] and
      (JsPath \ "count").read[Int] and
      (JsPath \ "price").read[Double] and
      (JsPath \ "discount").read[Double] and
      (JsPath \ "wifi").read[Boolean] and
      (JsPath \ "breakfast").read[String] and
      (JsPath \ "parking").read[Boolean] and
      (JsPath \ "fitness").read[Boolean] and
      (JsPath \ "pool").read[Boolean] and
      (JsPath \ "transport").read[String] and
      (JsPath \ "barRest").read[String] and
      (JsPath \ "bathroom").read[String]
    )(Room)

  implicit val hotelTypeWrites = Json.writes[HotelType]
  implicit val hotelTypeReads: Reads[HotelType] = (
    (JsPath \ "id").readNullable[Int] and
    (JsPath \ "name").read[String]
    )(HotelType)

}
