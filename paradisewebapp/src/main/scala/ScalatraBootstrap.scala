import name.piotrszul.paradise._
import org.scalatra._
import javax.servlet.ServletContext
import name.piotrszul.paradise.impl.neo4j.Neo4jGraphRepository
import org.neo4j.driver.v1.GraphDatabase
import name.piotrszul.paradise.impl.neo4j.DriverNeo4jQueryTemplate
import org.neo4j.driver.v1.AuthTokens

class ScalatraBootstrap extends LifeCycle {
  override def init(context: ServletContext) {    
    // do all the wiring here
    val driver = GraphDatabase.driver("bolt://localhost:7687", AuthTokens.basic( "neo4j", "maggie26"))
    val graphRepository = new Neo4jGraphRepository(new DriverNeo4jQueryTemplate(driver))
    context.mount(new MyScalatraServlet(graphRepository), "/*")
  }
}
