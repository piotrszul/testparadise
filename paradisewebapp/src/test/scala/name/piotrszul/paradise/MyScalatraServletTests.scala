package name.piotrszul.paradise

import org.scalatra.test.scalatest._
import org.scalamock.scalatest.MockFactory

class MyScalatraServletTests extends ScalatraFlatSpec with MockFactory  {

  addServlet(new MyScalatraServlet(), "/*")

  it should "GET / on MyScalatraServlet should return status 200" in {
    get("/") {
      status should equal (200)
    }
  }

 "GET /node/100" should "return status 200 if node exits" in {
    get("/node/100") {
      status should equal (200)
    }
  }

  
}
