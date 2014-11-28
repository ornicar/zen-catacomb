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
    channel.push(Json.obj("light" -> state))
    Ok
  }

  def humidity(value: Int) = Action {
    // Assuming that value range is from 0 -> 100
    val percentage = value.toDouble / 100.0
    channel.push(Json.obj("humidity" -> percentage))
    Ok
  }

  def temperature(value: Int) = Action {
    channel.push(Json.obj("temperature" -> value))
    Ok }

  def stream = Action {
    Ok.chunked(
      events &> EventSource()
    ) as "text/event-stream"
  }

}
