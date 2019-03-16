package name.piotrszul.paradise.impl.neo4j

import org.neo4j.driver.v1.StatementResult
import org.neo4j.driver.v1.Record

trait Neo4jQueryTemplate {
  def querySingle[T](cypherQuery: String, params: Map[String,Any], resultMapper: Record=>T): Option[T]  
}
