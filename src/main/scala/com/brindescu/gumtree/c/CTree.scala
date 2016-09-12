package com.brindescu.gumtree.c

import com.brindescu.gumtree.facade.SuperTree
import org.eclipse.cdt.core.dom.ast.IASTNode

class CTree(n: IASTNode) extends SuperTree {

	override def getLineNumber(): Int =
		n.getFileLocation.getStartingLineNumber

	override def getSourceRange(): List[Int] = ???

	override def getParent(): SuperTree =
		CTree(n.getParent)

	override def getChildren(): List[SuperTree] = ???

	override def getUnderlyingNode(): IASTNode = n

	override def getEnclosingClass(): Option[SuperTree] = ???

	override def getNodeType(): String = ???

	override def getEnclosingMethod(): Option[SuperTree] = ???

	override def equals(other: Any): Boolean =
		other match {
			case CTree(o) => o == n
			case o: IASTNode => o == n
			case _ => false
		}

	override def hashCode(): Int = n.hashCode()
}

object CTree {
	def apply(n: IASTNode) = new CTree(n)
	def unapply(t: CTree): Option[IASTNode] = Some(t.getUnderlyingNode)
}
