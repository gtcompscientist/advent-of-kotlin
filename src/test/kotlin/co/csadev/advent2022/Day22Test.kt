/*
 * Copyright (c) 2022 by Charles Anderson
 */

package co.csadev.advent2022

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class Day22Test {

    private val input = """
        ...#
        .#..
        #...
        ....
...#.......#
........#...
..#....#....
..........#.
        ...#....
        .....#..
        .#......
        ......#.

10R5L5R10L4R5L5
    """.trimIndent().lines()

    private val example = Day22(input)
    private val real = Day22()

    @Test
    fun `Matches example`() {
        val part1 = example.solvePart1()
        Assertions.assertThat(part1).isEqualTo(6032)

        example.sideMapping = listOf(
            (8..11) to (0..3),
            (12..15) to (8..11),
            (8..11) to (4..7),
            (8..11) to (8..11),
            (0..3) to (4..7),
            (4..7) to (4..7),
        )
        example.sideSize = 4

        val part2 = example.solvePart2()
        Assertions.assertThat(part2).isEqualTo(5031)
    }

    @Test
    fun `Actual answer`() {
        val part1 = real.solvePart1()
        Assertions.assertThat(part1).isEqualTo(43466)

        val part2 = real.solvePart2()
        Assertions.assertThat(part2).isEqualTo(162155)
    }
}
