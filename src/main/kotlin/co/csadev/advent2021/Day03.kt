/**
 * Copyright (c) 2021 by Charles Anderson
 * Advent of Code 2021, Day 3
 * Problem Description: http://adventofcode.com/2021/day/3
 */
package co.csadev.advent2021

import co.csadev.adventOfCode.BaseDay
import co.csadev.adventOfCode.Resources.resourceAsList
import co.csadev.adventOfCode.longest
import co.csadev.adventOfCode.shortest

class Day03(override val input: List<String> = resourceAsList("21day03.txt")) : BaseDay<List<String>, Int, Int> {

    override fun solvePart1(): Int {
        val gamma = (0 until (input.first().length))
            .map { index -> input.sortedBy { it[index] }[input.size / 2][index] }
            .joinToString(separator = "")
            .toIntOrNull(2) ?: 0
        val epsilon = gamma.inv() and (0xFFFF shr (16 - input.first().length))
        return gamma * epsilon
    }

    override fun solvePart2(): Int = input.bitwiseFilter(true).toInt(2) * input.bitwiseFilter(false).toInt(2)

    private fun List<String>.bitwiseFilter(keepMostCommon: Boolean): String =
        first().indices.fold(this) { inputs, column ->
            if (inputs.size == 1) {
                inputs
            } else {
                val split = inputs.partition { it[column] == '1' }
                if (keepMostCommon) split.longest() else split.shortest()
            }
        }.first()

    fun originalPart2(): Int {
        val inputSize = input.first().length
        val gammaList = input.toMutableList()
        val epsilonList = input.toMutableList()
        (0 until inputSize).forEach { index ->
            val gammaLength = gammaList.size
            val gammaCount = gammaList.count { it[index] == '1' }
            val gammaCount0 = epsilonList.count { it[index] == '1' }
            if (gammaCount < gammaLength && gammaCount0 < gammaLength) {
                gammaList.removeIf { it[index] != if (gammaCount * 2 >= gammaLength) '1' else '0' }
            }

            val epsilonLength = epsilonList.size
            val epsilonCount = epsilonList.count { it[index] == '1' }
            val epsilonCount0 = epsilonList.count { it[index] == '0' }
            if (epsilonCount < epsilonLength && epsilonCount0 < epsilonLength) {
                epsilonList.removeIf { it[index] == if (epsilonCount * 2 >= epsilonLength) '1' else '0' }
            }
        }
        val gamma = gammaList.firstOrNull()?.toIntOrNull(2) ?: 0
        val epsilon = epsilonList.firstOrNull()?.toIntOrNull(2) ?: 0
        return gamma * epsilon
    }
}
