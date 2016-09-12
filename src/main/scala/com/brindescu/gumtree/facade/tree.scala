package com.brindescu.gumtree.facade

abstract class SuperTree {
	def getLineNumber(): Int

	def getSourceRange(): List[Int]

	def getParent(): SuperTree

	def getChildren(): List[SuperTree]
}

trait SuperStatement extends SuperTree
trait SuperBlock extends SuperTree
trait SuperMethod extends SuperTree