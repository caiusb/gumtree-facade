package com.brindescu.gumtree.c

import com.github.gumtreediff.tree.{ITree, TreeContext}
import org.eclipse.cdt.core.dom.ast.{ASTGenericVisitor, ASTVisitor, IASTNode}

import scala.collection.mutable

class CVisitor extends ASTGenericVisitor(true) {

	private val stack = new mutable.Stack[ITree]()
	private val context = new TreeContext()

	override def genericVisit(n: IASTNode): Int = {
		val label = n.getRawSignature
		val pos = n.getSyntax.getOffset
		val length = n.getSyntax.getLength
		val t = n.getClass.hashCode

		val tree = context.createTree(t, label, n.getClass.getSimpleName)
		tree.setPos(pos)
		tree.setLength(length)
		if (stack.isEmpty)
			context.setRoot(tree)
		else
			tree.setParentAndUpdateChildren(stack.top)
		stack.push(tree)
		return ASTVisitor.PROCESS_CONTINUE
	}

	override def genericLeave(n: IASTNode):Int = {
		stack.pop().setMetadata("CONTAINED", CTree(n))
		return ASTVisitor.PROCESS_CONTINUE
	}

	def getContext() = {
		context.validate
		context
	}
}
