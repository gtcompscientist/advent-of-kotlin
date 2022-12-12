/*
 * Copyright (c) 2022 by Charles Anderson
 */

package co.csadev.advent2022

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class Day18Test {

    private val input = """
    """.trimIndent().lines()

    private val example = Day18(input)
    private val real = Day18()

    @Test
    fun `Matches example`() {
        val part1 = example.solvePart1()
        Assertions.assertThat(part1).isEqualTo(0)

        val part2 = example.solvePart2()
        Assertions.assertThat(part2).isEqualTo(0)
    }

    @Test
    fun `Actual answer`() {
        val part1 = real.solvePart1()
        Assertions.assertThat(part1).isEqualTo(0)

        val part2 = real.solvePart2()
        Assertions.assertThat(part2).isEqualTo(0)
    }
}
