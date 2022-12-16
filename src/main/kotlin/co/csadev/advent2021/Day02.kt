/**
 * Copyright (c) 2021 by Charles Anderson
 * Advent of Code 2021, Day 2
 * Problem Description: http://adventofcode.com/2021/day/2
 */
package co.csadev.advent2021

import co.csadev.adventOfCode.BaseDay
import co.csadev.adventOfCode.Resources.resourceAsList

class Day02(data: List<String> = resourceAsList("22day02.txt")) :
    BaseDay<List<Day02.Directions>, Int, Int> {

    class Directions(var x: Int = 0, var y: Int = 0, var aim: Int = 0) {
        fun addAndAim(d: Directions): Directions {
            x += d.x
            y += if (d.x > 0) aim * d.x else 0
            aim += d.y
            return this
        }
    }

    override val input: List<Directions> = data.map {
        it.split(" ").run { this[0] to this[1].toInt() }
    }.map {
        when (it.first) {
            "forward" -> Directions(it.second)
            "down" -> Directions(y = it.second)
            "up" -> Directions(y = it.second * -1)
            else -> Directions()
        }
    }

    override fun solvePart1() = input.sumOf { it.x } * input.sumOf { it.y }

    override fun solvePart2() = input.reduce { acc, d -> acc.addAndAim(d) }.run { x * y }
}
