/*
 * Copyright (c) 2021 by Charles Anderson
 */

package co.csadev.advent2020

class Day07(input: List<String>) {
    companion object {
        private val unusedText = """bags|bag|contain|,|\.""".toRegex()
        private val whitespace = """\s+""".toRegex()
    }

    private data class BagRule(val parent: String, val cost: Int, val child: String)

    private val relationships: Set<BagRule> =
        input.filterNot { it.contains("no other") }.flatMap { row ->
            val parts = row.replace(unusedText, "").split(whitespace)
            val parent = parts.take(2).joinToString(" ")
            parts.drop(2).windowed(3, 3, false).map { child ->
                BagRule(
                    parent,
                    child.first().toInt(),
                    child.drop(1).joinToString(" ")
                )
            }
        }.toSet()

    fun solvePart1(): Int =
        findParents().size - 1

    fun solvePart2(): Int =
        baggageCost() - 1

    private fun findParents(bag: String = "shiny gold"): Set<String> =
        relationships
            .filter { it.child == bag }
            .flatMap { findParents(it.parent) }.toSet() + bag

    private fun baggageCost(bag: String = "shiny gold"): Int =
        relationships
            .filter { it.parent == bag }
            .sumOf { it.cost * baggageCost(it.child) } + 1
}
