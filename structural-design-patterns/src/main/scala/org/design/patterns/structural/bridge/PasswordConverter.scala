package org.design.patterns.structural.bridge

import org.design.patterns.structural.bridge.common.Hasher

abstract class PasswordConverter(hasher: Hasher) {

  def convert(password: String): String

}

class SimplePasswordConverter(hasher: Hasher) extends PasswordConverter(hasher) {

  override def convert(password: String): String =
    hasher hash password

}

class SaltedPasswordConverter(salted: String, hasher: Hasher) extends PasswordConverter(hasher) {

  override def convert(password: String): String =
    hasher hash s"$salted:$password"

}

object BridgeExample extends App {

  val p1 = new SimplePasswordConverter(new Sha256Hasher)
  val p2 = new SimplePasswordConverter(new Md5Hasher)

  val p3 = new SaltedPasswordConverter("8jsdf32T^$%", new Sha1Hasher)
  val p4 = new SaltedPasswordConverter("8jsdf32T^$%", new Sha256Hasher)

  System.out.println(s"'password' in SH-256 is: ${p1 convert "password"}")
  System.out.println(s"'1234567890' in MD5 is: ${p2 convert "1234567890"}")

  System.out.println(s"'password' in salted SHA-1 is: ${p3 convert "password"}")
  System.out.println(s"'password' in salted SHA-256 is: ${p4 convert "password"}")

}
