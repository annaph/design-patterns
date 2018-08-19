package org.design.patterns.structural.bridge.scala

import org.apache.commons.codec.binary.Hex
import org.design.patterns.structural.bridge.common.Hasher

trait Sha1Hasher extends Hasher {

  override def hash(data: String): String =
    new String(Hex.encodeHex(messageDigest("SHA-1", data).digest()))

}

trait Sha256Hasher extends Hasher {

  override def hash(data: String): String =
    new String(Hex.encodeHex(messageDigest("SHA-256", data).digest()))

}

trait Md5Hasher extends Hasher {

  override def hash(data: String): String =
    new String(Hex.encodeHex(messageDigest("MD5", data).digest()))

}
