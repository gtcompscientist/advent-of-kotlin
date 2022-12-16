/**
 * Copyright (c) 2021 by Charles Anderson
 *  * Advent of Code 2021, Day 12
 * Problem Description: http://adventofcode.com/2021/day/12
 */
package co.csadev.advent2021

import co.csadev.adventOfCode.BaseDay
import co.csadev.adventOfCode.Resources.resourceAsList

class Day12(override val input: List<String> = resourceAsList("21day12.txt")) :
    BaseDay<List<String>, Int, Int> {
    private val connections = input.flatMap {
        it.split("-").run {
            listOf(this[0] to this[1], this[1] to this[0])
        }
    }

    private fun solveWithFilter(process: (List<String>) -> List<String>): Int {
        var paths = listOf(listOf("start"))
        while (paths.any { it.last() != "end" }) {
            paths = paths.flatMap { path ->
                if (path.last() == "end") return@flatMap listOf(path)
                val set = connections.filter { it.first == path.last() }
                    .map { it.second } - process(path).toSet()
                set.map { path + it }
            }
        }
        return paths.size
    }

    override fun solvePart1() = solveWithFilter { path -> path.filter { it[0].isLowerCase() } }

    private fun List<String>.getNotAllowed() = filter { s -> s[0].isLowerCase() }
        .let { s -> if (s.groupBy { it }.any { it.value.size > 1 }) s else emptyList() }

    override fun solvePart2() = solveWithFilter { path -> path.getNotAllowed() + listOf("start") }
}
