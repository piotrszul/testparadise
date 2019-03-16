package name.piotrszul.paradise

import org.scalatra._
import org.json4s.{DefaultFormats, Formats}
import org.scalatra.json._
import name.piotrszul.paradise.domain.Entity
import org.json4s.JsonAST.JValue
import org.json4s.ShortTypeHints
import org.json4s.jackson.Serialization
import org.json4s.JsonAST.JObject
import org.json4s._
import org.json4s.JsonDSL._
import org.slf4j.{Logger, LoggerFactory}
import name.piotrszul.paradise.domain.GraphRepository

class MyScalatraServlet(graphRepository:GraphRepository) extends ScalatraServlet with JacksonJsonSupport with UrlGeneratorSupport {

  def this() {
    this(null)
  }
  
  val logger =  LoggerFactory.getLogger(getClass)
  
  protected implicit lazy val jsonFormats: Formats = Serialization.formats(ShortTypeHints(List(classOf[Entity])))
  
  // Before every action runs, set the content type to be in JSON format.
  before() {
    contentType = formats("json")
  }
  
  get("/") {
    Ok()
  }

  val getNode = get("/node/:id") {
    logger.info("Getting node withs some id {}", params)
    val id = params("id").toInt
    logger.info("Inside id={}, repo={}", id, graphRepository)
    val result = graphRepository.getEntity(id);
    logger.info("Result={}", result)
    result.getOrElse(NotFound("Resource not found"))
  }

  protected override def transformResponseBody(body: JValue): JValue = {
    println(body)
    val id = (body \ "id")
    val asAsInt = id.extract[Int]
    val uri = url(getNode,("id", asAsInt.toString))
    body.removeField(f => f._1 == "jsonClass").asInstanceOf[JObject] ~ ("uri" -> uri)
  }
  
}
