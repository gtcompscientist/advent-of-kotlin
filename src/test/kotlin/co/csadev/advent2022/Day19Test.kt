/*
 * Copyright (c) 2022 by Charles Anderson
 */

package co.csadev.advent2022

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class Day19Test {

    private val input = """
        Blueprint 1: Each ore robot costs 4 ore. Each clay robot costs 2 ore. Each obsidian robot costs 3 ore and 14 clay. Each geode robot costs 2 ore and 7 obsidian.
        Blueprint 2: Each ore robot costs 2 ore. Each clay robot costs 3 ore. Each obsidian robot costs 3 ore and 8 clay. Each geode robot costs 3 ore and 12 obsidian.
    """.trimIndent().lines()

    private val example = Day19(input)
    private val real = Day19()

    @Test
    fun `Matches example`() {
        val part1 = example.solvePart1()
        Assertions.assertThat(part1).isEqualTo(33)

        val part2 = example.solvePart2()
        Assertions.assertThat(part2).isEqualTo(3348)
    }

    @Test
    fun `Actual answer`() {
        val part1 = real.solvePart1()
        Assertions.assertThat(part1).isEqualTo(817)

        val part2 = real.solvePart2()
        Assertions.assertThat(part2).isEqualTo(4216)
    }
}
