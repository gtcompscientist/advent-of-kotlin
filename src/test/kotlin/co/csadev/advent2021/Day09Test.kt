/*
 * Copyright (c) 2021 by Charles Anderson
 */

package co.csadev.advent2021

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Day09Test {

    private val input = """
        2199943210
        3987894921
        9856789892
        8767896789
        9899965678
    """.trimIndent().lines()

    private val example = Day09(input)
    private val real = Day09()

    @Test
    fun `Matches example`() {
        val part1 = example.solvePart1()
        assertThat(part1).isEqualTo(15)

        val part2 = example.solvePart2()
        assertThat(part2).isEqualTo(1_134)
    }

    @Test
    fun `Actual answer`() {
        val part1 = real.solvePart1()
        assertThat(part1).isEqualTo(458)

        val part2 = real.solvePart2()
        assertThat(part2).isEqualTo(1_391_940)
    }
}
