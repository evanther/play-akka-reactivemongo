package controllers

import models.Formats.AlarmFormat
import models._
import play.api.Play.current
import play.api.libs.concurrent.Akka
import play.api.libs.json.Json
import play.api.mvc._

import scala.concurrent.ExecutionContext.Implicits.global

/**
 * Created by evalery on 06/02/15.
 */
object AlarmController extends Controller with AlarmsRepository with MongoAware with SettingsAware with ActorSystemProvider {

  lazy val actorSystem = Akka.system

  def createAlarm = Action.async(parse.json) { request =>
    val alarm = request.body.as[Alarm]
    insertAlarm(alarm) map { lastError =>
      if (lastError.ok) Created
      else InternalServerError(lastError.errMsg.getOrElse("Unknown"))
    } recover {
      case DuplicatedAlarmIdException(id) => BadRequest(s"Duplicated alarm id <$id>")
    }
  }

  def getAlarm(id: Long) = Action.async {
    findAlarm(id) map { optAlarm =>
      if (optAlarm.isDefined) Ok(Json.toJson(optAlarm.get))
      else NotFound(s"Alarm id <$id> not found")
    }
  }

  def listAllAlarms = Action.async {
    listAlarms() map { alarms =>
      Ok(Json.toJson(alarms))
    }
  }

}