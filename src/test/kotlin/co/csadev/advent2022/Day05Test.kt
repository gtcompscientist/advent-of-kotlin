/*
 * Copyright (c) 2022 by Charles Anderson
 */

package co.csadev.advent2022

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Day05Test {

    private val input = """
    [D]    
[N] [C]    
[Z] [M] [P]
 1   2   3 

move 1 from 2 to 1
move 3 from 1 to 3
move 2 from 2 to 1
move 1 from 1 to 2
    """.trimIndent()

    private val example = Day05(input)
    private val real = Day05()

    @Test
    fun `Matches example`() {
        val part1 = example.solvePart1()
        assertThat(part1).isEqualTo("CMZ")

        val part2 = example.solvePart2()
        assertThat(part2).isEqualTo("MCD")
    }

    @Test
    fun `Actual answer`() {
        val part1 = real.solvePart1()
        assertThat(part1).isEqualTo("QNNTGTPFN")

        val part2 = real.solvePart2()
        assertThat(part2).isEqualTo("GGNPJBTTR")
    }
}
