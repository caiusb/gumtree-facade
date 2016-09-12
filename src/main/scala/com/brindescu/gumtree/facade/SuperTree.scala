package com.brindescu.gumtree.facade

trait SuperTree {
	def getLineNumber(): Int

	def getParent(): SuperTree
}
