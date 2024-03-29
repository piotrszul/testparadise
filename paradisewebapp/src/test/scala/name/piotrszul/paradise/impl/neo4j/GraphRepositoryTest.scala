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


class GraphRepositoryTest {
  
  @Test
  def testSometghin() {
    println(GraphRepositoryTest.graphDb)
    GraphRepositoryTest.graphDb.execute("CREATE (:Place {name: 'Malmö', longitude: 12.995098, latitude: 55.611730})")
    val driver = GraphDatabase.driver("bolt://localhost:7000")
    val session = driver.session()
    val result = session.run("MATCH (p) RETURN (p)")
    println(result)
    println(result.single().get(0))
    session.close()
  }
  
}

object GraphRepositoryTest {

  val bolt = GraphDatabaseSettings.boltConnector( "0" );
  lazy val graphDb = new GraphDatabaseFactory().newEmbeddedDatabaseBuilder(new File("target"))
                .setConfig( bolt.`type`, "BOLT" )
                .setConfig( bolt.enabled, "true" )
                .setConfig( bolt.address, "localhost:7000" )
                .newGraphDatabase();
  @BeforeClass
  def setup() {
  //  
  }
  
  @AfterClass
  def tearDown() {
      graphDb.shutdown()  
  }
  
}