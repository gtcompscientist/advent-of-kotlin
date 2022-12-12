/*
 * Copyright (c) 2021 by Charles Anderson
 */

/**
 * Advent of Code 2021, Day 18
 * Problem Description: http://adventofcode.com/2021/day/18
 */
package co.csadev.advent2021

import co.csadev.adventOfCode.BaseDay
import co.csadev.adventOfCode.Resources.resourceAsList
import com.github.shiguruikai.combinatoricskt.combinations
import java.util.LinkedList

class Day18(override val input: List<String> = resourceAsList("21day18.txt")) :
    BaseDay<List<String>, Int, Int> {
    sealed interface Token {
        object Open : Token
        object Close : Token
        object Separator : Token
        data class Value(val value: Int) : Token

        companion object {
            fun tokenFor(char: Char) = when (char) {
                '[' -> Open
                ']' -> Close
                ',' -> Separator
                else -> Value(char - '0') // Allows for chars with value higher than 10 (used in a few tests)
            }
        }
    }

    private val homework: List<LinkedList<Token>>
        get() = input.map { str -> LinkedList(str.map { Token.tokenFor(it) }) }

    operator fun LinkedList<Token>.plus(other: LinkedList<Token>) = apply {
        add(0, Token.Open)
        add(Token.Separator)
        addAll(other)
        add(Token.Close)

        // Adding two numbers always triggers a reduction
        while (explode(this) || split(this)) Unit // Keep exploding and splitting until fully reduced
    }

    // Adds 'toAdd' to the first regular number in the given range
    private fun addToFirstRegularNumber(number: LinkedList<Token>, range: IntProgression, toAdd: Int) {
        for (j in range) {
            val curr = number[j]
            if (curr is Token.Value) {
                number[j] = Token.Value(curr.value + toAdd)
                return
            }
        }
    }

    // returns true if an explosion was triggered
    private fun explode(number: LinkedList<Token>): Boolean {
        var nesting = 0
        for (i in number.indices) {
            when (number[i]) {
                Token.Open -> nesting++
                Token.Close -> nesting--
                else -> Unit
            }
            if (nesting == 5) {
                val left = (number[i + 1] as Token.Value).value
                addToFirstRegularNumber(number, i - 1 downTo 0, left)

                // Right value is added to first regular number to the right
                val right = (number[i + 3] as Token.Value).value
                addToFirstRegularNumber(number, i + 5 until number.size, right)

                repeat(5) { number.removeAt(i) } // Remove the current pair
                number.add(i, Token.Value(0)) // and insert 0 in its place
                return true
            }
        }
        return false
    }

    // returns true if the number was split
    fun split(number: LinkedList<Token>): Boolean {
        val idx = number.indexOfFirst { t -> t is Token.Value && t.value >= 10 }
        val token = number.getOrNull(idx) as? Token.Value ?: return false
        val l = token.value / 2
        val r = token.value - l
        number.removeAt(idx)
        number.addAll(idx, listOf(Token.Open, Token.Value(l), Token.Separator, Token.Value(r), Token.Close))
        return true
    }

    private fun LinkedList<Token>.calculateMagnitude(): Int {
        val stack = mutableListOf<MutableList<Int>>()
        for (i in indices) {
            when (val ch = get(i)) {
                Token.Open -> stack.add(mutableListOf())
                Token.Close -> {
                    val (left, right) = stack.removeLast()
                    val magnitude = 3 * left + 2 * right
                    if (stack.isEmpty()) {
                        return magnitude
                    }
                    stack.last().add(magnitude)
                }
                is Token.Value -> stack.last().add(ch.value)
                Token.Separator -> Unit
            }
        }
        return -1
    }

    override fun solvePart1() = homework.reduce { acc, next -> acc + next }.calculateMagnitude()

    override fun solvePart2() = homework.combinations(2).maxOf {
        (LinkedList(it.first().toMutableList()) + it.last()).calculateMagnitude()
    }
}
