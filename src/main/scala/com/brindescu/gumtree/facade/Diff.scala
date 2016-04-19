package com.brindescu.gumtree.facade

import com.github.gumtreediff.actions.model.Action
import com.github.gumtreediff.matchers.MappingStore
import com.github.gumtreediff.tree.ITree

class Diff(private val actions: List[Action],
           private val matchings: MappingStore,
           private val leftTree: ITree,
           private val rightTree: ITree) {

  def getActions: List[Action] = actions

  def getMatch(node: ITree): Option[ITree] = {
    if (matchings.hasDst(node))
      Some(matchings.getSrc(node))
    else if (matchings.hasSrc(node))
      Some(matchings.getDst(node))
    else
      None
  }

  def getLeftTree(): ITree = leftTree

  def getRightTree(): ITree = rightTree
}
