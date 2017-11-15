package controllers

import play.api.mvc.{Controller, _}

/**
  * Created by NAVER on 2017-11-15.
  */
object Session extends Controller {
  def read = Action { request =>
    request.session.get("connected").map { user =>
      Ok("Hello " + user)
    }.getOrElse {
      Unauthorized("Oops, you are not connected")
    }
  }

  def reset = Action {
    Ok("Welcome!").withSession(
      "connected" -> "user@gmail.com")
  }

  def add = Action { request =>
    Ok("Hello World!").withSession(
      request.session + ("saidHello" -> "yes"))
  }

  def remove = Action { request =>
    Ok("Theme reset!").withSession(
      request.session - "theme")
  }

  def discardWhole = Action {
    Ok("Bye").withNewSession
  }
}
