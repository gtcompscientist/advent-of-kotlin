/*
 * Copyright (c) 2021 by Charles Anderson
 */

package co.csadev.advent2021

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class Day16Test {

    private val input = "D2FE28"

    private val example = Day16(input)
    private val real = Day16()

    @Test
    fun `Matches example`() {
        val part1 = example.solvePart1()
        Assertions.assertThat(part1).isEqualTo(6)

        val part2 = example.solvePart2()
        Assertions.assertThat(part2).isEqualTo(2021L)
    }

    @Test
    fun `Actual answer`() {
        val part1 = real.solvePart1()
        Assertions.assertThat(part1).isEqualTo(821)

        val part2 = real.solvePart2()
        Assertions.assertThat(part2).isEqualTo(2056021084691L)
    }
}
