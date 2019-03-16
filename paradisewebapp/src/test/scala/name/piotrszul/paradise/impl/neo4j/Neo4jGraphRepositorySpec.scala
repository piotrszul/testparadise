package name.piotrszul.paradise.impl.neo4j

import org.scalatest.FlatSpec
import org.scalamock.scalatest.MockFactory
import name.piotrszul.paradise.domain.Path

class Neo4jGraphRepositorySpec extends FlatSpec with MockFactory {
  
  it should "do norhing" in { 
    val queryTemplate = mock[Neo4jQueryTemplate]
    (queryTemplate.querySingle[Path] _).expects("", 10, Neo4jGraphRepository.resultToPath _)
    val repo = new Neo4jGraphRepository(queryTemplate)
    assert(true)
    repo.getShortedPath(10, 20)
  } 
}