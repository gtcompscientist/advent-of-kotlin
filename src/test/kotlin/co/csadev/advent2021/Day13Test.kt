/*
 * Copyright (c) 2021 by Charles Anderson
 */

package co.csadev.advent2021

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class Day13Test {

    private val input = """
        6,10
        0,14
        9,10
        0,3
        10,4
        4,11
        6,0
        6,12
        4,1
        0,13
        10,12
        3,4
        3,0
        8,4
        1,10
        2,14
        8,10
        9,0

        fold along y=7
        fold along x=5
    """.trimIndent().lines()

    private val example = Day13(input)
    private val real = Day13()

    @Test
    fun `Matches example`() {
        val part1 = example.solvePart1()
        Assertions.assertThat(part1).isEqualTo(17)

        val part2 = example.solvePart2()
        Assertions.assertThat(part2).isEqualTo(0)
    }

    @Test
    fun `Actual answer`() {
        val part1 = real.solvePart1()
        Assertions.assertThat(part1).isEqualTo(610)

        val part2 = real.solvePart2()
        Assertions.assertThat(part2).isEqualTo(0)
    }
}
