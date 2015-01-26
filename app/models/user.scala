package models

import play.api.db.slick.Config.driver.simple._
import scala.slick.lifted.TableQuery

/**
 * Created by bunyod on 1/19/15.
 */

object UserStateEnum extends BaseEnum {

  override val enumPrefix = "userState"

  type UserState = Value
  val ACTIVE = Value(1)
  val INACTIVE = Value(0)
}

object UserRoleEnum extends BaseEnum {

  override val enumPrefix = "userRole"

  type UserRole = Value
  val ADMINISTATOR = Value(1)
  val ADMIN = Value(2)
  val USER = Value(3)
}

case class Credential(login: String,
                      password: String)

case class User(id: Option[Int],
                email: String,
                password: String,
                firstName: String,
                lastName: String,
                role: UserRoleEnum.UserRole,
                status: Int
                 )

class UsersTable(tag: Tag) extends Table[User](tag, "USER") {

  def id = column[Int]("ID", O.PrimaryKey, O.AutoInc)

  def email = column[String]("EMAIL", O.NotNull)

  def password = column[String]("PASSWORD", O.NotNull)

  def firstName = column[String]("FIRST_NAME", O.NotNull)

  def lastName = column[String]("LAST_NAME", O.NotNull)

  def role = column[UserRoleEnum.UserRole]("ROLE", O.NotNull)

  def status = column[Int]("STATUS", O.NotNull)

  def * = (id.?, email, password, firstName, lastName, role, status) <>(User.tupled, User.unapply _)

  val sorting = Map(
    "id" -> id, "firstName" -> firstName, "lastName" -> lastName,
    "email" -> email, "password" -> password, "role" -> role, "status" -> status)
}
