/*
 * Copyright (c) 2021 by Charles Anderson
 */

package co.csadev.advent2021

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Day01Test {

    private val input = listOf(199, 200, 208, 210, 200, 207, 240, 269, 260, 263)

    private val example = Day01(input)
    private val real = Day01()

    @Test
    fun `Matches example`() {
        val part1 = example.solvePart1()
        assertThat(part1).isEqualTo(7)

        val part2 = example.solvePart2()
        assertThat(part2).isEqualTo(5)
    }

    @Test
    fun `Actual answer`() {
        val part1 = real.solvePart1()
        assertThat(part1).isEqualTo(1583)

        val part2 = real.solvePart2()
        assertThat(part2).isEqualTo(1627)
    }
}
