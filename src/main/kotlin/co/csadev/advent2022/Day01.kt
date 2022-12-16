/**
 * Copyright (c) 2022 by Charles Anderson
 * Advent of Code 2022, Day 1
 * Problem Description: http://adventofcode.com/2021/day/1
 */
package co.csadev.advent2022

import co.csadev.adventOfCode.BaseDay
import co.csadev.adventOfCode.Resources

class Day01(override val input: String = Resources.resourceAsText("22day01.txt")) :
    BaseDay<String, Int, Int> {
    private val elves = input.split("\n\n").map { it.lines().sumOf { x -> x.toInt() } }.sortedDescending()

    override fun solvePart1() = elves.first()

    override fun solvePart2() = elves.take(3).sum()
}
