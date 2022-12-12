/*
 * Copyright (c) 2021 by Charles Anderson
 */

package co.csadev.advent2020

import co.csadev.adventOfCode.Resources
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("Day 9")
class Day09Test {

    private val input = """
        35
        20
        15
        25
        47
        40
        62
        55
        65
        95
        102
        117
        150
        182
        127
        219
        299
        277
        309
        576
    """.trimIndent().lines()

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {
        @Test
        fun `Matches example`() {
            // Act
            val answer = Day09(input, 5).solvePart1()

            // Assert
            Assertions.assertThat(answer).isEqualTo(127)
        }

        @Test
        fun `Actual answer`() {
            // Act
            val answer = Day09(Resources.resourceAsList("20day09.txt")).solvePart1()

            // Assert
            Assertions.assertThat(answer).isEqualTo(88_311_122L)
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {
        @Test
        fun `Matches example`() {
            // Act
            val answer = Day09(input, 5).solvePart2()

            // Assert
            Assertions.assertThat(answer).isEqualTo(62)
        }

        @Test
        fun `Actual answer`() {
            // Act
            val answer = Day09(Resources.resourceAsList("20day09.txt")).solvePart2()

            // Assert
            Assertions.assertThat(answer).isEqualTo(13_549_369L)
        }
    }
}
