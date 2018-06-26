package org.design.patterns.aop

import org.design.patterns.aop.model.Person

trait LoggingDataReader extends DataReader {

  abstract override def readData(): List[Person] = {
    val startMillis = System.currentTimeMillis()
    val result = super.readData()
    val time = System.currentTimeMillis() - startMillis
    System.err.println(s"readData took ${time} milliseconds.")

    result
  }

  abstract override def readDataInefficiently(): List[Person] = {
    val startMillis = System.currentTimeMillis()
    val result = super.readDataInefficiently()
    val time = System.currentTimeMillis() - startMillis
    System.err.println(s"readDataInefficiently tokk ${time} milliseconds.")

    result
  }

}
