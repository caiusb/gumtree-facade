package com.brindescu.gumtree.facade

import com.brindescu.gumtree.facade.ASTDiff._
import com.github.gumtreediff.tree.ITree
import org.scalatest.{FlatSpec, Matchers}

import scala.collection.JavaConversions._

class DiffTest extends FlatSpec with Matchers {

  private val diff = getDiff("public class A{}", "public class A{}")

  it should "have the correct matchings, left to right, for the null diff" in {
    processTree(diff.getLeftTree)
  }

  it should "have the correct matchings, right to left, for the null diff" in {
    processTree(diff.getRightTree)
  }

  it should "not match a deleted node" in {
    val diff = getDiff("public class A{public void m(){}}", "public class A{}")
    val tree = diff.getLeftTree()
  }

  private def processTree(n: ITree): Any = {
    val matchedNode = diff.getMatch(n)
    assertNodeEquality(n, matchedNode.get)
    asScalaBuffer(n.getChildren).foreach(n => processTree(n))
  }

  private def assertNodeEquality(a: ITree, b: ITree): Unit = {
    getASTNode(b).getClass should equal(getASTNode(a).getClass)
    getASTNode(b).getNodeType should equal(getASTNode(a).getNodeType)
    getASTNode(b).getStartPosition should equal(getASTNode(a).getStartPosition)
    getASTNode(b).getLength should equal(getASTNode(a).getLength)
  }
}