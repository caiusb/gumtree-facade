package com.brindescu.gumtree.facade

abstract class SuperTree {
	def getLineNumber(): Int

	def getParent(): SuperTree
}
