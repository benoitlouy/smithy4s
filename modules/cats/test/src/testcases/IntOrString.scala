/*
 *  Copyright 2021-2022 Disney Streaming
 *
 *  Licensed under the Tomorrow Open Source Technology License, Version 1.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *     https://disneystreaming.github.io/TOST-1.0.txt
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package smithy4s.interopcats.testcases

import smithy4s.schema.Schema._
import smithy4s.schema.Schema
import smithy4s.ShapeId

sealed trait IntOrString

object IntOrString {
  case class IntValue(value: Int) extends IntOrString

  case class StringValue(value: String) extends IntOrString

  val schema: Schema[IntOrString] = {
    val intValue = int.oneOf[IntOrString]("intValue", IntValue(_))
    val stringValue = string.oneOf[IntOrString]("stringValue", StringValue(_))
    union(intValue, stringValue) {
      case IntValue(int)       => intValue(int)
      case StringValue(string) => stringValue(string)
    }.withId(ShapeId("", "IntOrString"))
  }
}
