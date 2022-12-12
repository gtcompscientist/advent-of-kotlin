/*
 * Copyright (c) 2022 by Charles Anderson
 */

/**
 * Advent of Code 2022, Day 4
 * Problem Description: http://adventofcode.com/2021/day/4
 */
package co.csadev.advent2022

import co.csadev.adventOfCode.BaseDay
import co.csadev.adventOfCode.Resources.resourceAsList

class Day04(override val input: List<String> = resourceAsList("22day04.txt")) :
    BaseDay<List<String>, Int, Int> {

    private fun String.toRangePair() = split(",")
        .map { e ->
            e.split("-")
                .map { it.toInt() }
                .run { (this[0]..this[1]).toSet() }
        }

    override fun solvePart1() = input.count { p ->
        val ranges = p.toRangePair()
        ranges[0].containsAll(ranges[1]) || ranges[1].containsAll(ranges[0])
    }

    override fun solvePart2() = input.count { p ->
        p.toRangePair().reduce { acc, ran -> acc.intersect(ran) }.isNotEmpty()
    }
}
