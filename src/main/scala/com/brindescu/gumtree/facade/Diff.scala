package com.brindescu.gumtree.facade

import com.github.gumtreediff.actions.model.Action
import com.github.gumtreediff.matchers.MappingStore
import com.github.gumtreediff.tree.{ITree, Tree}

class Diff(private val actions: List[Action],
           private val matchings: MappingStore,
           private val leftTree: ITree,
           private val rightTree: ITree) {

  def getActions: List[Action] = actions

  def getMatch(node: ITree): ITree = {
    if (matchings.hasDst(node))
      matchings.getDst(node)
    else if (matchings.hasSrc(node))
      matchings.getSrc(node)
    else
      null
  }
}
