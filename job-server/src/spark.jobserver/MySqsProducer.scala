package spark.jobserver

import akka.camel.Producer

/**
 * Created by hhokanson on 12/9/15.
 */
//For sending messages to Camel endpoints, actors need to mixin the Producer trait
//and implement the endpointUri method.
//This means that sending a message to this actor will route it to SQS!
//The Producer always sends messages asynchronously. Response messages (if
//supported by the configured endpoint) will, by default, be returned to the
//original sender however for SQS this won't work
class MySqsProducer extends Producer {
  override def endpointUri: String = "aws-sqs://abcd-test-hoke?amazonSQSClient=#client"
  override def oneway: Boolean = true
}
