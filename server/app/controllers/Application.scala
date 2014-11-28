package controllers

import play.api._
import play.api.mvc._
import play.api.libs._
import play.api.libs.json._
import play.api.libs.iteratee._

object Application extends Controller {

  def index = Action {
    Ok(views.html.index("Your new application is ready."))
  }

  val (events, channel) = Concurrent.broadcast[JsValue]

  def light(state: Boolean) = Action {
    channel.push(Json.obj("state" -> state))
    Ok
  }

  def humidity(value: Int) = Action { NotImplemented }

  def temperature(value: Int) = Action { NotImplemented }

  def stream = Action {
    Ok.stream {
      events &> EventSource()
    }
  }

}
