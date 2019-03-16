package name.piotrszul.paradise.impl.neo4j

import org.neo4j.driver.v1.Driver
import org.neo4j.driver.v1.Session
import org.neo4j.driver.v1.StatementResult

class DriverNeo4jQueryTemplate(driver:Driver) extends Neo4jQueryTemplate {
  def querySingle[T](cypherQuery: String, params: Any, resultMapper: StatementResult=>T): T = {
    var session:Session = null
    try {
      session = driver.session()
      resultMapper(session.run(cypherQuery))
    } finally {
      session.close()
    }
  }
}