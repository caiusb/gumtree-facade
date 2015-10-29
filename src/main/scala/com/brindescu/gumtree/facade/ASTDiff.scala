package com.brindescu.gumtree.facade

import com.brindescu.gumtree.jdt.JDTGenerator
import com.github.gumtreediff.actions.ActionGenerator
import com.github.gumtreediff.matchers.Matchers
import com.github.gumtreediff.tree.ITree
import org.eclipse.jdt.core.dom.ASTNode

import scala.collection.JavaConversions

object ASTDiff {

  private[gumtree] val containedNode = "CONTAINED"

  def getDiff(a: String, b:String): Diff = {
    val aTree = JDTGenerator.generateFromString(a).getRoot()
    val bTree = JDTGenerator.generateFromString(b).getRoot()
    val matcher = Matchers.getInstance().getMatcher(aTree, bTree)
    matcher.`match`()
    val gen = new ActionGenerator(aTree, bTree, matcher.getMappings)
    val actions = JavaConversions.asScalaBuffer(gen.generate()).toList
    new Diff(actions, matcher.getMappings, aTree, bTree)
  }

  def getASTNode(tree: ITree): ASTNode = tree.getMetadata(containedNode).asInstanceOf[ASTNode]
}
