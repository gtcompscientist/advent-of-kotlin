/**
 * Copyright (c) 2021 by Charles Anderson
 * Advent of Code 2020, Day 2
 * Problem Description: http://adventofcode.com/2020/day/2
 */
package co.csadev.advent2020

class Day02(data: List<String>) {

    private val pattern = """^(\d+)-(\d+) (\w): (.+)$""".toRegex()
    private val input = data.mapNotNull { pattern.find(it) }.map {
        val (min, max, letter, password) = it.destructured
        PasswordCheck(min.toInt(), max.toInt(), letter.first(), password)
    }

    private data class PasswordCheck(val min: Int, val max: Int, val letter: Char, val password: String) {

        val isValid: Boolean = password.count { c -> letter == c }.run { this in min..max }
        val isValid2 = (password[min - 1] == letter) xor (password[max - 1] == letter)
    }

    fun solvePart1(): Int = input.count { it.isValid }

    fun solvePart2(): Int = input.count { it.isValid2 }
}
