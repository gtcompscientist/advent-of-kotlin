/**
 * Copyright (c) 2022 by Charles Anderson
 * Advent of Code 2022, Day 3
 * Problem Description: http://adventofcode.com/2022/day/3
 */
package co.csadev.advent2022

import co.csadev.adventOfCode.BaseDay
import co.csadev.adventOfCode.Resources.resourceAsList

class Day03(override val input: List<String> = resourceAsList("22day03.txt")) :
    BaseDay<List<String>, Int, Int> {

    private val Char.score: Int
        get() = if (isLowerCase()) this - 'a' + 1 else this - 'A' + 27

    override fun solvePart1(): Int {
        return input.sumOf { line ->
            line.chunked(line.length / 2)
                .map { it.toSet() }
                .reduce { acc, set -> acc.intersect(set) }
                .first().score
        }
    }

    override fun solvePart2(): Int {
        return input.chunked(3)
            .map { it.map { l -> l.toSet() }.reduce { acc, set -> acc.intersect(set) } }
            .sumOf { it.first().score }
    }
}
