/*
 * Copyright (c) 2022 by Charles Anderson
 */

package co.csadev.advent2022

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class Day14Test {

    private val input = """
        498,4 -> 498,6 -> 496,6
        503,4 -> 502,4 -> 502,9 -> 494,9
    """.trimIndent().lines()

    private val example = Day14(input)
    private val real = Day14()

    @Test
    fun `Matches example`() {
        val part1 = example.solvePart1()
        Assertions.assertThat(part1).isEqualTo(24)

        val part2 = example.solvePart2()
        Assertions.assertThat(part2).isEqualTo(93)
    }

    @Test
    fun `Actual answer`() {
        val part1 = real.solvePart1()
        Assertions.assertThat(part1).isEqualTo(1001)

        val part2 = real.solvePart2()
        Assertions.assertThat(part2).isEqualTo(27976)
    }
}
