/**
 * Copyright (c) 2021 by Charles Anderson
 * Advent of Code 2021, Day 21
 * Problem Description: http://adventofcode.com/2021/day/21
 */
package co.csadev.advent2021

import co.csadev.adventOfCode.BaseDay
import co.csadev.adventOfCode.Resources.resourceAsList

class Day21(override val input: List<String> = resourceAsList("21day21.txt")) :
    BaseDay<List<String>, Int, Long> {

    private var rollCount = 0
    private val deterministicDie: Sequence<Int>
        get() = sequence {
            while (true) {
                yield((rollCount++ % 100) + 1)
            }
        }

    override fun solvePart1(): Int {
        var p1Pos = input.first().toInt()
        var p1Score = 0
        var p2Pos = input.last().toInt()
        var p2Score = 0
        val dice = deterministicDie
        while (true) {
            var roll = dice.take(3).sum()
            p1Pos = (p1Pos + roll) % 10
            p1Score += if (p1Pos == 0) 10 else p1Pos
            if (p1Score >= 1000) {
                return p2Score * rollCount
            }
            roll = dice.take(3).sum()
            p2Pos = (p2Pos + roll) % 10
            p2Score += if (p2Pos == 0) 10 else p2Pos
            if (p2Score >= 1000) {
                return p1Score * rollCount
            }
        }
    }

    override fun solvePart2(): Long = Score(input.first().toInt(), input.last().toInt(), 0, 0).let { (first, second) ->
        maxOf(
            first,
            second
        )
    }

    private object Score {
        private val dice = sequence {
            for (i in 1..3) for (j in 1..3) for (k in 1..3) yield(i + j + k)
        }.groupingBy { it }.eachCount()
        private val x = LongArray(44100)
        private val y = LongArray(44100)

        operator fun invoke(player1: Int, player2: Int, score1: Int, score2: Int): Pair<Long, Long> {
            val i = player1 + 10 * player2 + 100 * score1 + 2100 * score2 - 11
            if (x[i] == 0L && y[i] == 0L) {
                var x1 = 0L
                var y1 = 0L
                for ((d, n) in dice) {
                    val play = (player1 + d - 1) % 10 + 1
                    if (score1 + play < 21) {
                        val (x2, y2) = this(player2, play, score2, score1 + play)
                        x1 += n * y2
                        y1 += n * x2
                    } else {
                        x1 += n
                    }
                }
                x[i] = x1
                y[i] = y1
            }
            return x[i] to y[i]
        }
    }
}
