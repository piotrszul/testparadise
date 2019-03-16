package name.piotrszul.paradise.impl.neo4j

import org.neo4j.driver.v1.StatementResult

trait Neo4jQueryTemplate {
  def querySingle[T](cypherQuery: String, params: Map[String,Any], resultMapper: StatementResult=>T): T  
  def querySingleRaw[T](cypherQuery: String, params: Map[String,Any]): StatementResult  
}
