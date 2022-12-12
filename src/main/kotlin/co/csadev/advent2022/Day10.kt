/*
 * Copyright (c) 2022 by Charles Anderson
 */

/**
 * Advent of Code 2022, Day 10
 * Problem Description: http://adventofcode.com/2021/day/10
 */
package co.csadev.advent2022

import co.csadev.adventOfCode.BaseDay
import co.csadev.adventOfCode.Point2D
import co.csadev.adventOfCode.Resources.resourceAsList
import co.csadev.adventOfCode.printArea

class Day10(override val input: List<String> = resourceAsList("22day10.txt")) :
    BaseDay<List<String>, Int, Unit> {

    private var signal = 0
    private val screen = mutableMapOf<Point2D, Boolean>()

    init {
        var register = 1
        var cycle = 1
        val onCycle = {
            val screenX = (cycle - 1) % 40
            if (screenX in (register - 1)..(register + 1)) {
                screen[Point2D(screenX, (cycle - 1) / 40)] = true
            }
            signal += register * if (cycle in 1..221 && (cycle + 20) % 40 == 0) cycle else 0
            cycle++
        }

        input.forEach { line ->
            val commands = line.split(" ")
            onCycle()
            if (commands[0] != "noop") {
                onCycle()
                register += commands[1].toInt()
            }
        }
    }

    override fun solvePart1() = signal

    override fun solvePart2(): Unit = screen.printArea { '#' }
}
