/*
 * Copyright (c) 2021 by Charles Anderson
 */

package co.csadev.advent2021

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Day07Test {

    private val input = "16,1,2,0,4,2,7,1,2,14"

    private val example = Day07(input)
    private val real = Day07()

    @Test
    fun `Matches example`() {
        val part1 = example.solvePart1()
        assertThat(part1).isEqualTo(37)

        val part2 = example.solvePart2()
        assertThat(part2).isEqualTo(168)
    }

    @Test
    fun `Actual answer`() {
        val part1 = real.solvePart1()
        assertThat(part1).isEqualTo(355_989)

        val part2 = real.solvePart2()
        assertThat(part2).isEqualTo(102_245_489)
    }
}
