/**
 * Copyright (c) 2021 by Charles Anderson
 * Advent of Code 2021, Day 10
 * Problem Description: http://adventofcode.com/2021/day/10
 */
package co.csadev.advent2021

import co.csadev.adventOfCode.BaseDay
import co.csadev.adventOfCode.Resources.resourceAsList
import co.csadev.adventOfCode.midpoint

class Day10(override val input: List<String> = resourceAsList("21day10.txt")) :
    BaseDay<List<String>, Int, Long> {

    private val parsed = input.map { parseRow(it) }

    override fun solvePart1() = parsed
        .filterIsInstance<Corrupted>()
        .sumOf { it.score() }

    override fun solvePart2() = parsed
        .filterIsInstance<Incomplete>()
        .map { it.score() }
        .sorted()
        .midpoint()

    private fun parseRow(row: String): ParseResult {
        val stack = ArrayDeque<Char>()

        row.forEach { symbol ->
            when {
                symbol in openToClose -> stack.addLast(symbol)
                openToClose[stack.removeLast()] != symbol -> return Corrupted(symbol)
            }
        }

        return if (stack.isEmpty()) Success else Incomplete(stack.reversed())
    }

    private sealed interface ParseResult
    private object Success : ParseResult
    private class Corrupted(val actual: Char) : ParseResult {
        fun score(): Int =
            scoresPart1.getValue(actual)
    }
    private class Incomplete(val pending: List<Char>) : ParseResult {
        fun score(): Long =
            pending
                .map { openToClose.getValue(it) }
                .fold(0) { carry, symbol ->
                    (carry * 5) + scoresPart2.getValue(symbol)
                }
    }

    companion object {
        private val scoresPart1 = mapOf(')' to 3, ']' to 57, '}' to 1197, '>' to 25137)
        private val scoresPart2 = mapOf(')' to 1, ']' to 2, '}' to 3, '>' to 4)
        private val openToClose = mapOf('(' to ')', '[' to ']', '{' to '}', '<' to '>')
    }
}
