package controllers

import play.api.mvc._

object Application extends Controller {

  //  def index = Action {
  //    Ok("Hello world!")
  //  }

  //  def index = Action {
  //    Redirect("/user/home")
  //  }

  //  def index = Action {
  //    Result(
  //      header = ResponseHeader(200, Map(CONTENT_TYPE -> "text/plain")),
  //      body = Enumerator("Hello world123!".getBytes())
  //    )
  //  }

  def index = TODO

  def helloBob = Action {
    Redirect(routes.Application.hello("Bob"))
  }

  def optionValue(version: Option[String]) = Action {
    Ok("option value: " + version)
  }

  def defaultValue(n: String) = Action {
    Ok("default value: " + n)
  }

  def fixValue(n: String) = Action {
    Ok("fixed value: " + n)
  }

  def hello(name: String) = Action {
    Ok(views.html.index(name))
  }

  def hello2(id: Long) = Action {
    Ok("hello" + id)
  }

  val echo = Action { request =>
    Ok("Got request [" + request + "]")
  }

  val ok = Ok("Hello world!")
  val notFound = NotFound
  val pageNotFound = NotFound(<h1>Page not found</h1>)
  val oops = InternalServerError("Oops")
  val anyStatus = Status(488)("Strange response type")
}