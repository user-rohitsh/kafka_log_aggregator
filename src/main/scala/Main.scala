import java.util.Properties
import java.util.concurrent.TimeUnit

import org.apache.kafka.streams.scala._
import org.apache.kafka.streams.scala.kstream._
import org.apache.kafka.streams.scala.ImplicitConversions._
import config._
import org.apache.kafka.streams.{KafkaStreams, StreamsConfig}


object Main extends App {

  import Serdes._
  val props: Properties = {
    val p = new Properties()
    p.put(StreamsConfig.APPLICATION_ID_CONFIG, MyConf.conf.appId)
    p.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, MyConf.conf.bootstrapServers)
    p
  }

  //create topology
  val builder:StreamsBuilder = new StreamsBuilder()
  val logLines: KStream[String,String] = builder.stream[String,String](MyConf.conf.topics)

  //filter messages
  val filteredLine:KStream[String,String] = logLines.filter {
    (_, line) =>
      MyConf.conf.filter.findFirstMatchIn(line) match {
        case Some(_) => true
        case None => false
      }
  }
  //filteredLine.to(MyConf.conf.filteredTopic)
  logLines.foreach( (_,line) => print(line))
  //create and start the stream
  val streams: KafkaStreams = new KafkaStreams(builder.build(), props)
  streams.start()


  //Shutdown hook
  sys.ShutdownHookThread {
    streams.close(10, TimeUnit.SECONDS)
  }
}
