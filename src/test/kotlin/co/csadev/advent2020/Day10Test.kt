/*
 * Copyright (c) 2021 by Charles Anderson
 */

package co.csadev.advent2020

import co.csadev.adventOfCode.Resources
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("Day 10")
class Day10Test {

    private val input = """
        16
        10
        15
        5
        1
        11
        7
        19
        6
        12
        4
    """.trimIndent().lines().map { it.toInt() }

    private val input2 = """
        28
        33
        18
        42
        31
        14
        46
        20
        48
        47
        24
        23
        49
        45
        19
        38
        39
        11
        1
        32
        25
        35
        8
        17
        7
        9
        4
        2
        34
        10
        3
    """.trimIndent().lines().map { it.toInt() }

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {
        @Test
        fun `Matches example`() {
            // Act
            val answer = Day10(input).solvePart1()

            // Assert
            Assertions.assertThat(answer).isEqualTo(35)

            // Act
            val answer2 = Day10(input2).solvePart1()

            // Assert
            Assertions.assertThat(answer2).isEqualTo(220)
        }

        @Test
        fun `Actual answer`() {
            // Act
            val answer = Day10(Resources.resourceAsListOfInt("20day10.txt")).solvePart1()

            // Assert
            Assertions.assertThat(answer).isEqualTo(2_040)
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {
        @Test
        fun `Matches example`() {
            // Act
            val answer = Day10(input).solvePart2()

            // Assert
            Assertions.assertThat(answer).isEqualTo(8)

            // Act
            val answer2 = Day10(input2).solvePart2()

            // Assert
            Assertions.assertThat(answer2).isEqualTo(19_208)
        }

        @Test
        fun `Actual answer`() {
            // Act
            val answer = Day10(Resources.resourceAsListOfInt("20day10.txt")).solvePart2()

            // Assert
            Assertions.assertThat(answer).isEqualTo(28_346_956_187_648L)
        }
    }
}
