/**
 * Copyright (c) 2022 by Charles Anderson
 * Advent of Code 2022, Day 2
 * Problem Description: http://adventofcode.com/2021/day/2
 */
package co.csadev.advent2022

import co.csadev.adventOfCode.BaseDay
import co.csadev.adventOfCode.Resources

class Day02(override val input: String = Resources.resourceAsText("22day02.txt")) :
    BaseDay<String, Int, Int> {

    enum class RPS(val opp: String, val beats: String, val draw: String, val loses: String) {
        Rock("A", "Y", "X", "Z"),
        Paper("B", "Z", "Y", "X"),
        Scissors("C", "X", "Z", "Y");

        companion object {
            private fun selfVal(s: String) = 1 + values().first { it.draw == s }.ordinal
            fun score(o: String, s: String) = selfVal(s) + values()
                .first { it.opp == o }
                .run { if (beats == s) 6 else if (loses == s) 0 else 3 }

            fun strat(o: String, s: String) = values()
                .first { it.opp == o }
                .run {
                    when (s) {
                        "X" -> selfVal(loses)
                        "Y" -> 3 + selfVal(draw)
                        else -> 6 + selfVal(beats)
                    }
                }
        }
    }

    private val strategy = input.lines().map { it.split(" ") }

    override fun solvePart1() = strategy.sumOf { RPS.score(it[0], it[1]) }

    override fun solvePart2() = strategy.sumOf { RPS.strat(it[0], it[1]) }
}
