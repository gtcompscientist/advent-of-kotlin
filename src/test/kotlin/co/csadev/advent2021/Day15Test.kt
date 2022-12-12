/*
 * Copyright (c) 2021 by Charles Anderson
 */

package co.csadev.advent2021

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class Day15Test {

    private val input = """
        1163751742
        1381373672
        2136511328
        3694931569
        7463417111
        1319128137
        1359912421
        3125421639
        1293138521
        2311944581
    """.trimIndent().lines()

    private val example = Day15(input)
    private val real = Day15()

    @Test
    fun `Matches example`() {
        val part1 = example.solvePart1()
        Assertions.assertThat(part1).isEqualTo(40)

        val part2 = example.solvePart2()
        Assertions.assertThat(part2).isEqualTo(315)
    }

    @Test
    fun `Actual answer`() {
        val part1 = real.solvePart1()
        Assertions.assertThat(part1).isEqualTo(755)

        val part2 = real.solvePart2()
        Assertions.assertThat(part2).isEqualTo(3016)
    }
}
