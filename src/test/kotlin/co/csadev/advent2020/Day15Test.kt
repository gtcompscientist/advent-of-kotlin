/*
 * Copyright (c) 2021 by Charles Anderson
 */

package co.csadev.advent2020

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("Day 15")
class Day15Test {

    private val input = "0,3,6"

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {
        @Test
        fun `Matches example`() {
            // Act
            val answer = Day15(input).solvePart1()

            // Assert
            Assertions.assertThat(answer).isEqualTo(165)
        }

        @Test
        fun `Actual answer`() {
            // Act
            val answer = Day15().solvePart1()

            // Assert
            Assertions.assertThat(answer).isEqualTo(4297467072083L)
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {
        @Test
        fun `Matches example`() {
            // Act
            val answer = Day15(input).solvePart2()

            // Assert
            Assertions.assertThat(answer).isEqualTo(175594)
        }

        @Test
        fun `Actual answer`() {
            // Act
            val answer = Day15().solvePart2()

            // Assert
            Assertions.assertThat(answer).isEqualTo(0)
        }
    }
}
