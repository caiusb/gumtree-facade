package com.brindescu.gumtree.jdt

import com.brindescu.gumtree.facade.ASTDiff
import com.github.gumtreediff.gen.jdt.JdtVisitor
import org.eclipse.jdt.core.dom.ASTNode

/**
 * Created by caius on 10/28/15.
 */
class Visitor extends JdtVisitor {

  override def postVisit(node: ASTNode) = {
    getCurrentParent.setMetadata(ASTDiff.containedNode, node)
    super.postVisit(node)
  }

}
