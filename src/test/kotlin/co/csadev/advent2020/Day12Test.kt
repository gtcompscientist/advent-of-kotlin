/*
 * Copyright (c) 2021 by Charles Anderson
 */

package co.csadev.advent2020

import co.csadev.adventOfCode.Resources
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("Day 12")
class Day12Test {

    private val input = """
        F10
        N3
        F7
        R90
        F11
    """.trimIndent().lines()

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {
        @Test
        fun `Matches example`() {
            // Act
            val answer = Day12(input).solvePart1()

            // Assert
            Assertions.assertThat(answer).isEqualTo(25)
        }

        @Test
        fun `Actual answer`() {
            // Act
            val answer = Day12(Resources.resourceAsList("20day12.txt")).solvePart1()

            // Assert
            Assertions.assertThat(answer).isEqualTo(882)
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {
        @Test
        fun `Matches example`() {
            // Act
            val answer = Day12(input).solvePart2()

            // Assert
            Assertions.assertThat(answer).isEqualTo(286)
        }

        @Test
        fun `Actual answer`() {
            // Act
            val answer = Day12(Resources.resourceAsList("20day12.txt")).solvePart2()

            // Assert
            Assertions.assertThat(answer).isEqualTo(28_885)
        }
    }
}
