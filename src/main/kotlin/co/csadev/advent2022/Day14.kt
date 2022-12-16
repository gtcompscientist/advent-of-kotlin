/**
 * Copyright (c) 2022 by Charles Anderson
 * Advent of Code 2022, Day 14
 * Problem Description: http://adventofcode.com/2021/day/14
 */
package co.csadev.advent2022

import co.csadev.adventOfCode.BaseDay
import co.csadev.adventOfCode.Resources.resourceAsList
import co.csadev.adventOfCode.deepCopy
import kotlin.math.max

class Day14(override val input: List<String> = resourceAsList("22day14.txt")) :
    BaseDay<List<String>, Int, Int> {

    private var maxY = Int.MIN_VALUE
    private val cave: MutableList<MutableList<Boolean>> =
        MutableList(1000) { MutableList(1000) { false } }.apply {
            maxY = Int.MIN_VALUE
            input.forEach { addPath(it) }
        }

    override fun solvePart1() = cave.deepCopy().solve()

    override fun solvePart2() =
        cave.deepCopy().apply { addPath("0," + (maxY + 2) + " -> 999," + (maxY + 2)) }.solve()

    fun MutableList<MutableList<Boolean>>.solve() =
        generateSequence { addSand(maxY) }.takeWhile { it }.toList().count()

    private fun MutableList<MutableList<Boolean>>.addPath(line: String) {
        line.split(" -> ").flatMap {
            val (x, y) = it.split(",")
            maxY = max(maxY, y.toInt())
            listOf(x.toInt(), y.toInt())
        }.windowed(4, 2).forEach { (x1, y1, x2, y2) ->
            for (j in x1.coerceAtMost(x2)..x1.coerceAtLeast(x2)) {
                for (k in y1.coerceAtMost(y2)..y1.coerceAtLeast(y2)) {
                    this[j][k] = true
                }
            }
        }
    }

    @Suppress("ReturnCount")
    private fun MutableList<MutableList<Boolean>>.addSand(maxY: Int): Boolean {
        var x = 500
        var y = 0
        if (this[x][y]) {
            return false
        }
        while (y <= maxY + 3) {
            when {
                !this[x][y + 1] -> y++
                !this[x - 1][y + 1] -> y++ to x--
                !this[x + 1][y + 1] -> y++ to x++
                else -> {
                    this[x][y] = true
                    return true
                }
            }
        }
        return false
    }
}
