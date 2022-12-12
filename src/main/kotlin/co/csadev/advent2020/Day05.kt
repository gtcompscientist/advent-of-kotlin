/*
 * Copyright (c) 2021 by Charles Anderson
 */

package co.csadev.advent2020

class Day05(private val input: List<String>) {
    companion object {
        const val MAX_ROW = 128
        const val MAX_SEAT = 8
    }

    private val String.calculateRowValue: Int
        get() {
            var minRow = 0
            var maxRow = MAX_ROW
            var minSeat = 0
            var maxSeat = MAX_SEAT
            forEach {
                when (it) {
                    'F' -> maxRow -= (maxRow - minRow) / 2
                    'B' -> minRow += (maxRow - minRow) / 2
                    'L' -> maxSeat -= (maxSeat - minSeat) / 2
                    'R' -> minSeat += (maxSeat - minSeat) / 2
                }
            }
            return minRow * 8 + minSeat
        }

    private val inputValues = input.map { it.calculateRowValue }.sorted()

    fun solvePart1(): Int = inputValues.last()

    fun solvePart2(): Int = (inputValues.first()..inputValues.last()).map { it }.first { !inputValues.contains(it) }
}
