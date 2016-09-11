package com.brindescu.gumtree.jdt

import com.brindescu.gumtree.facade.SuperTree
import org.eclipse.jdt.core.dom.{ASTNode, CompilationUnit}

class JavaTree(n: ASTNode) extends SuperTree {
	override def getLineNumber(): Int =
		n.getRoot.asInstanceOf[CompilationUnit].getLineNumber(n.getStartPosition)

	def getUnderlyingNode(): ASTNode = n
}


object JavaTree {
	def apply(n: ASTNode) = new JavaTree(n)
}