package name.piotrszul.paradise.impl.neo4j

import org.scalatest.FlatSpec
import org.scalamock.scalatest.MockFactory

class Neo4jGraphRepositorySpec extends FlatSpec with MockFactory {
  
  it should "do norhing" in {
    val repo = new Neo4jGraphRepository(new Neo4jQueryTemplate())
    assert(true)
  }
  
}