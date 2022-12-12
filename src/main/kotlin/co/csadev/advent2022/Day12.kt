/*
 * Copyright (c) 2022 by Charles Anderson
 */

/**
 * Advent of Code 2022, Day 12
 * Problem Description: http://adventofcode.com/2021/day/12
 */
package co.csadev.advent2022

import co.csadev.adventOfCode.BaseDay
import co.csadev.adventOfCode.Point2D
import co.csadev.adventOfCode.Resources.resourceAsList
import co.csadev.adventOfCode.shortestPath

class Day12(override val input: List<String> = resourceAsList("22day12.txt")) :
    BaseDay<List<String>, Int, Int> {

    private val topo = mutableMapOf<Point2D, Char>()
    private lateinit var start: Point2D
    private lateinit var end: Point2D

    init {
        // go over input, populating heights and noting start and end
        for (y in input.indices) {
            val line = input[y]
            for (x in line.indices) {
                val curr = Point2D(x, y)
                when (line[x]) {
                    'S' -> start = curr.also { topo[curr] = 'a' }
                    'E' -> end = curr.also { topo[curr] = 'z' }
                    else -> topo[curr] = line[x]
                }
            }
        }
    }

    override fun solvePart1() = plotCourse(start, end) ?: Int.MAX_VALUE

    override fun solvePart2() = topo.keys
        .filter { k -> topo[k] == 'a' }
        .mapNotNull { k -> plotCourse(k, end) }
        .minOf { it }

    private fun plotCourse(s: Point2D, end: Point2D) = shortestPath(s, end) { current, next ->
        next in topo.keys && (topo[next]!! <= topo[current]!! + 1)
    }?.size
}
