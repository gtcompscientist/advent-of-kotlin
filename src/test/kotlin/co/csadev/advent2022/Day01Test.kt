/*
 * Copyright (c) 2022 by Charles Anderson
 */

package co.csadev.advent2022

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Day01Test {

    private val input =
"""        1000
        2000
        3000
        
        4000

        5000
        6000

        7000
        8000
        9000

        10000
""".trimIndent()

    private val example = Day01(input)
    private val real = Day01()

    @Test
    fun `Matches example`() {
        val part1 = example.solvePart1()
        assertThat(part1).isEqualTo(24_000)

        val part2 = example.solvePart2()
        assertThat(part2).isEqualTo(45_000)
    }

    @Test
    fun `Actual answer`() {
        val part1 = real.solvePart1()
        assertThat(part1).isEqualTo(73_211)

        val part2 = real.solvePart2()
        assertThat(part2).isEqualTo(213_958)
    }
}
