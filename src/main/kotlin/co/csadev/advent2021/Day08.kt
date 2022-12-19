/**
 * Copyright (c) 2021 by Charles Anderson
 * Advent of Code 2021, Day 8
 * Problem Description: http://adventofcode.com/2021/day/8
 */
package co.csadev.advent2021

import co.csadev.adventOfCode.BaseDay
import co.csadev.adventOfCode.Resources.resourceAsList
import co.csadev.adventOfCode.match

class Day08(override val input: List<String> = resourceAsList("21day08.txt")) :
    BaseDay<List<String>, Int, Long> {
    data class Display(val inputs: List<String>) {
        val outputs by lazy { inputs.drop(10).map { it.toSet() } }
        private val outputMap by lazy {
            mutableMapOf<Set<Char>, Int>().apply {
                // We order the list so that the items with the smallest string length are up front.
                val strings = inputs.take(10).sortedBy { it.length }.map { it.toSet() }

                // Unique numbers by length
                this[strings[0]] = 1 // 2
                this[strings[1]] = 7 // 3
                this[strings[2]] = 4 // 4
                this[strings[9]] = 8 // 7

                // Keep track of 6 to verify 5.
                var sixIndex = 0

                // Digits with length 6: 0 (1 + 7), 6, 9 (1 + 7 + 4)
                for (i in 6..8) {
                    val toCompare: Set<Char> = strings[i]
                    when {
                        toCompare.match(strings[0], strings[1], strings[2]) -> this[toCompare] = 9
                        toCompare.match(strings[0], strings[1]) -> this[toCompare] = 0
                        else -> {
                            this[toCompare] = 6
                            sixIndex = i
                        }
                    }
                }

                // Digits with length 5: 2, 3 (1 + 7), 5 (6 matches)
                for (i in 3..5) {
                    val toCompare: Set<Char> = strings[i]
                    when {
                        toCompare.match(strings[0], strings[1]) -> this[toCompare] = 3
                        strings[sixIndex].match(toCompare) -> this[toCompare] = 5
                        else -> this[toCompare] = 2
                    }
                }
            }
        }

        val output by lazy { outputs.fold(0L) { acc, s -> acc * 10 + outputMap[s]!! } }

        // / region Printer
        @Suppress("unused", "MaxLineLength", "MaximumLineLength")
        fun print() {
            println("  0:      1:      2:      3:      4:      5:      6:      7:      8:      9:    |   0:      1:      2:      3:    ")
            println("${0.a}  ${1.a}  ${2.a}  ${3.a}  ${4.a}  ${5.a}  ${6.a}  ${7.a}  ${8.a}  ${9.a}  | ${10.a}  ${11.a}  ${12.a}  ${13.a}  ")
            println("${0.b}  ${1.b}  ${2.b}  ${3.b}  ${4.b}  ${5.b}  ${6.b}  ${7.b}  ${8.b}  ${9.b}  | ${10.b}  ${11.b}  ${12.b}  ${13.b}  ")
            println("${0.b}  ${1.b}  ${2.b}  ${3.b}  ${4.b}  ${5.b}  ${6.b}  ${7.b}  ${8.b}  ${9.b}  | ${10.b}  ${11.b}  ${12.b}  ${13.b}  ")
            println("${0.d}  ${1.d}  ${2.d}  ${3.d}  ${4.d}  ${5.d}  ${6.d}  ${7.d}  ${8.d}  ${9.d}  | ${10.d}  ${11.d}  ${12.d}  ${13.d}  ")
            println("${0.e}  ${1.e}  ${2.e}  ${3.e}  ${4.e}  ${5.e}  ${6.e}  ${7.e}  ${8.e}  ${9.e}  | ${10.e}  ${11.e}  ${12.e}  ${13.e}  ")
            println("${0.e}  ${1.e}  ${2.e}  ${3.e}  ${4.e}  ${5.e}  ${6.e}  ${7.e}  ${8.e}  ${9.e}  | ${10.e}  ${11.e}  ${12.e}  ${13.e}  ")
            println("${0.g}  ${1.g}  ${2.g}  ${3.g}  ${4.g}  ${5.g}  ${6.g}  ${7.g}  ${8.g}  ${9.g}  | ${10.g}  ${11.g}  ${12.g}  ${13.g}  ")
        }

        private val Int.a: String
            get() = " ${if (inputs[this].contains("a")) "aaaa" else "...."} "
        private val Int.b: String
            get() = "${if (inputs[this].contains("b")) "b" else "."}    ${if (inputs[this].contains("c")) "c" else "."}"
        private val Int.d: String
            get() = " ${if (inputs[this].contains("d")) "dddd" else "...."} "
        private val Int.e: String
            get() = "${if (inputs[this].contains("e")) "e" else "."}    ${if (inputs[this].contains("f")) "f" else "."}"
        private val Int.g: String
            get() = " ${if (inputs[this].contains("g")) "gggg" else "...."} "
        // / endregion
    }

    private val digits = input.map { Display(it.replace("| ", "").split(" ")) }

    private val checkList = listOf(2, 3, 4, 7)
    override fun solvePart1(): Int = digits.sumOf {
        it.outputs.count { o -> checkList.contains(o.size) }
    }

    override fun solvePart2(): Long = digits.sumOf { it.output }
}
