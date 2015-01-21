package models

import play.api.db.slick.Config.driver.simple._

import scala.slick.lifted.TableQuery

/**
 * Created by user17 on 1/19/15.
 */
class UsersDao {

  val users = TableQuery[UsersTable]

  def saveUser(user: User)(implicit session: Session) = {
    val userId = (users returning users.map(_.id)) += user
  }

}
