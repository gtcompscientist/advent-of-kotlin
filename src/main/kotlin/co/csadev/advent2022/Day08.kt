/*
 * Copyright (c) 2022 by Charles Anderson
 */

/**
 * Advent of Code 2022, Day 8
 * Problem Description: http://adventofcode.com/2021/day/8
 */
package co.csadev.advent2022

import co.csadev.adventOfCode.BaseDay
import co.csadev.adventOfCode.Resources.resourceAsList
import co.csadev.adventOfCode.asInt
import co.csadev.adventOfCode.indexOrMax
import kotlin.math.max

class Day08(override val input: List<String> = resourceAsList("22day08.txt")) :
    BaseDay<List<String>, Int, Int> {

    val size = input.maxOf { it.length }
    private var bestScore = 0
    private val visible = mutableSetOf<Pair<Int, Int>>()

    init {
        val map = MutableList(size) { mutableListOf<Int>() }
        input.forEachIndexed { row, line ->
            line.map { it.asInt() }.forEachIndexed { col, tree ->
                map[col].add(row, tree)
            }
        }
        (0 until size).forEach { x ->
            val maximums = MutableList(4) { -1 }
            (0 until size).forEach { y ->
                bestScore = maxOf(bestScore, calculateScore(map, x, y))
                maximums[0] = maximums[0].orMax(map[x][y]) { visible.add(x to y) }
                maximums[1] = maximums[1].orMax(map[x][size - y - 1]) { visible.add(x to size - y - 1) }
                maximums[2] = maximums[2].orMax(map[y][x]) { visible.add(y to x) }
                maximums[3] = maximums[3].orMax(map[size - y - 1][x]) { visible.add(size - y - 1 to x) }
            }
        }
    }

    private fun Int.orMax(other: Int, ifBigger: () -> Unit): Int {
        if (other > this) {
            ifBigger()
        }
        return max(this, other)
    }

    override fun solvePart1() = visible.size

    override fun solvePart2(): Int = bestScore

    private fun calculateScore(grid: List<List<Int>>, i: Int, j: Int, comp: Int = grid[i][j]) =
        (i + 1 until size).indexOrMax { grid[it][j] >= comp } *
            (i - 1 downTo 0).indexOrMax { grid[it][j] >= comp } *
            (j + 1 until size).indexOrMax { grid[i][it] >= comp } *
            (j - 1 downTo 0).indexOrMax { grid[i][it] >= comp }
}
