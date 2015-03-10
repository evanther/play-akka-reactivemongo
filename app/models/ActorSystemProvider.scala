package models

/**
 * Created by steve on 12/02/15.
 */
trait ActorSystemProvider {
  val actorSystem: akka.actor.ActorSystem
}
