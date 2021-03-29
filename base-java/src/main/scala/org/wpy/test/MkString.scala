package org.wpy.test

import org.scalatest.funsuite.AnyFunSuite

class MkString extends AnyFunSuite{

  test("mkString"){
    val seq = Seq("aaa","bbb","ccc","dddd","eee")
    println(seq.mkString(","))
    println(seq.reduce(_+","+_))
  }
}
