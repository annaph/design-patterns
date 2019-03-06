package org.design.patterns.functional.lens.bad

case class Country(var name: String, var code: String)

case class City(var name: String, var country: Country)

case class Address(var number: Int, var street: String, var city: City)

case class Company(var name: String, var address: Address)

case class User(var name: String, var company: Company, var address: Address)

object UserBadExample extends App {

  val uk = Country("United Kingdom", "uk")
  val london = City("London", uk)
  val buckinghamPalace = Address(1, "Buckingham Palace Road", london)
  val castleBuilders = Company("Castle Builders", buckinghamPalace)

  val switzerland = Country("Switzerland", "CH")
  val geneva = City("Geneva", switzerland)
  val genevaAddress = Address(1, "Geneva Lake", geneva)

  val anna = User("Anna", castleBuilders, genevaAddress)
  System.out.println(anna)

  System.out.println("Capitalize UK code...")

  anna.company.address.city.country.code = anna.company.address.city.country.code.toUpperCase

  System.out.println(anna)

}
