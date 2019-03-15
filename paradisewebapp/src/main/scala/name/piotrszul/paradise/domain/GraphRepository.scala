package name.piotrszul.paradise.domain

trait GraphRepository {
 
    def getEntity(id:Int):Entity
    def getShortedPath(startId:Int, endId:Int):Path
}