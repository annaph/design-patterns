package org.design.patterns.structural.bridge.common

import java.security.MessageDigest

trait Hasher {

  def hash(data: String): String

  protected def messageDigest(algorithm: String, data: String): MessageDigest = {
    val crypt = MessageDigest.getInstance(algorithm)

    crypt.reset()
    crypt.update(data.getBytes("UTF-8"))
    crypt
  }

}
