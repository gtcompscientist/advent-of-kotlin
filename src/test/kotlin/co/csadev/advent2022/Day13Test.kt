/*
 * Copyright (c) 2022 by Charles Anderson
 */

package co.csadev.advent2022

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class Day13Test {

    private val input = """
        [1,1,3,1,10]
        [1,1,5,1,10]

        [[1],[2,3,4]]
        [[1],4]

        [9]
        [[8,7,6]]

        [[4,4],4,4]
        [[4,4],4,4,4]

        [7,7,7,7]
        [7,7,7]

        []
        [3]

        [[[]]]
        [[]]

        [1,[2,[3,[4,[5,6,7]]]],8,10]
        [1,[2,[3,[4,[5,6,0]]]],8,10]
    """.trimIndent()

    private val example = Day13(input)
    private val real = Day13()

    @Test
    fun `Matches example`() {
        val part1 = example.solvePart1()
        Assertions.assertThat(part1).isEqualTo(13)

        val part2 = example.solvePart2()
        Assertions.assertThat(part2).isEqualTo(140)
    }

    @Test
    fun `Actual answer`() {
        val part1 = real.solvePart1()
        Assertions.assertThat(part1).isEqualTo(5185)

        val part2 = real.solvePart2()
        Assertions.assertThat(part2).isEqualTo(23751)
    }
}
