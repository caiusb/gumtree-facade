package com.brindescu.gumtree.jdt

import com.github.gumtreediff.gen.jdt.{AbstractJdtTreeGenerator, AbstractJdtVisitor}

object JDTGenerator extends AbstractJdtTreeGenerator {
  override def createVisitor(): AbstractJdtVisitor = new Visitor
}
