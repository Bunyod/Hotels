package controllers

import play.api._
import play.api.mvc._

/**
 * Created by bunyod on 1/19/15.
 */

object Application extends Controller {

  def index = Action {
    Redirect(routes.Users.index)
  }

}