package com.brindescu.gumtree.facade

import com.brindescu.gumtree.jdt.JDTGenerator
import org.scalatest.{FlatSpec, Matchers}

import Gumtree._

class SuperTreeTest extends FlatSpec with Matchers {

	it should "correctly iterate" in {
		val tree = JDTGenerator.generateFromString("public class A{public void m(){}}").getRoot
		tree.getASTNode.listAllNodes() should have size 9
	}
}
