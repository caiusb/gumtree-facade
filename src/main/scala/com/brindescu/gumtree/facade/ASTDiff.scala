package com.brindescu.gumtree.facade

import com.github.gumtreediff.actions.ActionGenerator
import com.github.gumtreediff.gen.jdt.JdtTreeGenerator
import com.github.gumtreediff.matchers.Matchers

import scala.collection.JavaConversions

object ASTDiff {

  def getDiff(a: String, b:String): Diff = {
    val gen = new JdtTreeGenerator()
    val aTree = gen.generateFromString(a).getRoot()
    val bTree = gen.generateFromString(b).getRoot()
    val matcher = Matchers.getInstance().getMatcher(aTree, bTree)
    matcher.`match`()
    val actionsGenerator = new ActionGenerator(aTree, bTree, matcher.getMappings)
    val actions = JavaConversions.asScalaBuffer(actionsGenerator.generate()).toList
    new Diff(actions, matcher.getMappings)
  }

}
