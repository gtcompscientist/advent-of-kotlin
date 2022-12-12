/*
 * Copyright (c) 2021 by Charles Anderson
 */

/**
 * Advent of Code 2021, Day 23
 * Problem Description: http://adventofcode.com/2021/day/23
 */
package co.csadev.advent2021

import co.csadev.adventOfCode.BaseDay
import co.csadev.adventOfCode.Point2D
import co.csadev.adventOfCode.Resources.resourceAsList

class Day23(override val input: List<String> = resourceAsList("21day23.txt")) :
    BaseDay<List<String>, Int, Int> {

    private val startingConfig = input.drop(2)
        .flatMapIndexed { y: Int, s: String ->
            s.split("#").mapNotNull { c ->
                c.trim().ifEmpty { null }
            }.mapIndexed { x, c ->
                Point2D(2 + (x * 2), y + 1) to c
            }
        }.toMap().toMutableMap().apply {
            repeat(11) { this[Point2D(it, 0)] = "" }
        }

    override fun solvePart1(): Int {
        startingConfig.toMap()

        return 0
    }

    override fun solvePart2() = 0
}
