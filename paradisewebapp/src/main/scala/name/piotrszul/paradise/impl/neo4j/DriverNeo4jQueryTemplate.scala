package name.piotrszul.paradise.impl.neo4j

import org.neo4j.driver.v1.Driver
import org.neo4j.driver.v1.Session
import org.neo4j.driver.v1.StatementResult
import collection.JavaConverters._
import org.neo4j.driver.v1.Record

class DriverNeo4jQueryTemplate(driver:Driver) extends Neo4jQueryTemplate {
  def querySingle[T](cypherQuery: String, params: Map[String,Any], resultMapper: Record=>T): Option[T] = {
    var session:Session = null
    try {
      session = driver.session()
      val statementResult = session.run(cypherQuery, params.mapValues(_.asInstanceOf[Object]).asJava)
      if (statementResult.hasNext()) {
        Some(resultMapper(statementResult.single()))
      } else {
        None
      }
    } finally {
      session.close()
    }
  }
}