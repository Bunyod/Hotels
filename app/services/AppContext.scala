package services

import akka.actor.Props
import akka.util.Timeout
import com.typesafe.config.ConfigFactory
import com.typesafe.scalalogging.slf4j.LazyLogging
import play.api.Play.current
import play.api.libs.concurrent.Akka

import scala.concurrent.duration._

object AppContext extends LazyLogging {

  val config = ConfigFactory.load()

  implicit val actorSystem = Akka.system
  implicit val timeout = Timeout(10 seconds)

  val messageManager = actorSystem.actorOf(Props(new MessageManager()), "messageManager")

}
