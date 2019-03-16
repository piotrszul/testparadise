package name.piotrszul.paradise.impl.neo4j

import org.neo4j.driver.v1.StatementResult
import name.piotrszul.paradise.domain.Path
import name.piotrszul.paradise.domain.Entity
import org.neo4j.driver.v1.Record

case object ResultToPath extends Function1[Record, Path] {
  def apply(result: Record):Path = null
}


case object ResultToEntity extends Function1[Record, Entity] {
  def apply(record: Record):Entity = {
    val node = record.get(0).asNode()
    Entity(node.id, node.get("name").asString())
  }
}