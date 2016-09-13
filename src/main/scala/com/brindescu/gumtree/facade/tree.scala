package com.brindescu.gumtree.facade

import scala.collection.mutable.ListBuffer

abstract class SuperTree {
	def getLineNumber(): Int

	def getSourceRange(): List[Int]

	def getParent(): Option[SuperTree]

	def getChildren(): List[SuperTree]

	def getUnderlyingNode(): Any

	def listAllNodes(): List[SuperTree] = {
		val builder = new ListBuffer[SuperTree]
		builder.append(this)
		getChildren.foreach { c => builder.appendAll(c.listAllNodes) }
		builder.toList
	}

	def getEnclosingClass(): Option[SuperTree]

	def getEnclosingMethod(): Option[SuperTree]

	def getNodeType(): String
}

trait Named {
	def getIdentifier(): String
}

trait SuperStatement extends SuperTree

trait SuperBlock extends SuperTree {
	def getBlockInfo(): String
}

trait SuperMethod extends SuperTree with Named

trait SuperClass extends SuperTree with Named