/**
 * Copyright (c) 2022 by Charles Anderson
 * Advent of Code 2022, Day 25
 * Problem Description: http://adventofcode.com/2021/day/25
 */
package co.csadev.advent2022

import co.csadev.adventOfCode.BaseDay
import co.csadev.adventOfCode.Resources.resourceAsList
import co.csadev.adventOfCode.pow

class Day25(override val input: List<String> = resourceAsList("22day25.txt")) :
    BaseDay<List<String>, String, Int> {

    private val snafuStr = mapOf(0L to "0", 1L to "1", 2L to "2", 3L to "=", 4L to "-")
    private val snafuAdj = mapOf(0L to 0, 1L to -1, 2L to -2, 3L to 2, 4L to 1)

    private val values = input.map {
        it.reversed().foldRightIndexed(0L) { idx, c, acc ->
            acc + (5L.pow(idx) * (c.digitToIntOrNull() ?: if (c == '-') -1 else -2))
        }
    }

    private fun Long.toSnafu(): String {
        var ret = this
        val snafu = mutableListOf<String>()
        while (ret != 0L) {
            (ret % 5).apply { snafu.add(snafuStr[this]!!.also { ret += snafuAdj[this]!! }) }
            ret /= 5
        }
        return snafu.reversed().joinToString("")
    }

    override fun solvePart1() = values.sum().toSnafu()

    override fun solvePart2() = 0
}
