/*
 * Copyright (c) 2021 by Charles Anderson
 */

package co.csadev.advent2021

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Day06Test {

    private val input = "3,4,3,1,2"

    private val example = Day06(input)
    private val real = Day06()

    @Test
    fun `Matches example`() {
        val part1 = example.solvePart1()
        assertThat(part1).isEqualTo(5_934)

        val part2 = example.solvePart2()
        assertThat(part2).isEqualTo(26_984_457_539L)
    }

    @Test
    fun `Actual answer`() {
        val part1 = real.solvePart1()
        assertThat(part1).isEqualTo(376_194)

        val part2 = real.solvePart2()
        assertThat(part2).isEqualTo(1_693_022_481_538)
    }
}
