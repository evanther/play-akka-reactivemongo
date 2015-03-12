package models

import reactivemongo.api.MongoDriver
import reactivemongo.core.nodeset.Authenticate

import scala.concurrent.ExecutionContext.Implicits.global

/**
 * Created by steve on 12/02/15.
 */
trait MongoAware {
  this: ActorSystemProvider with SettingsAware =>

  private val driver = new MongoDriver(actorSystem)
  private val credentials = Seq(Authenticate(settings.mongoDbName, settings.mongoUser, settings.mongoPassword))
  private val connection = driver.connection(nodes = settings.mongoServers, authentications = credentials)
  lazy val db = connection(settings.mongoDbName)
}
