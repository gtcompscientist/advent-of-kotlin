/*
 * Copyright (c) 2021 by Charles Anderson
 */

/**
 * Advent of Code 2021, Day 11
 * Problem Description: http://adventofcode.com/2021/day/11
 */
package co.csadev.advent2021

import co.csadev.adventOfCode.BaseDay
import co.csadev.adventOfCode.Point2D
import co.csadev.adventOfCode.Resources.resourceAsList

class Day11(override val input: List<String> = resourceAsList("21day11.txt")) :
    BaseDay<List<String>, Int, Int> {

    private val octopuses = input.flatMapIndexed { y, l ->
        l.mapIndexed { x, o -> Point2D(x, y) to "$o".toInt() }
    }.toMap().toMutableMap()

    private val steps by lazy { octopuses.step() }

    override fun solvePart1() = steps.take(100).sum()

    override fun solvePart2() = steps.takeWhile { it < 100 }.count() + 101

    private fun MutableMap<Point2D, Int>.step() = sequence {
        val flashes = mutableListOf<Point2D>()
        while (true) {
            // First, the energy level of each octopus increases by 1.
            forEach { (pos, energy) ->
                octopuses[pos] = energy + 1
            }

            // Then, any octopus with an energy level greater than 9 flashes.
            // This increases the energy level of all adjacent octopuses by 1,
            //   including octopuses that are diagonally adjacent.
            // If this causes an octopus to have an energy level greater than 9, it also flashes.
            // This process continues as long as new octopuses keep having their energy level
            //   increased beyond 9. (An octopus can only flash at most once per step.)
            do {
                val toFlash = filter { it.value > 9 && !flashes.contains(it.key) }
                toFlash.keys.flatMap { it.neighbors }.forEach {
                    octopuses[it]?.let { e -> octopuses[it] = e + 1 }
                }
                flashes.addAll(toFlash.keys)
            } while (toFlash.isNotEmpty())

            // Finally, any octopus that flashed during this step has its energy level set to 0,
            //   as it used all of its energy to flash.
            flashes.forEach { octopuses[it] = 0 }
            yield(flashes.size).also { flashes.clear() }
        }
    }
}
