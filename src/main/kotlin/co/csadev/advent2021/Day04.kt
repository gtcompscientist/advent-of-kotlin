/**
 * Copyright (c) 2021 by Charles Anderson
 * Advent of Code 2021, Day 4
 * Problem Description: http://adventofcode.com/2021/day/4
 */
package co.csadev.advent2021

import co.csadev.adventOfCode.BaseDay
import co.csadev.adventOfCode.Resources.resourceAsText

class Day04(override val input: String = resourceAsText("21day04.txt")) :
    BaseDay<String, Int, Int> {

    private val newlineSplit = input.split("\n\n")
    private val numbersCalled = newlineSplit.first().split(",").map { it.trim().toInt() }
    private val boards = newlineSplit.drop(1)
        .map { board ->
            board.split(" ", "\n")
                .filter { it.isNotEmpty() }
                .mapIndexed { index, num -> BingoSpot(num.toInt(), index / 5, index % 5) }
        }.map { BingoBoard(it) }

    private val scores =
        numbersCalled.flatMap { numberDrawn -> boards.mapNotNull { it.callNumber(numberDrawn) } }

    private data class BingoSpot(
        val num: Int,
        val yIdx: Int,
        val xIdx: Int,
        var marked: Boolean = false
    )

    private data class BingoBoard(val spots: List<BingoSpot>, private var solved: Boolean = false) {
        private val unmarkedSum: Int
            get() = spots.filter { !it.marked }.sumOf { it.num }

        fun callNumber(num: Int) = if (solved) {
            null
        } else {
            spots.firstOrNull { it.num == num }?.let { calledSpot ->
                calledSpot.marked = true
                solved = spots.count { it.xIdx == calledSpot.xIdx && it.marked } == 5 ||
                    spots.count { it.yIdx == calledSpot.yIdx && it.marked } == 5
                when {
                    solved -> unmarkedSum * num
                    else -> null
                }
            }
        }
    }

    override fun solvePart1() = scores.first()

    override fun solvePart2() = scores.last()
}
