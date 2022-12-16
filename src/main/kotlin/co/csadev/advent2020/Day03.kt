/**
 * Copyright (c) 2021 by Charles Anderson
 * Advent of Code 2020, Day 3
 * Problem Description: http://adventofcode.com/2020/day/3
 */
package co.csadev.advent2020

class Day03(private val input: List<String>) {

    private val width = input.first().length

    fun solvePart1(): Int = checkTrees(3 to 1)

    fun solvePart2(): Long = listOf(1 to 1, 3 to 1, 5 to 1, 7 to 1, 1 to 2).fold(1L) { acc, i ->
        acc * checkTrees(i)
    }

    private fun checkTrees(slope: Pair<Int, Int>): Int {
        var trees = 0
        var location = 0 to 0
        while (location.second < input.size) {
            trees += if (input[location.second][location.first] == '#') 1 else 0
            location += slope
        }
        return trees
    }

    private operator fun Pair<Int, Int>.plus(slope: Pair<Int, Int>) = Pair(
        (first + slope.first) % width,
        second + slope.second
    )
}
