/*
 * Copyright (c) 2021 by Charles Anderson
 */

/**
 * Advent of Code 2021, Day 5
 * Problem Description: http://adventofcode.com/2021/day/5
 */
package co.csadev.advent2021

import co.csadev.adventOfCode.BaseDay
import co.csadev.adventOfCode.Resources.resourceAsText

class Day05(override val input: List<String> = resourceAsText("21day05.txt").lines()) :
    BaseDay<List<String>, Int, Int> {
    private fun countVents(input: List<String>, diagonals: Boolean): Int {
        return input.flatMap {
            val (x1, y1, x2, y2) = """\d+""".toRegex().findAll(it).map { found -> found.value.toInt() }.toList()
            when {
                x1 == x2 -> {
                    val (start, end) = listOf(y1, y2).sorted()
                    (start..end).map { y -> x1 to y }
                }
                y1 == y2 -> {
                    val (start, end) = listOf(x1, x2).sorted()
                    (start..end).map { x -> x to y1 }
                }
                diagonals -> {
                    val dx = if (x1 < x2) 1 else -1
                    val dy = if (y1 < y2) 1 else -1
                    (0..dx * (x2 - x1)).map { t ->
                        (x1 + dx * t) to (y1 + dy * t)
                    }
                }
                else -> emptyList()
            }
        }.groupingBy { it }.eachCount().count { it.value > 1 }
    }

    override fun solvePart1() = countVents(input, false)
    override fun solvePart2() = countVents(input, true)
}
