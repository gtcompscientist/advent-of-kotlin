/*
 * Copyright (c) 2021 by Charles Anderson
 */

package co.csadev.advent2020

import co.csadev.adventOfCode.Resources
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("Day 6")
class Day06Test {

    private val input = """
        abc

        a
        b
        c

        ab
        ac

        a
        a
        a
        a

        b
    """.trimIndent()

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {
        @Test
        fun `Matches example`() {
            // Act
            val answer = Day06(input).solvePart1()

            // Assert
            Assertions.assertThat(answer).isEqualTo(11)
        }

        @Test
        fun `Actual answer`() {
            // Act
            val answer = Day06(Resources.resourceAsText("20day06.txt")).solvePart1()

            // Assert
            Assertions.assertThat(answer).isEqualTo(6_504)
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {
        @Test
        fun `Matches example`() {
            // Act
            val answer = Day06(input).solvePart2()

            // Assert
            Assertions.assertThat(answer).isEqualTo(6)
        }

        @Test
        fun `Actual answer`() {
            // Act
            val answer = Day06(Resources.resourceAsText("20day06.txt")).solvePart2()

            // Assert
            Assertions.assertThat(answer).isEqualTo(3351)
        }
    }
}
