package controllers

import play.api.mvc._

object Application extends Controller {

    def index = Action {
      Ok("Hello world!")
    }

  //  def index = Action {
  //    Redirect("/user/home")
  //  }

  //  def index = Action {
  //    Result(
  //      header = ResponseHeader(200, Map(CONTENT_TYPE -> "text/plain")),
  //      body = Enumerator("Hello world123!".getBytes())
  //    )
  //  }

//  def index = TODO

  def hello(name: String) = Action {
    Ok(views.html.index(name))
  }

  def fixValue(n: String) = Action {
    Ok("fixed value: " + n)
  }

  def defaultValue(n: String) = Action {
    Ok("default value: " + n)
  }

  def optionValue(version: Option[String]) = Action {
    Ok("option value: " + version)
  }

  def helloUser(id: Long) = Action {
    Ok("hello user id: " + id)
  }

  def helloBob = Action {
    Redirect(routes.Application.hello("Bob"))
  }

  val echo = Action { request =>
    Ok("Got request [" + request + "]")
  }

  // 다음과 같이 변수로 선언해서 사용할 수 있음
  val ok = Ok("Hello world!")
  val notFound = NotFound
  val oops = InternalServerError("Oops")
  val anyStatus = Status(488)("Strange response type")

  // 자동으로 Content-Type = application/xml (by. play.api.http.ContentTypeOf)
  val pageNotFound = NotFound(<h1>Page not found</h1>)

  // content type 을 바꾸고 싶을 때때
  val htmlResult1 = Ok(<h1>Hello World!</h1>).as("text/html")
  val htmlResult2 = Ok(<h1>Hello World!</h1>).as(HTML)

  // 헤더 조작 (이전의 값이 지워지는 형태)
  val result = Ok("Hello World!").withHeaders(
    CACHE_CONTROL -> "max-age=3600",
    ETAG -> "xx")

  // 쿠키 추가
  val cookieAdd = Ok("Hello world").withCookies(
    Cookie("theme", "blue"))

  // 쿠키 해제
  val cookieDiscard = cookieAdd.discardingCookies(DiscardingCookie("theme"))

  // 쿠키 추가 및 해제
  val cookieAddAndDiscard = cookieAdd.withCookies(Cookie("theme", "blue")).discardingCookies(DiscardingCookie("skin"))

  // charset 변경 (codec 클래스에 생김 default implicit 은 utf-8)
  implicit val myCustomCharset = Codec.javaSupported("iso-8859-1")

  def changeCharset = Action {
    Ok(<h1>Hello World!</h1>).as(HTML)
  }

  def customAction = LoggingAction {
    Ok("Hello World")
  }

  def customActionWithBodyParser = LoggingAction(parse.text) { request =>
    Ok("Got a body " + request.body.length + " bytes long")
  }
}