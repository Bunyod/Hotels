package services

import java.util.Date

import akka.actor.{Actor, Props}
import com.typesafe.plugin._
import com.typesafe.scalalogging.slf4j.LazyLogging
import play.api.Play.current
import play.api.db.slick.Config.driver.simple._
import play.api.db.slick.DB
import play.api.libs.concurrent.Akka
import scala.concurrent.duration._
import play.api.libs.concurrent.Execution.Implicits.defaultContext

import scala.slick.lifted.TableQuery

object MessageManager {
  case class SendEmail(address: String, text: String, emailSubject: String)
}

class MessageManager() extends Actor with LazyLogging {

  import services.MessageManager._

  def receive = {
    case email@SendEmail(address, text, emailSubject) =>
      logger.info(s"Sending Email: $email")
        val mail = use[MailerPlugin].email
        mail.setSubject(emailSubject)
        mail.setBcc(List(address): _*)
        mail.setFrom("Uzhotels <bunyodreal@gmail.com>")
        mail.send(text)
  }
}
