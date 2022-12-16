/**
 * Copyright (c) 2021 by Charles Anderson
 * Advent of Code 2021, Day 9
 * Problem Description: http://adventofcode.com/2021/day/9
 */
package co.csadev.advent2021

import co.csadev.adventOfCode.BaseDay
import co.csadev.adventOfCode.Point2D
import co.csadev.adventOfCode.Resources.resourceAsList
import co.csadev.adventOfCode.product

class Day09(override val input: List<String> = resourceAsList("21day09.txt")) :
    BaseDay<List<String>, Int, Int> {
    private val inputGraph = input.flatMapIndexed { y, i ->
        i.mapIndexed { x, c -> Point2D(x, y) to c.toString().toInt() }
    }.toMap()

    override fun solvePart1(): Int = inputGraph.getLowPoints().sumOf { inputGraph[it]!! + 1 }

    override fun solvePart2(): Int {
        val basinGraph = inputGraph.getLowPoints().toMutableList()
        return basinGraph.map { b ->
            val basin = mutableListOf(b)
            val checkpoints = b.adjacent.toMutableList()
            while (checkpoints.isNotEmpty()) {
                val addTo = checkpoints.filter { 9 != (inputGraph[it] ?: 9) && !basin.contains(it) }
                basin.addAll(addTo)
                checkpoints.clear()
                checkpoints.addAll(addTo.flatMap { a -> a.adjacent }.distinct().filter { !basin.contains(it) })
            }
            basin.size
        }.sortedDescending().take(3).product()
    }

    private fun Map<Point2D, Int>.getLowPoints() = keys.filter { p ->
        this[p]!!.let { i -> p.adjacent.all { i < this.getOrDefault(it, Int.MAX_VALUE) } }
    }.toMutableList()
}
