package org.design.patterns.functional.memo

import java.security.MessageDigest

import org.apache.commons.codec.binary.Hex
import scalaz.Memo

class Hasher extends Memoizer {

  val memoMd5: String => String = memo(md5)

  val memoMd5Scalaz: String => String = Memo.immutableHashMapMemo(md5)

  def md5(input: String): String = {
    System.out.println(s"Calling md5 for $input.")

    val md = MessageDigest getInstance "MD5"
    val hash = md digest input.getBytes

    new String(Hex encodeHex hash)
  }

}
