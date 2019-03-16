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
  
class MyScalatraServlet extends ScalatraServlet with JacksonJsonSupport with UrlGeneratorSupport {

  protected implicit lazy val jsonFormats: Formats = Serialization.formats(ShortTypeHints(List(classOf[Entity])))
  
  // Before every action runs, set the content type to be in JSON format.
  before() {
    contentType = formats("json")
  }
  
  get("/") {
    Ok()
  }

  val getNode = get("/node/:id") {
    Ok(Entity(10,"Piotr"))
  }

  protected override def transformResponseBody(body: JValue): JValue = {
    println(body)
    val id = (body \ "id")
    val asAsInt = id.extract[Int]
    val uri = url(getNode,("id", asAsInt.toString))
    body.removeField(f => f._1 == "jsonClass").asInstanceOf[JObject] ~ ("uri" -> uri)
  }
  
}
