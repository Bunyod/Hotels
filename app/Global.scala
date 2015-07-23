import controllers.routes
import play.api._
import play.api.mvc.Results._
import play.api.mvc._

import scala.concurrent.Future

import akka.actor.Props
import com.typesafe.config.ConfigFactory
import controllers.routes
import play.api._
import play.api.libs.concurrent.Akka
import play.api.mvc.Results._
import play.api.mvc._
import scala.concurrent.Future




object Global extends GlobalSettings {

  override def onHandlerNotFound(request: RequestHeader) = {
    Future.successful(Redirect(routes.Application.index()))
  }

  override def onStart(app: Application): Unit = {

    services.AppContext.config

    import services.AppContext._
    messageManager

  }
}

