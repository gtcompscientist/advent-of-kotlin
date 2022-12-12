/*
 * Copyright (c) 2020 by Charles Anderson
 */

/**
 * Advent of Code 2020, Day 15 - Rambunctious Recitation
 * Problem Description: http://adventofcode.com/2020/day/15
 */
package co.csadev.advent2020

import co.csadev.adventOfCode.Resources.resourceAsText

class Day15(input: String = resourceAsText("20day15.txt")) {

    private val startingNumbers = input.split(",").map { it.toInt() }

    fun solvePart1() = solve(2020)

    fun solvePart2() = solve(30000000)

    fun solve(turns: Int): Int =
        memoryGame().drop(turns - 1).first()

    private fun memoryGame(): Sequence<Int> = sequence {
        yieldAll(startingNumbers)
        val memory = startingNumbers.mapIndexed { index, i -> i to index }.toMap().toMutableMap()
        var turns = startingNumbers.size
        var sayNext = 0
        while (true) {
            yield(sayNext)
            val lastTimeSpoken = memory[sayNext] ?: turns
            memory[sayNext] = turns
            sayNext = turns - lastTimeSpoken
            turns++
        }
    }
}
