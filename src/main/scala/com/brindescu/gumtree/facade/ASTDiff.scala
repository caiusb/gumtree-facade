package com.brindescu.gumtree.facade

import com.brindescu.gumtree.c.CTreeGenerator
import com.brindescu.gumtree.jdt.JDTGenerator
import com.github.gumtreediff.actions.ActionGenerator
import com.github.gumtreediff.gen.TreeGenerator
import com.github.gumtreediff.matchers.Matchers
import com.github.gumtreediff.tree.ITree
import org.eclipse.jdt.core.dom.ASTNode

import scala.collection.JavaConversions._

abstract class ASTDiff(private val tg: TreeGenerator) {

  def getDiff(a: String, b:String): Diff =
    getDiff(getTree(a), b)

  def getTree(a: String): ITree =
    tg.generateFromString(a).getRoot

  def getDiff(aTree: ITree, b:String): Diff =
    getDiff(aTree, getTree(b))

  def getDiff(a: String, bTree: ITree): Diff =
    getDiff(getTree(a), bTree)

  def getDiff(aTree: ITree, bTree: ITree): Diff = {
    val matcher = Matchers.getInstance().getMatcher(aTree, bTree)
    matcher.`match`()
    val gen = new ActionGenerator(aTree, bTree, matcher.getMappings)
    val actions = gen.generate().toList
    new Diff(actions, matcher.getMappings, aTree, bTree)
  }
}

object JavaASTDiff extends ASTDiff(JDTGenerator)
object CASTDiff extends ASTDiff(CTreeGenerator)