package config
import scala.util.matching._

case class MyStreamConfig(
      appId:String ,
      bootstrapServers:String,
      nThreads:Int,
      topics:Set[String],
      filter:Regex,
      filteredTopic:String
)

//Change this to run
object MyConf {
  val conf = MyStreamConfig(
    appId="LogAggregator",
    bootstrapServers="localhost:9092",
    nThreads=1,
    topics=Set("LogTopic1","LogTopic2"),
    filter=new Regex("Thread"),
    filteredTopic= "Filter1_topic"
  )
}

