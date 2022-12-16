/**
 * Copyright (c) 2022 by Charles Anderson
 * Advent of Code 2022, Day 6
 * Problem Description: http://adventofcode.com/2021/day/6
 */
package co.csadev.advent2022

import co.csadev.adventOfCode.BaseDay
import co.csadev.adventOfCode.Resources.resourceAsText

class Day06(override val input: String = resourceAsText("22day06.txt")) :
    BaseDay<String, Int, Int> {

    private fun indexAfterUnique(size: Int) = input.windowedSequence(size, 1)
        .indexOfFirst { it.toSet().size == size } + size

    override fun solvePart1() = indexAfterUnique(4)

    override fun solvePart2() = indexAfterUnique(14)
}
