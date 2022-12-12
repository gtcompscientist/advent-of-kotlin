/*
 * Copyright (c) 2021 by Charles Anderson
 */

package co.csadev.advent2021

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class Day24Test {

    private val real = Day24()

    @Test
    fun `Actual answer`() {
        val part1 = real.solvePart1()
        Assertions.assertThat(part1).isEqualTo(16931171414113)
        val part2 = real.solvePart2()
        Assertions.assertThat(part2).isEqualTo(79997391969649)
    }
}
