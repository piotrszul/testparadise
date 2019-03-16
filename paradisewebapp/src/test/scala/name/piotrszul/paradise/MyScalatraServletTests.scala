package name.piotrszul.paradise

import org.scalatra.test.scalatest._
import org.scalamock.scalatest.MockFactory
import name.piotrszul.paradise.domain.GraphRepository
import name.piotrszul.paradise.domain.Entity

class MyScalatraServletTests extends ScalatraFlatSpec with MockFactory  {

  
  val entityRepositoryStub = stub[GraphRepository]
  addServlet(new MyScalatraServlet(entityRepositoryStub), "/*")


 "GET /node/200" should "return status 200 if node exits" in {
    (entityRepositoryStub.getEntity _).when(100).returns(None)
    (entityRepositoryStub.getEntity _).when(200).returns(Some(Entity(200, "TestName")))
    get("/node/200") {
      status should equal (200)
      println(body)
    }
  }

 "GET /node/100" should "return status 404 if node exits" in {
    (entityRepositoryStub.getEntity _).when(100).returns(None)
    (entityRepositoryStub.getEntity _).when(200).returns(Some(Entity(200, "TestName")))
    get("/node/100") {
      status should equal (404)
    }
  }
  
}
