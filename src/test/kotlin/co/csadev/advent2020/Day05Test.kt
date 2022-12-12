/*
 * Copyright (c) 2021 by Charles Anderson
 */

package co.csadev.advent2020

import co.csadev.adventOfCode.Resources
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("Day 5")
class Day05Test {

    private val input = "FBFBBFFRLR".lines()

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {
        @Test
        fun `Matches example`() {
            // Act
            val answer = Day05(input).solvePart1()

            // Assert
            Assertions.assertThat(answer).isEqualTo(357)
        }

        @Test
        fun `Actual answer`() {
            // Act
            val answer = Day05(Resources.resourceAsList("20day05.txt")).solvePart1()

            // Assert
            Assertions.assertThat(answer).isEqualTo(933)
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {
//        @Test
//        fun `Matches example`() {
//            // Act
//            val answer = Day05(input).solvePart2()
//
//            // Assert
//            Assertions.assertThat(answer).isEqualTo(0)
//        }

        @Test
        fun `Actual answer`() {
            // Act
            val answer = Day05(Resources.resourceAsList("20day05.txt")).solvePart2()

            // Assert
            Assertions.assertThat(answer).isEqualTo(0)
        }
    }
}
