package models

import play.api.db.slick.Config.driver.simple._

import scala.slick.lifted.TableQuery

/**
 * Created by bunyod on 1/19/15.
 */

class UsersDao {

  val users = TableQuery[UsersTable]

  def saveUser(user: Account)(implicit session: Session) = {
    val userId = (users returning users.map(_.id)) += user
  }

}
