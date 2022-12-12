/*
 * Copyright (c) 2022 by Charles Anderson
 */

package co.csadev.advent2022

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Day08Test {

    private val input = """
        30373
        25512
        65332
        33549
        35390
    """.trimIndent().lines()

    private val example = Day08(input)
    private val real = Day08()

    @Test
    fun `Matches example`() {
        val part1 = example.solvePart1()
        assertThat(part1).isEqualTo(21)

        val part2 = example.solvePart2()
        assertThat(part2).isEqualTo(8)
    }

    @Test
    fun `Actual answer`() {
        val part1 = real.solvePart1()
        assertThat(part1).isEqualTo(1693)

        val part2 = real.solvePart2()
        assertThat(part2).isEqualTo(422059)
    }
}
