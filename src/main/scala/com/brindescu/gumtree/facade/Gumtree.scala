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
	implicit def wrapJavaAST(a: ASTNode): JavaTree = new JavaTree(a)
	implicit def wrapCAST(a: IASTNode): CTree = new CTree(a)
	implicit def unwrapJavaAST(t: JavaTree) = if (t != null) t.getUnderlyingNode else null
	implicit def unwrapCAST(t: CTree) = if (t != null) t.getUnderlyingNode else null
}

import com.brindescu.gumtree.facade.Gumtree._

class RichTree(tree: ITree) {

	def getASTNode: SuperTree = tree.getMetadata("CONTAINED").asInstanceOf[SuperTree]
}

class RichAction(action: Action) {

	def getASTNode: SuperTree = action.getNode.getASTNode
}