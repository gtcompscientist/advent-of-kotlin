/*
 * Copyright (c) 2021 by Charles Anderson
 */

package co.csadev.advent2021

import co.csadev.advent2022.Day02
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Day02Test {

    private val input = listOf(
        "forward 5",
        "down 5",
        "forward 8",
        "up 3",
        "down 8",
        "forward 2"
    )

    private val example = Day02(input)
    private val real = Day02()

    @Test
    fun `Matches example`() {
        val part1 = example.solvePart1()
        assertThat(part1).isEqualTo(150)

        val part2 = example.solvePart2()
        assertThat(part2).isEqualTo(900)
    }

    @Test
    fun `Actual answer`() {
        val part1 = real.solvePart1()
        assertThat(part1).isEqualTo(1451208)

        val part2 = real.solvePart2()
        assertThat(part2).isEqualTo(1620141160)
    }
}
