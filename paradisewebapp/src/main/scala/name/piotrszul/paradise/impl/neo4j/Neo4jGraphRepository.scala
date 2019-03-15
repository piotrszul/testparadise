package name.piotrszul.paradise.impl.neo4j

import name.piotrszul.paradise.domain.GraphRepository
import name.piotrszul.paradise.domain.Entity
import name.piotrszul.paradise.domain.Path

class Neo4jGraphRepository(queryTemplate:Neo4jQueryTemplate) extends GraphRepository {
  def getEntity(id: Int): Entity = {
    ???
  }

  def getShortedPath(startId: Int, endId: Int): Path = {
    queryTemplate.evaluate("", startId)
    ???
  }
}