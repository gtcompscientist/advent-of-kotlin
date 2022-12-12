/*
 * Copyright (c) 2021 by Charles Anderson
 */

package co.csadev.advent2021

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class Day17Test {

    private val input = """
        20,30
        -10,-5
    """.trimIndent().lines()

    private val example = Day17(input)
    private val real = Day17()

    @Test
    fun `Matches example`() {
        val part1 = example.solvePart1()
        Assertions.assertThat(part1).isEqualTo(45)

        val part2 = example.solvePart2()
        Assertions.assertThat(part2).isEqualTo(112)
    }

    @Test
    fun `Actual answer`() {
        val part1 = real.solvePart1()
        Assertions.assertThat(part1).isEqualTo(5050)

        val part2 = real.solvePart2()
        Assertions.assertThat(part2).isEqualTo(2223)
    }
}
