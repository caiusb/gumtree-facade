package com.brindescu.gumtree.facade

import scala.collection.mutable.ListBuffer

abstract class SuperTree {
	def getLineNumber(): Int

	def getSourceRange(): List[Int]

	def getParent(): SuperTree

	def getChildren(): List[SuperTree]

	def getUnderlyingNode(): Any

	def listAllNodes(): List[SuperTree] = {
		val builder = new ListBuffer[SuperTree]
		builder.append(this)
		getChildren.foreach { c => builder.appendAll(c.listAllNodes) }
		builder.toList
	}
}

trait SuperStatement extends SuperTree
trait SuperBlock extends SuperTree
trait SuperMethod extends SuperTree