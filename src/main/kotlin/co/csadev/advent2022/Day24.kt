/**
 * Copyright (c) 2022 by Charles Anderson
 * Advent of Code 2022, Day 24
 * Problem Description: http://adventofcode.com/2021/day/24
 */
package co.csadev.advent2022

import co.csadev.adventOfCode.BaseDay
import co.csadev.adventOfCode.Resources.resourceAsList

/**
 * The ALU is a four-dimensional processing unit: it has integer variables w, x, y, and z.
 * These variables all start with the value 0. The ALU also supports six instructions:
 *
 * inp a - Read an input value and write it to variable a.
 * add a b - Add the value of a to the value of b, then store the result in variable a.
 * mul a b - Multiply the value of a by the value of b, then store the result in variable a.
 * div a b - Divide the value of a by the value of b, truncate the result to an integer, then store
 *   the result in variable a. (Here, "truncate" means to round the value toward zero.)
 * mod a b - Divide the value of a by the value of b, then store the remainder in variable a.
 *   (This is also called the modulo operation.)
 * eql a b - If the value of a and b are equal, then store the value 1 in variable a.
 *   Otherwise, store the value 0 in variable a.
 *
 * In all of these instructions, a and b are placeholders a will always be the variable
 *   where the result of the operation is stored (one of w, x, y, or z), while b can be either
 *   a variable or a number. Numbers can be positive or negative, but will always be integers.
 */

class Day24(override val input: List<String> = resourceAsList("22day24.txt")) :
    BaseDay<List<String>, Int, Int> {

    override fun solvePart1() = 0

    override fun solvePart2() = 0
}
