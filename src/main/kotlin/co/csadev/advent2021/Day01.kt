/**
 * Copyright (c) 2021 by Charles Anderson
 * Advent of Code 2021, Day 1
 * Problem Description: http://adventofcode.com/2021/day/1
 */
package co.csadev.advent2021

import co.csadev.adventOfCode.BaseDay
import co.csadev.adventOfCode.Resources.resourceAsListOfInt
import co.csadev.adventOfCode.countIncreases

class Day01(override val input: List<Int> = resourceAsListOfInt("21day01.txt")) : BaseDay<List<Int>, Int, Int> {

    override fun solvePart1() = input.countIncreases()

    override fun solvePart2() = input.windowed(3, 1) { it.sum() }.countIncreases()
}
