package com.brindescu.gumtree.c

import com.brindescu.gumtree.facade.{SuperBlock, SuperMethod, SuperStatement, SuperTree}
import org.eclipse.cdt.core.dom.ast.{IASTCompoundStatement, IASTFunctionDefinition, IASTNode, IASTStatement}

class CTree(n: IASTNode) extends SuperTree {

	override def getLineNumber(): Int =
		n.getFileLocation.getStartingLineNumber

	override def getSourceRange(): List[Int] =
		Range(n.getFileLocation.getStartingLineNumber, n.getFileLocation.getEndingLineNumber+1).toList

	override def getParent(): SuperTree =
		CTree(n.getParent)

	override def getChildren(): List[SuperTree] = n.getChildren.map { CTree(_) }.toList

	override def getUnderlyingNode(): IASTNode = n

	override def getEnclosingClass(): Option[SuperTree] = None

	override def getNodeType(): String = n.getClass.getSimpleName

	override def getEnclosingMethod(): Option[SuperTree] = n match {
		case f: IASTFunctionDefinition => Some(CTree(f))
		case n: IASTNode if getParent() != null => Some(CTree(n))
		case _ => None
	}

	override def equals(other: Any): Boolean =
		other match {
			case CTree(o) => o == n
			case o: IASTNode => o == n
			case _ => false
		}

	override def hashCode(): Int = n.hashCode()
}

object CTree {
	def apply(n: IASTNode) = n match {
		case b: IASTCompoundStatement => new CBlock(b)
		case s: IASTStatement => new CStatement(s)
		case m: IASTFunctionDefinition => new CMethod(m)
		case n => new CTree(n)
	}
	def unapply(t: CTree): Option[IASTNode] = Some(t.getUnderlyingNode)
}

class CStatement(s: IASTStatement) extends CTree(s) with SuperStatement

class CMethod(f: IASTFunctionDefinition) extends CTree(f) with SuperMethod {
	override def getIdentifier(): String = f.getDeclarator.getName.toString
}

class CBlock(b: IASTCompoundStatement) extends CTree(b) with SuperBlock {
	override def getBlockInfo(): String = ""
}