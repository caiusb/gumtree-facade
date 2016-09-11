package com.brindescu.gumtree.facade

import com.brindescu.gumtree.c.CTree
import com.brindescu.gumtree.jdt.JavaTree
import com.github.gumtreediff.actions.model.Action
import com.github.gumtreediff.tree.ITree
import org.eclipse.cdt.core.dom.ast.IASTNode
import org.eclipse.jdt.core.dom.ASTNode

object Gumtree {

	implicit def wrapTree(t: ITree) = new RichTree(t)
	implicit def wrapAction(a: Action) = new RichAction(a)
	implicit def wrapJavaAST(a: ASTNode) = new JavaTree(a)
	implicit def wrapCAST(a: IASTNode) = new CTree(a)
}

import com.brindescu.gumtree.facade.Gumtree._

class RichTree(tree: ITree) {

	def getASTNode = tree.getMetadata("CONTAINED")
}

class RichAction(action: Action) {

	def getASTNode = action.getNode.getASTNode
}