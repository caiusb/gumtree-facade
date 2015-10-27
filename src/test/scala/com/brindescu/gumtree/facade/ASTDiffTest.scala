package com.brindescu.gumtree.facade

import org.scalatest.{Matchers, FlatSpec}

/**
 * Created by caius on 10/26/15.
 */
class ASTDiffTest extends FlatSpec with Matchers {

  private val aContent = "public class A{public void m(){}}"
  private val bContent = "public class A{public void n(){}}"

  it should "find the diff" in {
    val diff = ASTDiff.getDiff(aContent, bContent)
    diff.getActions should have size 1
  }

}
