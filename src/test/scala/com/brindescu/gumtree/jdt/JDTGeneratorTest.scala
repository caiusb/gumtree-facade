package com.brindescu.gumtree.jdt

import com.github.gumtreediff.tree.ITree
import org.eclipse.jdt.core.dom.ASTNode
import org.scalatest.{Matchers, FlatSpec}

import scala.collection.JavaConversions._

/**
 * Created by caius on 10/28/15.
 */
class JDTGeneratorTest extends FlatSpec with Matchers {

  it should "not fail" in {
    JDTGenerator.generateFromString("public class A").getRoot should not be (null)
  }

  it should "have metadata for all nodes" in {
    val tree = JDTGenerator.generateFromString("public class A").getRoot
    def processNode(node: ITree): Any = {
      val astNode = node.getMetadata("CONTAINED")
      astNode should not be null
      astNode shouldBe an [ASTNode]
      asScalaBuffer(node.getChildren).foreach(n => processNode(n))
    }
    processNode(tree)
  }
}
