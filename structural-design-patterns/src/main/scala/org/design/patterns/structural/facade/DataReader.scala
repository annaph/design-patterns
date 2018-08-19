package org.design.patterns.structural.facade

import org.design.patterns.structural.facade.model.Person

class DataReader extends DataDownloader with DataDecoder with DataDeserialzer {

  def readPerson(url: String): Person = {
    val data = download(url)
    val jsonStr = decode(data)

    parse[Person](jsonStr)
  }

}

object FacadeExample extends App {

  val reader = new DataReader

  System.out.println(s"We just read the following person: ${reader.readPerson("http://www.ivan-nikolov.com/")}")

}
