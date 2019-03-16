package name.piotrszul.paradise.misc

import org.neo4j.driver.v1.GraphDatabase
import org.neo4j.driver.v1.AuthTokens
import org.neo4j.driver.v1.types.Node

import collection.JavaConverters._

object TestClient {
  def main(args: Array[String]) {
    val driver = GraphDatabase.driver("bolt://localhost:7687", 
        AuthTokens.basic( "neo4j", "maggie26"))
        
    val session = driver.session()
    val params:Map[String,Any] =  Map("id" -> 9)
    val result = session.run("MATCH (p) WHERE ID(p) = $id RETURN p",
          params.mapValues(_.asInstanceOf[Object]).asJava)
    println(result)
    val recored = result.single()
    println(recored)
    val value = recored.get("p")
    println(value)
    val node = value.asNode()
    println(node)
    println(node.asMap())
    
    session.close()   
    driver.close();
  }
}