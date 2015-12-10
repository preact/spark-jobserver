package spark.jobserver

import akka.camel.{ CamelMessage, Consumer }
import org.json4s.NoTypeHints
import org.json4s.ext.JodaTimeSerializers
import org.json4s.jackson.{Serialization, JsonMethods}
import spark.jobserver.CommonMessages.JobQueued

/**
 * Created by hhokanson on 12/9/15.
 */
class MySqsConsumer extends Consumer {

  //The SQS URI is an in-only message exchange (autoAck=true)
  override def endpointUri: String = "aws-sqs://abcd-test-hoke?amazonSQSClient=#client"

  override def receive: Receive = {


    case msg: CamelMessage => {

      implicit val formats = Serialization.formats(NoTypeHints) ++ JodaTimeSerializers.all

      val body = msg.bodyAs[String]
      println("received message %s" format body)

      val msgObj = JsonMethods.parse(body).extract[JobQueued]

      println("received %s" format msgObj.jobId)
    }

  }

}
