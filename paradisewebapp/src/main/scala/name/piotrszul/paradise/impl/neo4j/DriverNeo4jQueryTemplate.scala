package name.piotrszul.paradise.impl.neo4j

import org.neo4j.driver.v1.Driver
import org.neo4j.driver.v1.Session
import org.neo4j.driver.v1.StatementResult
import collection.JavaConverters._

class DriverNeo4jQueryTemplate(driver:Driver) extends Neo4jQueryTemplate {
  def querySingle[T](cypherQuery: String, params: Map[String,Any], resultMapper: StatementResult=>T): T = {
    var session:Session = null
    try {
      session = driver.session()
      resultMapper(session.run(cypherQuery, params.mapValues(_.asInstanceOf[Object]).asJava))
    } finally {
      session.close()
    }
  }
  def querySingleRaw[T](cypherQuery: String, params: Map[String,Any]): StatementResult = {
    var session:Session = null
    try {
      session = driver.session()
      session.run(cypherQuery, params.mapValues(_.asInstanceOf[Object]).asJava)
    } finally {
      session.close()
    }
  }
}