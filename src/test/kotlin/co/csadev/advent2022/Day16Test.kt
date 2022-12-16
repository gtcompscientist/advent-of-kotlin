/*
 * Copyright (c) 2022 by Charles Anderson
 */

package co.csadev.advent2022

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class Day16Test {

    private val input = """     
           Valve AA has flow rate=0; tunnels lead to valves DD, II, BB
           Valve BB has flow rate=13; tunnels lead to valves CC, AA
           Valve CC has flow rate=2; tunnels lead to valves DD, BB
           Valve DD has flow rate=20; tunnels lead to valves CC, AA, EE
           Valve EE has flow rate=3; tunnels lead to valves FF, DD
           Valve FF has flow rate=0; tunnels lead to valves EE, GG
           Valve GG has flow rate=0; tunnels lead to valves FF, HH
           Valve HH has flow rate=22; tunnel leads to valve GG
           Valve II has flow rate=0; tunnels lead to valves AA, JJ
           Valve JJ has flow rate=21; tunnel leads to valve II
    """.trimIndent().lines()

    private val example = Day16(input)
    private val real = Day16()

    @Test
    fun `Matches example`() {
        val part1 = example.solvePart1()
        Assertions.assertThat(part1).isEqualTo(1651)

        val part2 = example.solvePart2()
        Assertions.assertThat(part2).isEqualTo(1707)
    }

    @Test
    fun `Actual answer`() {
        val part1 = real.solvePart1()
        Assertions.assertThat(part1).isEqualTo(1880)

        val part2 = real.solvePart2()
        Assertions.assertThat(part2).isEqualTo(2520)
    }
}
