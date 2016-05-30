package com.brindescu.gumtree.facade

import com.github.gumtreediff.tree.ITree

class Gumtree {

	implicit def wrapTree(t: ITree) = new RichTree(t)
}

class RichTree(tree: ITree) {

	def getNode = ASTDiff.getASTNode(tree)
}