/*
 * Copyright (c) 2021 by Charles Anderson
 */

package co.csadev.advent2021

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class Day21Test {

    private val input = """
        4
        8
    """.trimIndent().lines()

    private val example = Day21(input)
    private val real = Day21()

    @Test
    fun `Matches example`() {
        val part1 = example.solvePart1()
        Assertions.assertThat(part1).isEqualTo(739_785)

        val part2 = example.solvePart2()
        Assertions.assertThat(part2).isEqualTo(444_356_092_776_315L)
    }

    @Test
    fun `Actual answer`() {
        val part1 = real.solvePart1()
        Assertions.assertThat(part1).isEqualTo(920_580)

        val part2 = real.solvePart2()
        Assertions.assertThat(part2).isEqualTo(647_920_021_341_197L)
    }
}
