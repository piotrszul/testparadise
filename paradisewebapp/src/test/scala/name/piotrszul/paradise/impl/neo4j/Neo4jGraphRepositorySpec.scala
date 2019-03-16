package name.piotrszul.paradise.impl.neo4j

import org.scalatest.FlatSpec
import org.scalamock.scalatest.MockFactory
import name.piotrszul.paradise.domain.Path
import org.neo4j.driver.v1.StatementResult



class Neo4jGraphRepositorySpec extends FlatSpec with MockFactory {
  
  it should "do norhing" in { 
    val queryTemplate = mock[Neo4jQueryTemplate]
    (queryTemplate.querySingle[Path] _).expects("", Map("start_id"->10), ResultToPath).returns(null)
    val repo = new Neo4jGraphRepository(queryTemplate)
    repo.getShortedPath(10, 20)
  } 
  
  
}