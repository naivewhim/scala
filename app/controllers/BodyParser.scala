package controllers

import java.io.File

import play.api.mvc.{Action, AnyContent, Controller}

/**
  * Created by NAVER on 2017-11-15.
  *
  * default body parser
  * - text/plain: String
  * - application/json: JsValue
  * - application/xml, text/xml or application/XXX+xml: NodeSeq
  * - application/form-url-encoded: Map[String, Seq[String]]
  * - multipart/form-data: MultipartFormData[TemporaryFile]
  * - any other content type: RawBuffer
  */
object BodyParser extends Controller {
  def read = Action { request =>
    val body: AnyContent = request.body
    val textBody: Option[String] = body.asText

    // Expecting text body
    textBody.map { text =>
      Ok("Got: " + text)
    }.getOrElse {
      BadRequest("Expecting text/plain request body")
    }
  }

  def readBodyUsingParser = Action(parse.text) { request =>
    Ok("Got: " + request.body)
  }

  def readBodyUsingParserWithoutContentTypeChecking = Action(parse.tolerantText) { request =>
    Ok("Got: " + request.body)
  }

  val storeInUserFile = parse.using { request =>
    request.session.get("username").map { user =>
      parse.file(to = new File("/tmp/" + user + ".upload"))
    }.getOrElse {
      sys.error("You don't have the right to upload here")
    }
  }

  def saveRequestBodyInUniqueUserFile = Action(storeInUserFile) { request =>
    Ok("Saved the request content to " + request.body)
  }

  def readRequestBodyMaxLength = Action(parse.text(maxLength = 1024 * 10)) { request =>
    Ok("Got: " + parse.text)
  }

  // Accept only 10KB of data.
  def saveRequestBodyInUniqueUserFileMaxLength = Action(parse.maxLength(1024 * 10, storeInUserFile)) { request =>
    Ok("Saved the request content to " + request.body)
  }
}
