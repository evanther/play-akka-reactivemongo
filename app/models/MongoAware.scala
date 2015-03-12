package models

import reactivemongo.api.{DB, MongoDriver}
import reactivemongo.core.nodeset.Authenticate

import scala.concurrent.ExecutionContext.Implicits.global

/**
 * Created by steve on 12/02/15.
 */
trait MongoAware {
  this: ActorSystemProvider with SettingsAware =>

  private lazy val driver = new MongoDriver(actorSystem)
  private lazy val credentials = List(Authenticate(settings.mongoDbName, settings.mongoUser, settings.mongoPassword))
  private lazy val connection = driver.connection(nodes = settings.mongoServers, authentications = credentials)
  lazy val db: DB = connection(settings.mongoDbName)
}
