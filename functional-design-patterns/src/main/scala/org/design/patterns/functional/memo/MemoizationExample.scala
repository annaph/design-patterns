package org.design.patterns.functional.memo

object MemoizationExample extends App {

  val hasher = new Hasher

  System.out.println(s"MD5 for 'hello' is '${hasher memoMd5 "hello"}'.")
  System.out.println(s"MD5 for 'bye' is '${hasher memoMd5 "bye"}'.")
  System.out.println(s"MD5 for 'hello' is '${hasher memoMd5 "hello"}'.")
  System.out.println(s"MD5 for 'bye1' is '${hasher memoMd5 "bye1"}'.")
  System.out.println(s"MD5 for 'bye' is '${hasher memoMd5 "bye"}'.")

  System.out.println("Using Scalaz Memo:")
  System.out.println(s"MD5 for 'hello' is '${hasher memoMd5Scalaz "hello"}'.")
  System.out.println(s"MD5 for 'bye' is '${hasher memoMd5Scalaz "bye"}'.")
  System.out.println(s"MD5 for 'hello' is '${hasher memoMd5Scalaz "hello"}'.")
  System.out.println(s"MD5 for 'bye1' is '${hasher memoMd5Scalaz "bye1"}'.")
  System.out.println(s"MD5 for 'bye' is '${hasher memoMd5Scalaz "bye"}'.")

}
