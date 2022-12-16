/**
 * Copyright (c) 2021 by Charles Anderson
 * Advent of Code 2021, Day 17
 * Problem Description: http://adventofcode.com/2021/day/17
 */
package co.csadev.advent2021

import co.csadev.adventOfCode.BaseDay
import co.csadev.adventOfCode.Resources.resourceAsList

class Day17(override val input: List<String> = resourceAsList("21day17.txt")) :
    BaseDay<List<String>, Int, Int> {
    private val targetX = input.first().split(",").let { IntRange(it[0].toInt(), it[1].toInt()) }
    private val targetY = input.last().split(",").let { IntRange(it[0].toInt(), it[1].toInt()) }
    private val solvedTargets = targets(targetX, targetY)

    override fun solvePart1() = solvedTargets.maxOf { it }

    override fun solvePart2() = solvedTargets.count()

    @Suppress("ReturnCount", "LoopWithTooManyJumpStatements", "ComplexCondition")
    private fun targets(rangeX: IntRange, rangeY: IntRange) = sequence {
        (0..rangeX.last).forEach { x ->
            var peak = 0
            (rangeY.first..(rangeX.first / 2) - rangeY.first).forEach { y ->
                var cX = 0
                var cY = 0
                var step = 0
                while (true) {
                    peak = maxOf(cY, peak)
                    val speed = x - step
                    cX += maxOf(speed, 0)
                    cY += y - step
                    if (rangeX.contains(cX) && rangeY.contains(cY)) {
                        yield(peak)
                        break
                    } else if (cX > rangeX.last || cY < rangeY.first || (speed < 0 && cX < rangeX.first)) {
                        break
                    }
                    step++
                }
            }
        }
    }
}
