package name.piotrszul.paradise.impl.neo4j

import name.piotrszul.paradise.domain.GraphRepository
import name.piotrszul.paradise.domain.Entity
import name.piotrszul.paradise.domain.Path
import org.neo4j.driver.v1.StatementResult

class Neo4jGraphRepository(queryTemplate:Neo4jQueryTemplate) extends GraphRepository {
  def getEntity(id: Int): Entity = {
    ???
  }

  def getShortedPath(startId: Int, endId: Int): Path = {
    queryTemplate.querySingle("", startId, Neo4jGraphRepository.resultToPath _)
  }
}


object Neo4jGraphRepository {
  def resultToPath(result: StatementResult):Path = ???
}