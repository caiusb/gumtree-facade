package com.brindescu.gumtree.facade

import com.github.gumtreediff.actions.model.Action
import com.github.gumtreediff.matchers.MappingStore
import com.github.gumtreediff.tree.ITree

import scala.collection.JavaConversions._

class Diff(private val actions: List[Action],
           private val matchings: MappingStore,
           private val leftTree: ITree,
           private val rightTree: ITree) {

	// Workaround. GumTree gives the right (dst) tree a fake root. I'm also doing this for the left (src) tree, just in case
	leftTree.setParent(null)
	rightTree.setParent(null)

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

	def getMatchedNodes(): List[(ITree, ITree)] =
		matchings.map { m => (m.getFirst, m.getSecond) }.toList
}
