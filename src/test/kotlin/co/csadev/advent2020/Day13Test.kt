/*
 * Copyright (c) 2021 by Charles Anderson
 */

package co.csadev.advent2020

import co.csadev.adventOfCode.Resources
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("Day 13")
class Day13Test {

    private val input = """
        939
        7,13,x,x,59,x,31,19
    """.trimIndent().lines()

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {
        @Test
        fun `Matches example`() {
            // Act
            val answer = Day13(input).solvePart1()

            // Assert
            Assertions.assertThat(answer).isEqualTo(295)
        }

        @Test
        fun `Actual answer`() {
            // Act
            val answer = Day13(Resources.resourceAsList("20day13.txt")).solvePart1()

            // Assert
            Assertions.assertThat(answer).isEqualTo(3_865)
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {
        @Test
        fun `Matches example`() {
            // Act
            val answer = Day13(input).solvePart2()

            // Assert
            Assertions.assertThat(answer).isEqualTo(1_068_781L)
        }

        @Test
        fun `Actual answer`() {
            // Act
            val answer = Day13(Resources.resourceAsList("20day13.txt")).solvePart2()

            // Assert
            Assertions.assertThat(answer).isEqualTo(415_579_909_629_976L)
        }
    }
}
