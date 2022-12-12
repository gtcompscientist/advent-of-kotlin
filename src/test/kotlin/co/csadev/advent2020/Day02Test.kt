/*
 * Copyright (c) 2021 by Charles Anderson
 */

package co.csadev.advent2020

import co.csadev.adventOfCode.Resources.resourceAsList
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("Day 2")
class Day02Test {

    // Arrange
    private val input = listOf("1-3 a: abcde", "1-3 b: cdefg", "2-9 c: ccccccccc")

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {
        @Test
        fun `Matches example`() {
            // Act
            val answer = Day02(input).solvePart1()

            // Assert
            assertThat(answer).isEqualTo(2)
        }

        @Test
        fun `Actual answer`() {
            // Act
            val answer = Day02(resourceAsList("20day02.txt")).solvePart1()

            // Assert
            assertThat(answer).isEqualTo(398)
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {
        @Test
        fun `Matches example`() {
            // Act
            val answer = Day02(input).solvePart2()

            // Assert
            assertThat(answer).isEqualTo(1)
        }

        @Test
        fun `Actual answer`() {
            // Act
            val answer = Day02(resourceAsList("20day02.txt")).solvePart2()

            // Assert
            assertThat(answer).isEqualTo(562)
        }
    }
}
