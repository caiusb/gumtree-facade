package com.brindescu.gumtree.c

import java.io.Reader

import com.brindescu.cdtfacade.CParser
import com.github.gumtreediff.gen.TreeGenerator
import com.github.gumtreediff.tree.TreeContext

object CTreeGenerator extends TreeGenerator {

	override def generate(reader: Reader): TreeContext = {
		val contents = Stream.continually(reader.read).takeWhile(_ != -1).map(_.toChar).toArray
		val visitor = new CVisitor
		CParser.parse(new String(contents)).accept(visitor)
		visitor.getContext
	}
}
