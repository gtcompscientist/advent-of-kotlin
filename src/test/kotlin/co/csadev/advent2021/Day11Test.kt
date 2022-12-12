/*
 * Copyright (c) 2021 by Charles Anderson
 */

package co.csadev.advent2021

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Day11Test {

    private val input = """
        5483143223
        2745854711
        5264556173
        6141336146
        6357385478
        4167524645
        2176841721
        6882881134
        4846848554
        5283751526
    """.trimIndent().lines()

    private val example = Day11(input)
    private val real = Day11()

    @Test
    fun `Matches example`() {
        val part1 = example.solvePart1()
        assertThat(part1).isEqualTo(1656)

        val part2 = example.solvePart2()
        assertThat(part2).isEqualTo(195)
    }

    @Test
    fun `Actual answer`() {
        val part1 = real.solvePart1()
        assertThat(part1).isEqualTo(1585)

        val part2 = real.solvePart2()
        assertThat(part2).isEqualTo(382)
    }
}
