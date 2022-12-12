/*
 * Copyright (c) 2021 by Charles Anderson
 */

/**
 * Advent of Code 2021, Day 7
 * Problem Description: http://adventofcode.com/2021/day/7
 */
package co.csadev.advent2021

import co.csadev.adventOfCode.BaseDay
import co.csadev.adventOfCode.Resources.resourceAsText
import co.csadev.adventOfCode.range
import kotlin.math.abs

class Day07(override val input: String = resourceAsText("21day07.txt")) :
    BaseDay<String, Int, Int> {
    private val crabs = input.split(",").map { it.toInt() }.let { list ->
        list.distinct().associateWith { list.count { i -> i == it } }.toSortedMap()
    }

    override fun solvePart1(): Int = crabs.range.minOf { pos ->
        crabs.keys.sumOf { k -> abs(pos - k) * (crabs[k] ?: 0) }
    }

    override fun solvePart2(): Int = crabs.range.minOf { pos ->
        crabs.keys.sumOf { k ->
            abs(k - pos)
                .let { n -> (((n * n) + n) / 2) }
                .let { single -> single * (crabs[k] ?: 0) }
        }
    }
}
