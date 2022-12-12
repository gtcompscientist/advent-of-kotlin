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
class Day08Test {

    private val input = """
nop +0
acc +1
jmp +4
acc +3
jmp -3
acc -99
acc +1
jmp -4
acc +6
    """.trimIndent().lines()

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {
        @Test
        fun `Matches example`() {
            // Act
            val answer = Day08(input).solvePart1()

            // Assert
            Assertions.assertThat(answer).isEqualTo(5)
        }

        @Test
        fun `Actual answer`() {
            // Act
            val answer = Day08(Resources.resourceAsList("20day08.txt")).solvePart1()

            // Assert
            Assertions.assertThat(answer).isEqualTo(1_217)
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {
        @Test
        fun `Matches example`() {
            // Act
            val answer = Day08(input).solvePart2()

            // Assert
            Assertions.assertThat(answer).isEqualTo(8)
        }

        @Test
        fun `Actual answer`() {
            // Act
            val answer = Day08(Resources.resourceAsList("20day08.txt")).solvePart2()

            // Assert
            Assertions.assertThat(answer).isEqualTo(501)
        }
    }
}
