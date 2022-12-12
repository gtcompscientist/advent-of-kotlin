/*
 * Copyright (c) 2022 by Charles Anderson
 */

package co.csadev.advent2022

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class Day12Test {

    private val input = """
        Sabqponm
        abcryxxl
        accszExk
        acctuvwj
        abdefghi
    """.trimIndent().lines()

    private val example = Day12(input)
    private val real = Day12()

    @Test
    fun `Matches example`() {
        val part1 = example.solvePart1()
        Assertions.assertThat(part1).isEqualTo(31)

        val part2 = example.solvePart2()
        Assertions.assertThat(part2).isEqualTo(29)
    }

    @Test
    fun `Actual answer`() {
        val part1 = real.solvePart1()
        Assertions.assertThat(part1).isEqualTo(440)

        val part2 = real.solvePart2()
        Assertions.assertThat(part2).isEqualTo(439)
    }
}
