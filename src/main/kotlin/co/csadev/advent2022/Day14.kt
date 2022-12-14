/*
 * Copyright (c) 2022 by Charles Anderson
 */

/**
 * Advent of Code 2022, Day 14
 * Problem Description: http://adventofcode.com/2021/day/14
 */
package co.csadev.advent2022

import co.csadev.adventOfCode.BaseDay
import co.csadev.adventOfCode.Point2D
import co.csadev.adventOfCode.Resources.resourceAsList
import kotlin.math.max


class Day14(override val input: List<String> = resourceAsList("22day14.txt")) :
    BaseDay<List<String>, Int, Int> {

    override fun solvePart1() = solve()

    override fun solvePart2() = solve(false)

    private var maxY = Int.MIN_VALUE

    fun solve(part1: Boolean = true): Int {
        val cave = MutableList(1000) { MutableList(1000) { false } }.apply {
            input.forEach { line ->
                addPath(line)
            }
        }
        if (!part1) {
            cave.addPath("0," + (maxY + 2) + " -> 999," + (maxY + 2))
        }
        var answer = 0
        while (cave.addSand(maxY)) {
            answer++
        }
        return answer
    }

    private fun MutableList<MutableList<Boolean>>.addPath(line: String) {
        val rock = line.split(" -> ").map {
            val (x, y) = it.split(",")
            maxY = max(maxY, y.toInt())
            Point2D(x.toInt(), y.toInt())
        }
        rock.windowed(2).forEach { (p1, p2) ->
            val (x1, y1) = p1
            val (x2, y2) = p2
            for (j in x1.coerceAtMost(x2)..x1.coerceAtLeast(x2)) {
                for (k in y1.coerceAtMost(y2)..y1.coerceAtLeast(y2)) {
                    this[j][k] = true
                }
            }
        }
    }

    private fun MutableList<MutableList<Boolean>>.addSand(maxY: Int): Boolean {
        if (this[500][0]) {
            return false
        }
        var x = 500
        var y = 0
        while (y <= maxY + 3) {
            if (!this[x][y + 1]) {
                y++
            } else if (!this[x - 1][y + 1]) {
                y++; x--
            } else if (!this[x + 1][y + 1]) {
                y++; x++
            } else {
                this[x][y] = true
                return true
            }
        }
        return false
    }
}
