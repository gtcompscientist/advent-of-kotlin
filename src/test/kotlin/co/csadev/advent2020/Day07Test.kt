/*
 * Copyright (c) 2021 by Charles Anderson
 */

package co.csadev.advent2020

import co.csadev.adventOfCode.Resources
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("Day 7")
class Day07Test {

    private val input = """
light red bags contain 1 bright white bag, 2 muted yellow bags.
dark orange bags contain 3 bright white bags, 4 muted yellow bags.
bright white bags contain 1 shiny gold bag.
muted yellow bags contain 2 shiny gold bags, 9 faded blue bags.
shiny gold bags contain 1 dark olive bag, 2 vibrant plum bags.
dark olive bags contain 3 faded blue bags, 4 dotted black bags.
vibrant plum bags contain 5 faded blue bags, 6 dotted black bags.
faded blue bags contain no other bags.
dotted black bags contain no other bags.
    """.trimIndent().lines()

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {
        @Test
        fun `Matches example`() {
            // Act
            val answer = Day07(input).solvePart1()

            // Assert
            Assertions.assertThat(answer).isEqualTo(4)
        }

        @Test
        fun `Actual answer`() {
            // Act
            val answer = Day07(Resources.resourceAsList("20day07.txt")).solvePart1()

            // Assert
            Assertions.assertThat(answer).isEqualTo(32)
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {
        @Test
        fun `Matches example`() {
            // Act
            val answer = Day07(input).solvePart2()

            // Assert
            Assertions.assertThat(answer).isEqualTo(126)
        }

        @Test
        fun `Actual answer`() {
            // Act
            val answer = Day07(Resources.resourceAsList("20day07.txt")).solvePart2()

            // Assert
            Assertions.assertThat(answer).isEqualTo(158_493)
        }
    }
}
