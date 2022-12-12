/*
 * Copyright (c) 2021 by Charles Anderson
 */

package co.csadev.advent2020

import com.github.shiguruikai.combinatoricskt.combinations

class Day09(input: List<String>, val size: Int = 25) {
    private val inputNum = input.map { it.toLong() }
    private val scanner = inputNum.take(size).toMutableList()

    fun solvePart1(): Long = inputNum.drop(size).first { i ->
        scanner.combinations(2).none { c -> c.sum() == i }.also {
            scanner.removeAt(0)
            scanner.add(i)
        }
    }

    fun solvePart2(): Long {
        val solution = solvePart1()
        var answer = 0L
        inputNum.indices.first { idxMin ->
            inputNum.indices.drop(idxMin + 1).any { idxMax ->
                val subList = inputNum.subList(idxMin, idxMax)
                (subList.sum() == solution).apply {
                    answer = subList.sorted().run { first() + last() }
                }
            }
        }
        return answer
    }
}
