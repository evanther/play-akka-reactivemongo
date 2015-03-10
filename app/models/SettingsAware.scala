package models

/**
 * Created by steve on 12/02/15.
 */
trait SettingsAware {
  lazy val settings = Settings()
}
