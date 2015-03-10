package models

import play.api.libs.json._

/**
 * Created by evalery on 10/03/15.
 */
object Formats {

  implicit object AlarmFormat extends Format[Alarm] {

    override def reads(json: JsValue) = JsSuccess(Alarm(
      (json \ "alarmId").as[Long],
      (json \ "typeId").as[Int],
      (json \ "typeName").as[String],
      (json \ "limit").as[Float],
      (json \ "userId").as[Long]
    ))

    override def writes(alarm: Alarm) = Json.obj(
      "alarmId" -> alarm.alarmId,
      "typeId" -> alarm.typeId,
      "typeName" -> alarm.typeName,
      "limit" -> alarm.limit,
      "userId" -> alarm.userId
    )

  }

}