/*
 * Copyright (c) 2022 by Charles Anderson
 */

package co.csadev.advent2022

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class Day25Test {

    private val input = """
        1=-0-2
        12111
        2=0=
        21
        2=01
        111
        20012
        112
        1=-1=
        1-12
        12
        1=
        122
    """.trimIndent().lines()

    private val example = Day25(input)
    private val real = Day25()

    @Test
    fun `Matches example`() {
        val part1 = example.solvePart1()
        Assertions.assertThat(part1).isEqualTo("2=-1=0")

        val part2 = example.solvePart2()
        Assertions.assertThat(part2).isEqualTo(0)
    }

    @Test
    fun `Actual answer`() {
        val part1 = real.solvePart1()
        Assertions.assertThat(part1).isEqualTo("2-1-110-=01-1-0-0==2")

        val part2 = real.solvePart2()
        Assertions.assertThat(part2).isEqualTo(0)
    }
}
