/**
 * Copyright (c) 2021 by Charles Anderson
 * Advent of Code 2021, Day 6
 * Problem Description: http://adventofcode.com/2021/day/6
 */
package co.csadev.advent2021

import co.csadev.adventOfCode.BaseDay
import co.csadev.adventOfCode.Resources.resourceAsText

class Day06(override val input: String = resourceAsText("21day06.txt")) :
    BaseDay<String, Long, Long> {
    private val inputMap = input.split(",").map { it.toInt() }
    private val fishCounters =
        (0..8).associateWith { d -> inputMap.count { d == it }.toLong() }.toMutableMap()

    private fun countFish(days: Int) = repeat(days) {
        val fishReset = fishCounters[0] ?: 0
        repeat(8) {
            val idx = it + 1
            fishCounters[idx - 1] = fishCounters[idx] ?: 0
        }
        fishCounters[8] = fishReset
        fishCounters[6] = (fishCounters[6] ?: 0) + fishReset
    }.run { fishCounters.values.sum() }

    override fun solvePart1() = countFish(80)
    override fun solvePart2() = countFish(256 - 80) // Take advantage of already running
}
