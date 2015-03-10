package models

import models.Formats.AlarmFormat
import play.api.libs.json.Json
import play.modules.reactivemongo.json.collection.JSONCollection
import reactivemongo.core.commands.LastError

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

/**
 * Created by steve on 19/02/15.
 */
trait AlarmsRepository {
  self: MongoAware =>

  private lazy val collection = db.collection[JSONCollection]("alarms")

  def insertAlarm(alarm: Alarm): Future[LastError] = {
    val id = alarm.alarmId
    findAlarm(id) flatMap { optAlarm =>
      if (optAlarm.isDefined) Future.failed(new DuplicatedAlarmIdException(id))
      else collection.insert(alarm)
    }
  }

  def findAlarm(alarmId: Long): Future[Option[Alarm]] = {
    collection.find(Json.obj("alarmId" -> alarmId)).cursor[Alarm].headOption
  }

  def listAlarms(): Future[List[Alarm]] = {
    collection.find(Json.obj()).cursor[Alarm].collect[List]()
  }

}
