package com.brindescu.gumtree.facade

import com.github.gumtreediff.actions.model.Action
import com.github.gumtreediff.tree.ITree

object Gumtree {

	implicit def wrapTree(t: ITree) = new RichTree(t)
	implicit def wrapAction(a: Action) = new RichAction(a)
}

import Gumtree._

class RichTree(tree: ITree) {

	def getASTNode = tree.getMetadata("CONTAINED")
}

class RichAction(action: Action) {

	def getASTNode = action.getNode.getASTNode
}