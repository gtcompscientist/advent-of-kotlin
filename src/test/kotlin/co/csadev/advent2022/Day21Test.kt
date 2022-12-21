/*
 * Copyright (c) 2022 by Charles Anderson
 */

package co.csadev.advent2022

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class Day21Test {

    private val input = """
        root: pppw + sjmn
        dbpl: 5
        cczh: sllz + lgvd
        zczc: 2
        ptdq: humn - dvpt
        dvpt: 3
        lfqf: 4
        humn: 5
        ljgn: 2
        sjmn: drzm * dbpl
        sllz: 4
        pppw: cczh / lfqf
        lgvd: ljgn * ptdq
        drzm: hmdt - zczc
        hmdt: 32
    """.trimIndent().lines()

    private val example = Day21(input)
    private val real = Day21()

    @Test
    fun `Matches example`() {
        val part1 = example.solvePart1()
        Assertions.assertThat(part1).isEqualTo(152L)

        val part2 = example.solvePart2()
        Assertions.assertThat(part2).isEqualTo(301)
    }

    @Test
    fun `Actual answer`() {
        val part1 = real.solvePart1()
        Assertions.assertThat(part1).isEqualTo(84244467642604L)

        val part2 = real.solvePart2()
        Assertions.assertThat(part2).isEqualTo(3759569926192L)
    }
}
