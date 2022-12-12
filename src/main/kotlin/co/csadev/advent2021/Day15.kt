/*
 * Copyright (c) 2021 by Charles Anderson
 */

/**
 * Advent of Code 2021, Day 15
 * Problem Description: http://adventofcode.com/2021/day/15
 */
package co.csadev.advent2021

import co.csadev.adventOfCode.BaseDay
import co.csadev.adventOfCode.Point2D
import co.csadev.adventOfCode.Resources.resourceAsList
import java.util.PriorityQueue

class Day15(override val input: List<String> = resourceAsList("21day15.txt")) :
    BaseDay<List<String>, Int, Int> {

    private val cave: Array<IntArray> = parseInput(input)

    override fun solvePart1(): Int =
        cave.traverse()

    override fun solvePart2(): Int =
        cave.traverse(
            Point2D((cave.first().size * 5) - 1, (cave.size * 5) - 1)
        )

    private fun Array<IntArray>.traverse(
        destination: Point2D = Point2D(first().lastIndex, lastIndex)
    ): Int {
        val toBeEvaluated = PriorityQueue<Traversal>().apply { add(Traversal(Point2D(0, 0), 0)) }
        val visited = mutableSetOf<Point2D>()

        while (toBeEvaluated.isNotEmpty()) {
            val thisPlace = toBeEvaluated.poll()
            if (thisPlace.point == destination) {
                return thisPlace.totalRisk
            }
            if (thisPlace.point !in visited) {
                visited.add(thisPlace.point)
                thisPlace.point
                    .adjacent
                    .filter { it.x in (0..destination.x) && it.y in (0..destination.y) }
                    .forEach { toBeEvaluated.offer(Traversal(it, thisPlace.totalRisk + this[it])) }
            }
        }
        error("No path to destination (which is really weird, right?)")
    }

    private operator fun Array<IntArray>.get(point: Point2D): Int {
        val dx = point.x / this.first().size
        val dy = point.y / this.size
        val originalRisk = this[point.y % this.size][point.x % this.first().size]
        val newRisk = (originalRisk + dx + dy)
        return newRisk.takeIf { it < 10 } ?: (newRisk - 9)
    }

    private fun parseInput(input: List<String>): Array<IntArray> =
        input.map { row ->
            row.map { risk ->
                risk.toString().toInt()
            }.toIntArray()
        }.toTypedArray()

    private class Traversal(val point: Point2D, val totalRisk: Int) : Comparable<Traversal> {
        override fun compareTo(other: Traversal): Int =
            this.totalRisk - other.totalRisk
    }
}
