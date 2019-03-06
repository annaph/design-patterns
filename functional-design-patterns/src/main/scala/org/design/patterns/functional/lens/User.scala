package org.design.patterns.functional.lens

import scalaz.Lens

case class Country(name: String, code: String)

case class City(name: String, country: Country)

case class Address(number: Int, street: String, city: City)

case class Company(name: String, address: Address)

case class User(name: String, company: Company, address: Address)

object User {

  val userCompany: Lens[User, Company] = Lens.lensu(
    (user, company) => user copy (company = company),
    _.company
  )

  val userAddress: Lens[User, Address] = Lens.lensu(
    (user, address) => user copy (address = address),
    _.address
  )

  val companyAddress: Lens[Company, Address] = Lens.lensu(
    (company, address) => company copy (address = address),
    _.address
  )

  val addressCity: Lens[Address, City] = Lens.lensu(
    (address, city) => address copy (city = city),
    _.city
  )

  val cityCountry: Lens[City, Country] = Lens.lensu(
    (city, country) => city copy (country = country),
    _.country
  )

  val countryCode: Lens[Country, String] = Lens.lensu(
    (country, code) => country copy (code = code),
    _.code
  )

  val userCompanyCountryCode: Lens[User, String] =
    userCompany >=> companyAddress >=> addressCity >=> cityCountry >=> countryCode

}

object UserVerboseExample extends App {

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

  val annaFixed = anna.copy(
    company = anna.company.copy(
      address = anna.company.address.copy(
        city = anna.company.address.city.copy(
          country = anna.company.address.city.country.copy(
            code = anna.company.address.city.country.code.toUpperCase)))))

  System.out.println(annaFixed)

}

object UserLensExample extends App {

  import User.userCompanyCountryCode

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

  val annaFixed = userCompanyCountryCode mod(_.toUpperCase, anna)

  System.out.println(annaFixed)

}
