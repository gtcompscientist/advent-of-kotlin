/*
 * Copyright (c) 2022 by Charles Anderson
 */

package co.csadev.hannukah5783

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class Day04Test {

    private val real = Day04()

    @Test
    fun `Actual answer`() {
        val part1 = real.solvePart1()
        Assertions.assertThat(part1).isEqualTo("718-649-9036")
    }
}
