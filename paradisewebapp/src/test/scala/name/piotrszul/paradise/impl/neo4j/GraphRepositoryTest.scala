package name.piotrszul.paradise.impl.neo4j


import org.junit.Assert._
import org.junit.Test
import org.junit.BeforeClass
import org.junit.AfterClass
import org.neo4j.harness.TestServerBuilders


class GraphRepositoryTest {
  
  @Test
  def testSometghin() {
    println(GraphRepositoryTest.graphDb.boltURI())
  }
  
}

object GraphRepositoryTest {

  lazy val graphDb = TestServerBuilders
            .newInProcessBuilder()
            .withFixture("" // <5>
                + " CREATE (:Place {name: 'Malmö', longitude: 12.995098, latitude: 55.611730})"
                + " CREATE (:Place {name: 'Aachen', longitude: 6.083736, latitude: 50.776381})"
                + " CREATE (:Place {name: 'Lost place'})"
            )
            .newServer()
  
  @BeforeClass
  def setup() {
  }
  
  @AfterClass
  def tearDown() {
      graphDb.close()  
  }
  
}