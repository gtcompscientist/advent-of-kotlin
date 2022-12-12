/*
 * Copyright (c) 2021 by Charles Anderson
 */

/**
 * Advent of Code 2021, Day 20
 * Problem Description: http://adventofcode.com/2021/day/20
 */
package co.csadev.advent2021

import co.csadev.adventOfCode.BaseDay
import co.csadev.adventOfCode.Point2D
import co.csadev.adventOfCode.Resources.resourceAsText
import kotlin.system.measureTimeMillis

class Day20(override val input: String = resourceAsText("21day20.txt")) :
    BaseDay<String, Int, Int> {

    private val enhancement = input.split("\n\n").first().map { it == '#' }
    private val image = input.split("\n\n")[1].lines().flatMapIndexed { y, s ->
        s.mapIndexed { x, c -> Point2D(x, y) to (if (c == '#') 1 else 0) }
    }.toMap()

    private fun Map<Point2D, Int>.enhance(default: Int = if (enhancement[0]) 1 else 0): Map<Point2D, Int> {
        return mapValues {
            val mapped = it.key.neighborsInc.toSortedSet().map { n ->
                getOrDefault(n, default)
            }
            val joined = mapped.joinToString("")
            val bin = joined.toInt(2)

            if (enhancement[bin]) 1 else 0
        }
    }

    private fun Map<Point2D, Int>.print(): Map<Point2D, Int> {
        val min = Point2D(keys.minOf { it.x } - 1, keys.minOf { it.y } - 1)
        val max = Point2D(keys.maxOf { it.x } + 1, keys.maxOf { it.y } + 1)
        (min.y..max.y).forEach { y ->
            println((min.x..max.x).joinToString("") { x -> if (this[Point2D(x, y)] == 1) "#" else "." })
        }
        return this
    }

    private fun Map<Point2D, Int>.enlarge(repeat: Int): Map<Point2D, Int> {
        val min = Point2D(keys.minOf { it.x } - (repeat * 2), keys.minOf { it.y } - (repeat * 2))
        val max = Point2D(keys.maxOf { it.x } + (repeat * 2), keys.maxOf { it.y } + (repeat * 2))
        return toMutableMap().apply {
            (min.y..max.y).forEach { y ->
                (min.x..max.x).forEach { x ->
                    val p = Point2D(x, y)
                    this[p] = this.getOrDefault(p, 0)
                }
            }
        }.toSortedMap()
    }

    private fun Map<Point2D, Int>.trim(edgeSize: Int): Map<Point2D, Int> {
        val min = Point2D(keys.minOf { it.x }, keys.minOf { it.y })
        val max = Point2D(keys.maxOf { it.x }, keys.maxOf { it.y })
        return filterKeys { it.x in min.x + edgeSize..max.x - edgeSize && it.y in min.y + edgeSize..max.y - edgeSize }
    }

    override fun solvePart1() = image.enlarge(2)
        .enhance().enhance()
        .trim(2).print().values.sum()

    override fun solvePart2(): Int {
        var enhanced = image.enlarge(100)
        repeat(50) { i ->
            measureTimeMillis {
                enhanced = enhanced.enhance(default = if (enhancement[0]) 1 else 0) // .print()
            }.also { println("Enhance x$i -> $it ms") }
        }
        return enhanced.trim(100).print().values.sum()
    }
}
