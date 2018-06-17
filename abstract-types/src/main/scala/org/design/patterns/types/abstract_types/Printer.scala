package org.design.patterns.types.abstract_types

abstract class PrintData

abstract class PrintMaterial

abstract class PrintMedia

case class Text() extends PrintData

case class Model() extends PrintData

case class Toner() extends PrintMaterial

case class Plastic() extends PrintMaterial

case class Paper() extends PrintMedia

case class Air() extends PrintMedia

trait Printer {
  type Data <: PrintData
  type Material <: PrintMaterial
  type Media <: PrintMedia

  def print(data: Data, material: Material, media: Media): String =
    s"Printing $data with $material material on $media media."

}

trait GenericPrinter[Data <: PrintData, Material <: PrintMaterial, Media <: PrintMedia] {

  def print(data: Data, material: Material, media: Media) =
    s"Printing $data with $material material on $media media."

}

class LaserPrinter extends Printer {
  type Data = Text
  type Material = Toner
  type Media = Paper
}

class ThreeDPrinter extends Printer {
  type Data = Model
  type Material = Plastic
  type Media = Air
}

class GenericLaserPrinter[Data <: Text, Material <: Toner, Media <: Paper]
  extends GenericPrinter[Data, Material, Media]

class GenericThreeDPrinter[Data <: Model, Material <: Plastic, Media <: Air]
  extends GenericPrinter[Data, Material, Media]

class GenericPrinterImpl[Data <: PrintData, Material <: PrintMaterial, Media <: PrintMedia]
  extends GenericPrinter[Data, Material, Media]

object PrinterExample extends App {

  val laser = new LaserPrinter
  val threeD = new ThreeDPrinter

  System.out.println(laser print(Text(), Toner(), Paper()))
  System.out.println(threeD print(Model(), Plastic(), Air()))

  val genericLaser = new GenericLaserPrinter[Text, Toner, Paper]
  val genericThreeD = new GenericThreeDPrinter[Model, Plastic, Air]

  System.out.println(genericLaser print(Text(), Toner(), Paper()))
  System.out.println(genericThreeD print(Model(), Plastic(), Air()))

  val wrongPrinter = new GenericPrinterImpl[Model, Toner, Air]

  System.out.println(wrongPrinter print(Model(), Toner(), Air()))

}
