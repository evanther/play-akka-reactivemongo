package models

import play.api.libs.json._

/**
 * Created by evalery on 10/03/15.
 */
object Formats {
  implicit val AlarmFormat = Json.format[Alarm]
}