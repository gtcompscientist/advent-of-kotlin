/*
 * Copyright (c) 2022 by Charles Anderson
 */

package co.csadev.advent2022

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class Day20Test {

    private val input = """
        1
        2
        -3
        3
        -2
        0
        4
    """.trimIndent().lines()

    private val example = Day20(input)
    private val real = Day20()

    @Test
    fun `Matches example`() {
        val part1 = example.solvePart1()
        Assertions.assertThat(part1).isEqualTo(3L)

        val part2 = example.solvePart2()
        Assertions.assertThat(part2).isEqualTo(1623178306L)
    }

    @Test
    fun `Actual answer`() {
        val part1 = real.solvePart1()
        Assertions.assertThat(part1).isEqualTo(2275L)

        val part2 = real.solvePart2()
        Assertions.assertThat(part2).isEqualTo(4090409331120L)
    }
}
