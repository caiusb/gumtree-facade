package com.brindescu.gumtree.jdt

import com.brindescu.gumtree.facade._
import org.eclipse.jdt.core.dom._
import com.brindescu.jdtfacade.Facade._

class JavaTree(n: ASTNode) extends SuperTree {

	override def getLineNumber(): Int =
		n.getRoot.asInstanceOf[CompilationUnit].getLineNumber(n.getStartPosition)

	override def getSourceRange(): List[Int] = {
		val root = n.getRoot.asInstanceOf[CompilationUnit]
		val s = root.getLineNumber(n.getStartPosition)
		val e = root.getLineNumber(n.getStartPosition + n.getLength)
		Range(s, e+1).toList
	}

	override def getParent(): Option[SuperTree] =
		n.getParent match {
			case x if x != null => Some(JavaTree(x))
			case _ => None
		}

	override def getChildren(): List[SuperTree] =
		n.getChildren.map{JavaTree(_)}

	override def getUnderlyingNode(): ASTNode = n

	override def getEnclosingClass(): Option[SuperTree] = {
		n match {
			case x: TypeDeclaration => Some(JavaTree(x))
			case x: ASTNode => getParent match {
				case Some(x) => x.getEnclosingClass
				case None => None
			}
			case _ => None
		}
	}

	override def getEnclosingMethod(): Option[SuperTree] = {
		n match {
			case x : MethodDeclaration => Some(JavaTree(x))
			case x : ASTNode => getParent match {
				case Some(x) => x.getEnclosingMethod
				case None => None
			}
			case _ => None
		}
	}

	override def getNodeType(): String =
		n.getClass.getSimpleName

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
		case t: TypeDeclaration => new JavaClass(t)
		case _ => new JavaTree(n)
	}
	def unapply(t: JavaTree): Option[ASTNode] = Some(t.getUnderlyingNode)
}

class JavaStatement(s: Statement) extends JavaTree(s) with SuperStatement

class JavaBlock(b: Block) extends JavaTree(b) with SuperBlock {
	override def getBlockInfo(): String = {
		val root = b.getRoot.asInstanceOf[CompilationUnit]
		val t = b.getParent match {
			case _: IfStatement => "if("
			case _: ForStatement => "for("
			case _: EnhancedForStatement => "for("
			case _: WhileStatement => "while("
			case _: DoStatement => "do("
			case _: MethodDeclaration => "method("
			case _: TryStatement => "try("
			case _: CatchClause => "catch("
			case _: Initializer => "static("
			case _ => "other("
		}
		t + getSourceRange.min + ":" + getSourceRange.max + "),"
	}
}

class JavaMethod(m: MethodDeclaration) extends JavaTree(m) with SuperMethod {
	override def getIdentifier(): String = m.getName.getIdentifier
}

class JavaClass(t: TypeDeclaration) extends JavaTree(t) with SuperClass {
	override def getIdentifier(): String = t.getName.getIdentifier
}