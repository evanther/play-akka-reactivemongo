package models

import com.typesafe.config.{Config, ConfigFactory}

class Settings(config: Config) {

  import scala.collection.JavaConverters._

//  val apiUri	        = config.getString("api.uri")
//  val apiPort		      = config.getInt("api.port")

  val mongoServers = config.getStringList("mongo.servers").asScala
  val mongoDbName  = config.getString("mongo.dbName")

//  val environments = config.getStringList("environments").asScala.toList

}

object Settings {
  def apply() = {
    new Settings(ConfigFactory.load())
  }
  def apply(conf: Config) = {
    new Settings(conf)
  }
}