/*
 * Copyright (c) 2021 by Charles Anderson
 */

package co.csadev.advent2020

import co.csadev.adventOfCode.Resources
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("Day 11")
class Day11Test {

    private val input = """
        L.LL.LL.LL
        LLLLLLL.LL
        L.L.L..L..
        LLLL.LL.LL
        L.LL.LL.LL
        L.LLLLL.LL
        ..L.L.....
        LLLLLLLLLL
        L.LLLLLL.L
        L.LLLLL.LL
    """.trimIndent().lines()

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {
        @Test
        fun `Matches example`() {
            // Act
            val answer = Day11(input).solvePart1()

            // Assert
            Assertions.assertThat(answer).isEqualTo(37)
        }

        @Test
        fun `Actual answer`() {
            // Act
            val answer = Day11(Resources.resourceAsList("20day11.txt")).solvePart1()

            // Assert
            Assertions.assertThat(answer).isEqualTo(2_453)
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {
        @Test
        fun `Matches example`() {
            // Act
            val answer = Day11(input).solvePart2()

            // Assert
            Assertions.assertThat(answer).isEqualTo(26)
        }

        @Test
        fun `Actual answer`() {
            // Act
            val answer = Day11(Resources.resourceAsList("20day11.txt")).solvePart2()

            // Assert
            Assertions.assertThat(answer).isEqualTo(0)
        }
    }
}
