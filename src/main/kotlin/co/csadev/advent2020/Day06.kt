/*
 * Copyright (c) 2021 by Charles Anderson
 */

package co.csadev.advent2020

class Day06(private val input: String) {
    private val groupList = input.split("\n\n")
        .map { it.filter { n -> n != '\n' }.associateWith { c -> it.count { count -> count == c } } }

    private val groupSize = input.split("\n\n").map { it.lines().count() }

    fun solvePart1(): Int = groupList.sumOf { it.size }

    fun solvePart2(): Int = groupList.mapIndexed { index, map ->
        map.filter { it.value == groupSize[index] }.values.count()
    }.sum()
}
