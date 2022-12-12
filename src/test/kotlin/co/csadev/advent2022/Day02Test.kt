/*
 * Copyright (c) 2022 by Charles Anderson
 */

package co.csadev.advent2022

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Day02Test {

    private val input = "A Y\nB X\nC Z"

    private val example = Day02(input)
    private val real = Day02()

    @Test
    fun `Matches example`() {
        val part1 = example.solvePart1()
        assertThat(part1).isEqualTo(15)

        val part2 = example.solvePart2()
        assertThat(part2).isEqualTo(12)
    }

    @Test
    fun `Actual answer`() {
        val part1 = real.solvePart1()
        assertThat(part1).isEqualTo(17189)

        val part2 = real.solvePart2()
        assertThat(part2).isEqualTo(13490)
    }
}
