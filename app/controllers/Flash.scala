package controllers

import play.api.mvc.{Action, Controller}

/**
  * Created by NAVER on 2017-11-15.
  *
  * 플래시가 세션과 다른 점
  * 1) 한 번의 요청에서만 유효함
  * 2) 사용자가 수정할 수 있음
  *
  * 플래시는 경쟁 상태를 통해 만들어져서 순서에 대한 보장이 없다.
  */
object Flash extends Controller {
  def get = Action { implicit request =>
    Ok {
      request.flash.get("success").getOrElse("Welcome!")
    }
  }

  /*
  view 에서 꺼내려면,

  @()(implicit flash: Flash)
  ...
  @flash.get("success").getOrElse("Welcome!")
  ...
   */
  // 이런 에러가 뜨면, ‘could not find implicit value for parameter flash: play.api.mvc.Flash’
  /*
  def index() = Action {
    implicit request =>
      Ok(views.html.Application.index())
  }
   */
  def save = Action {
    Redirect("/home").flashing(
      "success" -> "The item has been created")
  }
}
