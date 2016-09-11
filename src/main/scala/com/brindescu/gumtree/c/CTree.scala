package com.brindescu.gumtree.c

import com.brindescu.gumtree.facade.SuperTree
import org.eclipse.cdt.core.dom.ast.IASTNode

class CTree(n: IASTNode) extends SuperTree {

	override def getLineNumber(): Int =
		n.getFileLocation.getStartingLineNumber
}

object CTree {
	def apply(n: IASTNode) = new CTree(n)
}
