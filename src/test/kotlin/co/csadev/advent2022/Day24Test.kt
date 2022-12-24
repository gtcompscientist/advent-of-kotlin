/*
 * Copyright (c) 2022 by Charles Anderson
 */

package co.csadev.advent2022

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class Day24Test {

    private val input = """
        #.######
        #>>.<^<#
        #.<..<<#
        #>v.><>#
        #<^v^^>#
        ######.#
    """.trimIndent().lines()

    @Test
    fun `Matches example`() {
        val example = Day24(input)
        val part1 = example.solvePart1()
        Assertions.assertThat(part1).isEqualTo(18)

        val part2 = example.solvePart2()
        Assertions.assertThat(part2).isEqualTo(54)
    }

    @Test
    fun `Actual answer`() {
        val real = Day24()
        val part1 = real.solvePart1()
        Assertions.assertThat(part1).isEqualTo(297)

        val part2 = real.solvePart2()
        Assertions.assertThat(part2).isEqualTo(856)
    }
}
