package com.brindescu.gumtree.jdt

import com.brindescu.gumtree.facade.SuperTree
import org.eclipse.jdt.core.dom.{ASTNode, CompilationUnit}

class JavaTree(n: ASTNode) extends SuperTree {

	override def getLineNumber(): Int =
		n.getRoot.asInstanceOf[CompilationUnit].getLineNumber(n.getStartPosition)

	override def getParent(): SuperTree =
		JavaTree(n.getParent)

	def getUnderlyingNode(): ASTNode = n

	override def equals(other: Any): Boolean =
		other match {
			case JavaTree(o) => o == n
			case o: ASTNode => o == n
			case _ => false
		}
}


object JavaTree {
	def apply(n: ASTNode) = new JavaTree(n)
	def unapply(t: JavaTree): Option[ASTNode] = Some(t.getUnderlyingNode)
}