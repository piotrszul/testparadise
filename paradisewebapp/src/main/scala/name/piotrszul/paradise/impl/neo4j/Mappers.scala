package name.piotrszul.paradise.impl.neo4j

import org.neo4j.driver.v1.StatementResult
import name.piotrszul.paradise.domain.Path

case object ResultToPath extends Function1[StatementResult, Path] {
  def apply(result: StatementResult):Path = null
}