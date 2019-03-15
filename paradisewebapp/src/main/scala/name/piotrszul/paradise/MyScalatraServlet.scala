package name.piotrszul.paradise

import org.scalatra._
import net.liftweb.json.JsonDSL._
import net.liftweb.json.Serialization.{read, write}
import name.piotrszul.paradise.domain.Entity

class MyScalatraServlet extends ScalatraServlet {

  implicit val jsonFormat = net.liftweb.json.DefaultFormats
  
  get("/") {
    Ok()
  }

  get("/node/") {
    Ok(write(Entity(10,"Piotr")))
  }
  
}
