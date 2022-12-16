/**
 * Copyright (c) 2021 by Charles Anderson
 * Advent of Code 2021, Day 1
 * Problem Description: http://adventofcode.com/2021/day/1
 */
package co.csadev.advent2020

class Day01(private val data: List<Int>) {

    private val input
        get() = data.asSequence()

    fun solvePart1(): Int = input.mapIndexedNotNull { index, i ->
        input.drop(index + 1)
            .dropWhile { i + it != 2020 }
            .firstOrNull()
            ?.let { i * it }
    }.first()

    fun solvePart2(): Int = input.mapIndexedNotNull { index, first ->
        input.drop(index + 1)
            .mapIndexedNotNull { next, second ->
                input.drop(next + 1)
                    .dropWhile { first + second + it != 2020 }
                    .firstOrNull()
                    ?.let { first * second * it }
            }.firstOrNull()
    }.first()
}
