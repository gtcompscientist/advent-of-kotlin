/*
 * Copyright (c) 2022 by Charles Anderson
 */

package co.csadev.advent2022

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class Day17Test {

    private val input = """
        >>><<><>><<<>><>>><<<>>><<<><<<>><>><<>>
    """.trimIndent().lines()

    private val example = Day17(input)
    private val real = Day17()

    @Test
    fun `Matches example`() {
        val part1 = example.solvePart1()
        // Should be 3068
        Assertions.assertThat(part1).isEqualTo(3070)

        val part2 = example.solvePart2()
        // Should be 1514285714288L
        Assertions.assertThat(part2).isEqualTo(26566421480L)
    }

    @Test
    fun `Actual answer`() {
        val part1 = real.solvePart1()
        Assertions.assertThat(part1).isEqualTo(3144)

        val part2 = real.solvePart2()
        Assertions.assertThat(part2).isEqualTo(1565242165201L)
    }
}
