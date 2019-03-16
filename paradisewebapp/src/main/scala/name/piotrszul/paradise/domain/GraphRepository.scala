package name.piotrszul.paradise.domain

trait GraphRepository {
 
    def getEntity(id:Int):Option[Entity]
    def getShortedPath(startId:Int, endId:Int):Path
}