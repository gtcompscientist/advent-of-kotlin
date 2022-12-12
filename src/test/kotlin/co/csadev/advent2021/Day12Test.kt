/*
 * Copyright (c) 2021 by Charles Anderson
 */

package co.csadev.advent2021

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class Day12Test {

    private val input = """
        start-A
        start-b
        A-c
        A-b
        b-d
        A-end
        b-end
    """.trimIndent().lines()

    private val example = Day12(input)
    private val real = Day12()

    @Test
    fun `Matches example`() {
        val part1 = example.solvePart1()
        Assertions.assertThat(part1).isEqualTo(10)

        val part2 = example.solvePart2()
        Assertions.assertThat(part2).isEqualTo(36)
    }

    @Test
    fun `Actual answer`() {
        val part1 = real.solvePart1()
        Assertions.assertThat(part1).isEqualTo(5212)

        val part2 = real.solvePart2()
        Assertions.assertThat(part2).isEqualTo(134_862)
    }
}
