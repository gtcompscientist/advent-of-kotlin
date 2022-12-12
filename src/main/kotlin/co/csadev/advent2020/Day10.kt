/*
 * Copyright (c) 2021 by Charles Anderson
 */

package co.csadev.advent2020

class Day10(input: List<Int>) {
    private val adapters: List<Int> = input.plus(0).plus(input.maxOrNull()!! + 3).sorted()

    fun solvePart1(): Int =
        adapters
            .asSequence()
            .zipWithNext()
            .map { it.second - it.first }
            .groupingBy { it }
            .eachCount()
            .run {
                getOrDefault(1, 1) * getOrDefault(3, 1)
            }

    fun solvePart2(): Long {
        val pathsByAdapter: MutableMap<Int, Long> = mutableMapOf(0 to 1L)

        adapters.drop(1).forEach { adapter ->
            pathsByAdapter[adapter] = (1..3).sumOf { lookBack ->
                pathsByAdapter.getOrDefault(adapter - lookBack, 0)
            }
        }

        return pathsByAdapter.getValue(adapters.last())
    }
}
