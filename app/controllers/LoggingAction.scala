package controllers

/**
  * Created by NAVER on 2017-11-15.
  */
import play.Logger
import play.api.mvc._

import scala.concurrent.Future

object LoggingAction extends ActionBuilder[Request] {
  def invokeBlock[A](request: Request[A], block: (Request[A]) => Future[Result]) = {
    Logger.info("Calling action")
    block(request)
  }
}