/*
 * Copyright (c) 2022 by Charles Anderson
 */

package co.csadev.advent2022

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Day04Test {

    private val input = """
            2-4,6-8
            2-3,4-5
            5-7,7-9
            2-8,3-7
            6-6,4-6
            2-6,4-8
    """.trimIndent().lines()

    private val example = Day04(input)
    private val real = Day04()

    @Test
    fun `Matches example`() {
        val part1 = example.solvePart1()
        assertThat(part1).isEqualTo(2)

        val part2 = example.solvePart2()
        assertThat(part2).isEqualTo(4)
    }

    @Test
    fun `Actual answer`() {
        val part1 = real.solvePart1()
        assertThat(part1).isEqualTo(605)

        val part2 = real.solvePart2()
        assertThat(part2).isEqualTo(914)
    }
}
