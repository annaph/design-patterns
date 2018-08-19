package org.design.patterns.structural.facade

import org.json4s.jackson.JsonMethods
import org.json4s.{DefaultFormats, StringInput}

trait DataDeserialzer {

  implicit val formats: DefaultFormats = DefaultFormats

  def parse[T](data: String)(implicit m: Manifest[T]): T =
    JsonMethods.parse(StringInput(data)).extract[T]

}
