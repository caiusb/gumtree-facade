package com.brindescu.gumtree.c

import java.io.Reader

import com.github.gumtreediff.gen.TreeGenerator
import com.github.gumtreediff.tree.TreeContext
import org.eclipse.cdt.core.dom.ast.gnu.c.GCCLanguage
import org.eclipse.cdt.core.model.ILanguage
import org.eclipse.cdt.core.parser.{FileContent, IncludeFileContentProvider, ParserFactory, ScannerInfo}
import org.eclipse.cdt.internal.core.index.EmptyCIndex

object CTreeGenerator extends TreeGenerator {

	override def generate(reader: Reader): TreeContext = {
		val contents = Stream.continually(reader.read()).takeWhile(_ != -1).map(_.toChar).toArray
		val language = GCCLanguage.getDefault
		val fileContent = FileContent.create("temp.c", contents)
		val ast = language.getASTTranslationUnit(fileContent, new ScannerInfo(), IncludeFileContentProvider.getEmptyFilesProvider,
			EmptyCIndex.INSTANCE, ILanguage.OPTION_NO_IMAGE_LOCATIONS, ParserFactory.createDefaultLogService())
		val visitor = new CVisitor()
		ast.accept(visitor)
		visitor.getContext
	}
}
