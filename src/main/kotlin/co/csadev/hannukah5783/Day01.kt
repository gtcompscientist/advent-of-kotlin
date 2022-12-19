/**
 * Copyright (c) 2022 by Charles Anderson
 * Advent of Code 2022, Day 20
 * Problem Description: http://adventofcode.com/2021/day/20
 */
package co.csadev.hannukah5783

import co.csadev.adventOfCode.BaseDay
import co.csadev.adventOfCode.Resources
import co.csadev.hannukah5783.Day01.PhoneChars.Companion.toDigit

class Day01(override val input: List<String> = Resources.resourceAsList("83noahs-customers.jsonl")) :
    BaseDay<List<String>, String, Int> {

    override fun solvePart1(): String {
        return customers.map { c ->
            c.lastName() to c
        }.filter { (lastName, _) ->
            lastName.length == 10
        }.first { (lastName, c) ->
            val asPhone = lastName.asPhone
            val strippedPhone = c.phone.replace("-", "")
            asPhone == strippedPhone
        }.second.phone
    }

    private enum class PhoneChars(val chars: List<Char>, val digit: String) {
        P1(emptyList(), "1"),
        P2("ABC".toList(), "2"),
        P3("DEF".toList(), "3"),
        P4("GHI".toList(), "4"),
        P5("JKL".toList(), "5"),
        P6("MNO".toList(), "6"),
        P7("PQRS".toList(), "7"),
        P8("TUV".toList(), "8"),
        P9("WXYZ".toList(), "9");

        companion object {
            private val vals = values()
            fun Char.toDigit() = vals.first { this in it.chars }.digit
        }
    }
    private val String.asPhone: String
        get() = uppercase().map { it.toDigit() }.joinToString("")

    override fun solvePart2() = 0
}
