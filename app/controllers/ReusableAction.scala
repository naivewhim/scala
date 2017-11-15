package controllers

/**
  * Created by NAVER on 2017-11-15.
  */
import play.api.mvc._
import play.Logger
import scala.concurrent.Future

case class Logging[A](action: Action[A]) extends Action[A] {

  def apply(request: Request[A]): Future[Result] = {
    Logger.info("Calling action")
    action(request)
  }

  lazy val parser = action.parser
}