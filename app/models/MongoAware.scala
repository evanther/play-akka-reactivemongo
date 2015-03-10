package models

import reactivemongo.api.MongoDriver

import scala.concurrent.ExecutionContext.Implicits.global

/**
 * Created by steve on 12/02/15.
 */
trait MongoAware {
  this: ActorSystemProvider with SettingsAware =>

  private val driver = new MongoDriver(actorSystem)
  private val connection = driver.connection(settings.mongoServers)
  lazy val db = connection(settings.mongoDbName)
}
