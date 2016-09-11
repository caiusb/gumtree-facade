package com.brindescu.gumtree.jdt

import com.brindescu.gumtree.facade.{ASTDiff, JavaASTDiff}
import com.github.gumtreediff.gen.jdt.JdtVisitor
import org.eclipse.jdt.core.dom.ASTNode

class Visitor extends JdtVisitor {

  override def postVisit(node: ASTNode) = {
    getCurrentParent.setMetadata("CONTAINED", JavaTree(node))
    super.postVisit(node)
  }

}
