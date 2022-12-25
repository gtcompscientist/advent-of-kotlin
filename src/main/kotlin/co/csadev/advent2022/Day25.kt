/**
 * Copyright (c) 2022 by Charles Anderson
 * Advent of Code 2022, Day 25
 * Problem Description: http://adventofcode.com/2021/day/25
 */
package co.csadev.advent2022

import co.csadev.adventOfCode.BaseDay
import co.csadev.adventOfCode.Resources.resourceAsList

class Day25(override val input: List<String> = resourceAsList("22day25.txt")) :
    BaseDay<List<String>, String, Int> {

    val values = input.map {
        var fivers = 1L
        it.foldRight(0L) { c, acc ->
            fivers *= 5
            val cVal = when (c) {
                '-' -> -1
                '=' -> -2
                else -> c.digitToInt()
            }
            acc + (fivers / 5L * cVal)
        }
    }

    private fun Long.toSnafu(): String {
        var ret = this
        val snafu = mutableListOf<String>()
        while (ret != 0L) {
            when (ret % 5) {
                0L -> snafu.add("0")
                1L -> snafu.add("1").also { ret -= 1 }
                2L -> snafu.add("2").also { ret -= 2 }
                3L -> snafu.add("=").also { ret += 2 }
                4L -> snafu.add("-").also { ret += 1 }
            }
            ret /= 5
        }

        return snafu.reversed().joinToString("")
    }

    override fun solvePart1() = values.sum().toSnafu()

    override fun solvePart2() = 0
}
