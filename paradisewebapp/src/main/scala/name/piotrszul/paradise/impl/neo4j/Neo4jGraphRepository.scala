package name.piotrszul.paradise.impl.neo4j

import name.piotrszul.paradise.domain.GraphRepository
import name.piotrszul.paradise.domain.Entity
import name.piotrszul.paradise.domain.Path
import org.neo4j.driver.v1.StatementResult


class Neo4jGraphRepository(queryTemplate:Neo4jQueryTemplate) extends GraphRepository {
  def getEntity(id: Int): Option[Entity] = None

  def getShortedPath(startId: Int, endId: Int): Path = {
    queryTemplate.querySingle[Path]("", Map("start_id" -> startId), ResultToPath)
  }
}
