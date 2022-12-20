/**
 * Copyright (c) 2022 by Charles Anderson
 * Advent of Code 2022, Day 20
 * Problem Description: http://adventofcode.com/2021/day/20
 */
package co.csadev.advent2022

import co.csadev.adventOfCode.BaseDay
import co.csadev.adventOfCode.Resources.resourceAsList

class Day20(override val input: List<String> = resourceAsList("22day20.txt")) :
    BaseDay<List<String>, Long, Long> {

    private val decryptionKey = 811589153

    override fun solvePart1() = mix()

    override fun solvePart2() = mix(decryptionKey, 10)

    private fun mix(decryptKey: Int = 1, mix: Int = 1): Long {
        val base = input.mapIndexed { index, i -> index to i.toLong() * decryptKey }
        val mixing = base.toMutableList()
        repeat(mix) {
            base.forEach { p ->
                val index = mixing.indexOf(p)
                mixing.removeAt(index)
                // Order matters here, must calculate the new index after removal
                val newIndex = (index + p.second).mod(mixing.size)
                mixing.add(newIndex, p)
            }
        }
        val output = mixing.map { it.second }
        val zero = output.indexOf(0L)
        return output.run { this[(zero + 1000) % size] + this[(zero + 2000) % size] + this[(zero + 3000) % size] }
    }
}
