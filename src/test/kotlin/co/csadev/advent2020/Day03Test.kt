/*
 * Copyright (c) 2021 by Charles Anderson
 */

package co.csadev.advent2020

import co.csadev.adventOfCode.Resources.resourceAsList
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("Day 3")
class Day03Test {

    // Arrange
    private val input =
        """
            ..##.......
            #...#...#..
            .#....#..#.
            ..#.#...#.#
            .#...##..#.
            ..#.##.....
            .#.#.#....#
            .#........#
            #.##...#...
            #...##....#
            .#..#...#.#
        """.trimIndent().lines()

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {
        @Test
        fun `Matches example`() {
            // Act
            val answer = Day03(input).solvePart1()

            // Assert
            assertThat(answer).isEqualTo(7)
        }

        @Test
        fun `Actual answer`() {
            // Act
            val answer = Day03(resourceAsList("20day03.txt")).solvePart1()

            // Assert
            assertThat(answer).isEqualTo(203)
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {
        @Test
        fun `Matches example`() {
            // Act
            val answer = Day03(input).solvePart2()

            // Assert
            assertThat(answer).isEqualTo(336)
        }

        @Test
        fun `Actual answer`() {
            // Act
            val answer = Day03(resourceAsList("20day03.txt")).solvePart2()

            // Assert
            assertThat(answer).isEqualTo(3_316_272_960L)
        }
    }
}
