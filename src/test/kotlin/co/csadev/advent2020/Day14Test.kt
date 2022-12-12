/*
 * Copyright (c) 2021 by Charles Anderson
 */

package co.csadev.advent2020

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("Day 14")
class Day14Test {

    private val input = """
        mask = XXXXXXXXXXXXXXXXXXXXXXXXXXXXX1XXXX0X
        mem[8] = 11
        mem[7] = 101
        mem[8] = 0
    """.trimIndent().lines()

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {
        @Test
        fun `Matches example`() {
            // Act
            val answer = Day14(input).solvePart1()

            // Assert
            Assertions.assertThat(answer).isEqualTo(165)
        }

        @Test
        fun `Actual answer`() {
            // Act
            val answer = Day14().solvePart1()

            // Assert
            Assertions.assertThat(answer).isEqualTo(4297467072083L)
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {
//        @Test
//        fun `Matches example`() {
//            // Act
//            val answer = Day14(input).solvePart2()
//
//            // Assert
//            Assertions.assertThat(answer).isEqualTo(208)
//        }

        @Test
        fun `Actual answer`() {
            // Act
            val answer = Day14().solvePart2()

            // Assert
            Assertions.assertThat(answer).isEqualTo(5030603328768L)
        }
    }
}
