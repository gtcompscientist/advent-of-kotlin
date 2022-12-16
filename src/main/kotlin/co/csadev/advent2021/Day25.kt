/**
 * Copyright (c) 2021 by Charles Anderson
 * Advent of Code 2021, Day 25
 * Problem Description: http://adventofcode.com/2021/day/25
 */
package co.csadev.advent2021

import co.csadev.adventOfCode.BaseDay
import co.csadev.adventOfCode.Point2D
import co.csadev.adventOfCode.Resources.resourceAsList

class Day25(override val input: List<String> = resourceAsList("21day25.txt")) :
    BaseDay<List<String>, Int, Int> {
    private val height = input.size
    private val width = input.first().length
    private val state = input.flatMapIndexed { y, l ->
        l.mapIndexedNotNull { x, c ->
            if (c == '.') {
                null
            } else {
                Point2D(x, y) to c
            }
        }
    }.toMap().toMutableMap()

    override fun solvePart1(): Int {
        var runState = state.toMap()
        var lastState: String
        var count = 0
        do {
            count++
            lastState = runState.toSortedMap().toString()
            runState = runState.mapKeys { (p, c) ->
                if (c != '>') return@mapKeys p
                val movePoint = p.copy(x = (p.x + 1) % width)
                if (runState.containsKey(movePoint)) p else movePoint
            }
            runState = runState.mapKeys { (p, c) ->
                if (c != 'v') return@mapKeys p
                val movePoint = p.copy(y = (p.y + 1) % height)
                if (runState.containsKey(movePoint)) p else movePoint
            }
        } while (runState.toSortedMap().toString() != lastState)
        return count
    }

    @Suppress("unused")
    private fun Map<Point2D, Char>.printState() {
        (0..keys.maxOf { it.y }).forEach { y ->
            val line = (0..keys.maxOf { it.x }).fold("") { acc, x ->
                val p = Point2D(x, y)
                acc + getOrDefault(p, '.')
            }
            println(line)
        }
        println()
    }

    override fun solvePart2() = 0
}
