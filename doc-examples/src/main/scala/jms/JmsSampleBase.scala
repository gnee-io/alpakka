/*
 * Copyright (C) 2016-2018 Lightbend Inc. <http://www.lightbend.com>
 */

package jms

import javax.jms.ConnectionFactory

import akka.Done
import akka.stream.alpakka.jms.JmsSinkSettings
import akka.stream.alpakka.jms.scaladsl.JmsSink
import akka.stream.scaladsl.{Sink, Source}
import playground.ActorSystemAvailable

import scala.concurrent.Future

class JmsSampleBase extends ActorSystemAvailable {

  def enqueue(connectionFactory: ConnectionFactory)(msgs: String*): Unit = {
    val jmsSink: Sink[String, Future[Done]] =
      JmsSink.textSink(
        JmsSinkSettings(connectionFactory).withQueue("test")
      )
    Source(msgs.toList).runWith(jmsSink)
  }
}
