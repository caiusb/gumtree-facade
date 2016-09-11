package com.brindescu.gumtree.c

import org.scalatest.{FlatSpec, Matchers}

class CGeneratorTest extends FlatSpec with Matchers {

	it should "not fail" in {
		val tree = CTreeGenerator.generateFromString("void main(){}")
		tree should not be null
		tree.getRoot.getSize should be (6)
	}

	it should "work with multiple functions" in {
		val tree = CTreeGenerator.generateFromString("void main(){}\n\nvoid m(){}")
		tree should not be null
		tree.getRoot.getSize should be (11)
	}

	it should "work with include statements" in {
		val tree = CTreeGenerator.generateFromString("#include <stdio.h>\n\nvoid main(){\n\tprintf(\"Hello, world!\\n\")\n}")
		tree should not be null
		tree.getRoot.getSize should be (13)
	}
}
