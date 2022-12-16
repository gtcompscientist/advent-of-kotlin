/**
 * Copyright (c) 2021 by Charles Anderson
 * Advent of Code 2021, Day 24
 * Problem Description: http://adventofcode.com/2021/day/24
 */
package co.csadev.advent2021

import co.csadev.adventOfCode.BaseDay
import co.csadev.adventOfCode.Resources.resourceAsList

/**
 * The ALU is a four-dimensional processing unit: it has integer variables w, x, y, and z.
 * These variables all start with the value 0. The ALU also supports six instructions:
 *
 * inp a - Read an input value and write it to variable a.
 * add a b - Add the value of a to the value of b, then store the result in variable a.
 * mul a b - Multiply the value of a by the value of b, then store the result in variable a.
 * div a b - Divide the value of a by the value of b, truncate the result to an integer,
 *   then store the result in variable a. (Here, "truncate" means to round the value toward zero.)
 * mod a b - Divide the value of a by the value of b, then store the remainder in variable a.
 *   (This is also called the modulo operation.)
 * eql a b - If the value of a and b are equal, then store the value 1 in variable a.
 *   Otherwise, store the value 0 in variable a.
 *
 * In all of these instructions, a and b are placeholders a will always be the variable where
 *   the result of the operation is stored (one of w, x, y, or z), while b can be either a variable
 *   or a number. Numbers can be positive or negative, but will always be integers.
 */

class Day24(override val input: List<String> = resourceAsList("21day24.txt")) :
    BaseDay<List<String>, String?, String?> {
    override fun solvePart1() = solve(input, LongArray(4), 0, 1).also {
        println(it)
    }

    override fun solvePart2() = solve(input, LongArray(4), 0, -1).also { println(it) }

    var cache: MutableMap<String, String?> = HashMap()
    private fun cacheSolve(ins: List<String>, reg: LongArray, ip: Int, dir: Int): String? {
        val key = "${reg.contentToString()}|$ip|$dir"
        return cache.getOrPut(key) { solve(ins, reg, ip, dir) }
    }

    @Suppress("ReturnCount", "CyclomaticComplexMethod")
    fun solve(ins: List<String>, reg: LongArray, ip: Int, dir: Int): String? {
        if (reg[3] > 1_000_000) return null
        if (ip == ins.size) return if (reg[3] == 0L) "" else null
        val next = ins[ip]
        val args = next.split(" ").toTypedArray()
        when (args[0]) {
            "inp" -> {
                val iRange = if (dir > 0) (1..9) else (9 downTo 1)
                for (i in iRange) {
                    val r2 = reg.clone()
                    r2[args[1][0] - 'w'] = i.toLong()
                    val res = cacheSolve(ins, r2, ip + 1, dir)
                    if (res != null) {
                        return "$i$res"
                    }
                }
                return null
            }
            "add" -> reg[args[1][0] - 'w'] += arg2(reg, args)
            "mul" -> reg[args[1][0] - 'w'] *= arg2(reg, args)
            "div" -> reg[args[1][0] - 'w'] /= arg2(reg, args)
            "mod" -> reg[args[1][0] - 'w'] %= arg2(reg, args)
            "eql" -> reg[args[1][0] - 'w'] = if (reg[args[1][0] - 'w'] == arg2(reg, args)) 1 else 0
            else -> println(args[0])
        }
        return cacheSolve(ins, reg, ip + 1, dir)
    }

    private fun arg2(reg: LongArray, args: Array<String>) =
        if (args[2][0].isLetter()) reg[args[2][0] - 'w'] else args[2].toInt().toLong()
}
