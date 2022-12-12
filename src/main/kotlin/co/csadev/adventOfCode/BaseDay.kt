/*
 * Copyright (c) 2021 by Charles Anderson
 */

package co.csadev.adventOfCode

interface BaseDay<Input, Output1, Output2> {
    val input: Input
    fun solvePart1(): Output1
    fun solvePart2(): Output2
}
