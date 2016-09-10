package com.brindescu.gumtree.jdt

import java.io.Reader
import java.util

import com.github.gumtreediff.gen.TreeGenerator
import org.eclipse.jdt.core.JavaCore
import org.eclipse.jdt.core.dom.{AST, ASTParser}

object JDTGenerator extends TreeGenerator {

  override def generate(r: Reader) = {
    val parser = ASTParser.newParser(AST.JLS8)
    parser.setKind(ASTParser.K_COMPILATION_UNIT)
    parser.setBindingsRecovery(true)
    parser.setResolveBindings(true)
    val pOptions = JavaCore.getOptions.asInstanceOf[util.Hashtable[String, String]]
    pOptions.put(JavaCore.COMPILER_COMPLIANCE, JavaCore.VERSION_1_8)
    pOptions.put(JavaCore.COMPILER_CODEGEN_TARGET_PLATFORM, JavaCore.VERSION_1_8)
    pOptions.put(JavaCore.COMPILER_SOURCE, JavaCore.VERSION_1_8)
    parser.setCompilerOptions(pOptions)
    parser.setSource(Stream.continually(r.read).takeWhile(_ != -1).map(_.toChar).toArray)
    val v = new Visitor
    parser.createAST(null).accept(v)
    v.getTreeContext
  }
}
