package com.brindescu.gumtree.jdt

import java.io.Reader

import com.brindescu.jdtfacade.Parser
import com.github.gumtreediff.gen.TreeGenerator

object JDTGenerator extends TreeGenerator {

  override def generate(r: Reader) = {
    val v = new Visitor
    Parser.parse(Stream.continually(r.read).takeWhile(_ != -1).map(_.toChar).toArray).accept(v)
    v.getTreeContext
  }
}
