package com.brindescu.gumtree.facade

abstract class SuperTree extends Traversable[SuperTree] {
	def getLineNumber(): Int

	def getSourceRange(): List[Int]

	def getParent(): SuperTree

	def getChildren(): List[SuperTree]

	override def foreach[U](f: SuperTree => U) = {
		f(this)
		getChildren.foreach(f)
	}
}

trait SuperStatement extends SuperTree
trait SuperBlock extends SuperTree
trait SuperMethod extends SuperTree