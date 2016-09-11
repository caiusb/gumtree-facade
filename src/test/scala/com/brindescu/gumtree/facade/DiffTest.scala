package com.brindescu.gumtree.facade

import com.brindescu.gumtree.facade.JavaASTDiff._
import com.brindescu.gumtree.facade.Gumtree._
import com.brindescu.gumtree.jdt.JavaTree
import com.github.gumtreediff.tree.ITree
import org.eclipse.jdt.core.dom.{ASTNode, MethodDeclaration}
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
    val method = tree.getChild(0).getChild(2)
    method.getASTNode.asInstanceOf[JavaTree].getUnderlyingNode shouldBe a [MethodDeclaration]
    diff.getMatch(method) should be (None)
  }

  private def processTree(n: ITree): Any = {
    val matchedNode = diff.getMatch(n)
    assertNodeEquality(n, matchedNode.get)
    n.getChildren.foreach(n => processTree(n))
  }

  private def assertNodeEquality(a: ITree, b: ITree): Unit = {
    val aNode = a.getASTNode.asInstanceOf[JavaTree].getUnderlyingNode()
    val bNode = b.getASTNode.asInstanceOf[JavaTree].getUnderlyingNode()
    bNode.getClass should equal(aNode.getClass)
    bNode.getNodeType should equal(aNode.getNodeType)
    bNode.getStartPosition should equal(aNode.getStartPosition)
    bNode.getLength should equal(aNode.getLength)
  }
}