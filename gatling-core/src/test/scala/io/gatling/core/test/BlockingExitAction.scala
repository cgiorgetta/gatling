/*
 * Copyright 2011-2019 GatlingCorp (https://gatling.io)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.gatling.core.test

import java.util.concurrent.{ CountDownLatch, TimeUnit }

import scala.concurrent.duration.Duration

import io.gatling.core.action.Action
import io.gatling.core.session.Session

private[test] class BlockingExitAction(latchSize: Int = 1) extends Action {

  private val latch = new CountDownLatch(latchSize)

  override def name: String = "exit"

  override def execute(session: Session): Unit = latch.countDown()

  def await(duration: Duration): Unit = latch.await(duration.toMillis, TimeUnit.MILLISECONDS)
}
