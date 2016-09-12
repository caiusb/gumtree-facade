package com.brindescu.gumtree.jdt

import com.brindescu.gumtree.facade.{SuperBlock, SuperMethod, SuperStatement, SuperTree}
import org.eclipse.jdt.core.dom._

import com.brindescu.jdtfacade.Facade._

class JavaTree(n: ASTNode) extends SuperTree {

	override def getLineNumber(): Int =
		n.getRoot.asInstanceOf[CompilationUnit].getLineNumber(n.getStartPosition)

	override def getParent(): SuperTree =
		JavaTree(n.getParent)

	override def getChildren(): List[SuperTree] =
		n.getChildren.map{JavaTree(_)}

	def getUnderlyingNode(): ASTNode = n

	override def equals(other: Any): Boolean =
		other match {
			case JavaTree(o) => o == n
			case o: ASTNode => o == n
			case _ => false
		}

	override def hashCode() = n.hashCode
}

object JavaTree {
	def apply(n: ASTNode): JavaTree = n match {
		case b: Block => new JavaBlock(b)
		case s: Statement => new JavaStatement(s)
		case m: MethodDeclaration => new JavaMethod(m)
		case _ => new JavaTree(n)
	}
	def unapply(t: JavaTree): Option[ASTNode] = Some(t.getUnderlyingNode)
}

class JavaStatement(s: Statement) extends JavaTree(s) with SuperStatement
class JavaBlock(b: Block) extends JavaTree(b) with SuperBlock
class JavaMethod(m: MethodDeclaration) extends JavaTree(m) with SuperMethod