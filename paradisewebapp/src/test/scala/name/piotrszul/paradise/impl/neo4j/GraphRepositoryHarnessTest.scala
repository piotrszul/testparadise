package name.piotrszul.paradise.impl.neo4j


import org.junit.Assert._
import org.junit.Test
import org.junit.BeforeClass
import org.junit.AfterClass
//import org.neo4j.harness.TestServerBuilders
import org.neo4j.graphdb.factory.GraphDatabaseFactory
import java.io.File
import org.neo4j.graphdb.factory.GraphDatabaseSettings
import org.neo4j.driver.v1.GraphDatabase
import org.neo4j.harness.TestServerBuilders


class GraphRepositoryHarnsessTest {
  
  @Test
  def testSometghin() {
    println(GraphRepositoryHarnsessTest.graphDb)
    val driver = GraphDatabase.driver(GraphRepositoryHarnsessTest.graphDb.boltURI())
    val session = driver.session()
    val result = session.run("MATCH (p) RETURN (p)")
    println(result)
    println(result.single().get(0))
    session.close()
  }
  
}

object GraphRepositoryHarnsessTest {

  lazy val graphDb = TestServerBuilders
            .newInProcessBuilder()
            .withFixture("" // <5>
                + " CREATE (:Place {name: 'Malmö', longitude: 12.995098, latitude: 55.611730})"
            )
            .newServer();
  @BeforeClass
  def setup() {
  //  
  }
  
  @AfterClass
  def tearDown() {
      graphDb.close()  
  }
  
}