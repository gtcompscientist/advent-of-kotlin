/*
 * Copyright (c) 2022 by Charles Anderson
 */

package co.csadev.advent2022

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Day06Test {

    private val input =
        "mjqjpqmgbljsphdztnvjfqwrcgsmlb" // : first marker after 4
    // "bvwbjplbgvbhsrlpgdmjqwftvncz"//: first marker after character 5
    // nppdvjthqldpwncqszvftbrmjlhg: first marker after character 6
    // nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg: first marker after character 10
    // zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw

    private val example = Day06(input)
    private val real = Day06()

    @Test
    fun `Matches example`() {
        val part1 = example.solvePart1()
        assertThat(part1).isEqualTo(7)

        val part2 = example.solvePart2()
        assertThat(part2).isEqualTo(19)
    }

    @Test
    fun `Actual answer`() {
        val part1 = real.solvePart1()
        assertThat(part1).isEqualTo(1892)

        val part2 = real.solvePart2()
        assertThat(part2).isEqualTo(2313)
    }
}
