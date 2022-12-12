/*
 * Copyright (c) 2021 by Charles Anderson
 */

/**
 * Advent of Code 2021, Day 13
 * Problem Description: http://adventofcode.com/2021/day/13
 */
package co.csadev.advent2021

import co.csadev.adventOfCode.BaseDay
import co.csadev.adventOfCode.Point2D
import co.csadev.adventOfCode.Resources.resourceAsList
import co.csadev.adventOfCode.printGraph

class Day13(override val input: List<String> = resourceAsList("21day13.txt")) :
    BaseDay<List<String>, Int, Int> {
    private val inputInstructions = input.indexOf("").run { input.subList(this + 1, input.size) }.map { instr ->
        instr.split(" ")[2].split("=").let { it[0] to it[1].toInt() }
    }
    private val inputCoords = input.indexOf("").run { input.subList(0, this) }
        .map { it.split(",").let { p -> Point2D(p[0].toInt(), p[1].toInt()) } }

    private fun List<Point2D>.fold(move: Pair<String, Int>): List<Point2D> {
        val pos = move.second
        return map { p ->
            when (move.first) {
                "x" -> if (p.x > pos) p.copy(x = pos - (p.x - pos)) else p
                else -> if (p.y > pos) p.copy(y = pos - (p.y - pos)) else p
            }
        }.distinct()
    }

    override fun solvePart1() = inputCoords.fold(inputInstructions.first()).count()

    override fun solvePart2(): Int {
        var coords = inputCoords
        inputInstructions.forEach { move ->
            coords = coords.fold(move)
        }
        // Test: Square
        // Actual: PZFJHRFZ
        coords.printGraph()
        return 0
    }
}
