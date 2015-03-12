package models

import com.typesafe.config.{Config, ConfigFactory}

import scala.util.Properties

class Settings() {
//  constructor param: config: Config

//  import scala.collection.JavaConverters._

//  val mongoServers = config.getStringList("mongo.servers").asScala
//  val mongoDbName  = config.getString("mongo.dbName")

  val mongoServers: List[String] = {
    val servers = Properties.envOrNone("MONGO_SERVERS")
    if (!servers.isDefined) throw new RuntimeException("Missing environment variable MONGO_SERVERS")
    servers.get.split(',').map(s => s.trim).toList
  }

  val mongoDbName: String = {
    val dbname = Properties.envOrNone("MONGO_DBNAME")
    if (!dbname.isDefined) throw new RuntimeException("Missing environment variable MONGO_DBNAME")
    dbname.get
  }

  val mongoUser: String = {
    val username = Properties.envOrNone("MONGO_USER")
    if (!username.isDefined) throw new RuntimeException("Missing environment variable MONGO_USER")
    username.get
  }

  val mongoPassword: String = {
    val password = Properties.envOrNone("MONGO_PASSWORD")
    if (!password.isDefined) throw new RuntimeException("Missing environment variable MONGO_PASSWORD")
    password.get
  }

}

object Settings {
  def apply() = {
//    ConfigFactory.load()
    new Settings()
  }
//  def apply(conf: Config) = {
//    new Settings(conf)
//  }
}