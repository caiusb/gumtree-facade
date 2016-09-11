package com.brindescu.gumtree.facade

import org.scalatest.{Matchers, FlatSpec}

class ASTDiffTest extends FlatSpec with Matchers {

  private val aContent = "public class A{public void m(){}}"
  private val bContent = "public class A{public void n(){}}"

  it should "find the diff" in {
    val diff = JavaASTDiff.getDiff(aContent, bContent)
    diff.getActions should have size 1
  }

}
