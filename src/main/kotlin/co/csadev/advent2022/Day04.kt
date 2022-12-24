/**
 * Copyright (c) 2022 by Charles Anderson
 * Advent of Code 2022, Day 4
 * Problem Description: http://adventofcode.com/2021/day/4
 */
package co.csadev.advent2022

import co.csadev.adventOfCode.BaseDay
import co.csadev.adventOfCode.Resources.resourceAsList

class Day04(override val input: List<String> = resourceAsList("22day04.txt")) :
    BaseDay<List<String>, Int, Int> {

    private val ranges = input.map {
        it.split(",", "-").chunked(2).map { (i1, i2) -> (i1.toInt()..i2.toInt()).toSet() }
    }

    override fun solvePart1() = ranges.count { (r1, r2) -> r1.containsAll(r2) || r2.containsAll(r1) }

    override fun solvePart2() = ranges.count { (r1, r2) -> r1.intersect(r2).isNotEmpty() }
}
