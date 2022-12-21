/**
 * Copyright (c) 2022 by Charles Anderson
 * Advent of Code 2022, Day 21
 * Problem Description: http://adventofcode.com/2021/day/21
 */
package co.csadev.advent2022

import co.csadev.adventOfCode.BaseDay
import co.csadev.adventOfCode.Resources.resourceAsList
import java.math.BigInteger
import kotlin.math.abs

class Day21(override val input: List<String> = resourceAsList("22day21.txt")) :
    BaseDay<List<String>, Long, Long> {

    companion object {
        private const val ROOT = "root"
        private const val HUMAN = "humn"
    }

    private data class Monkey(var name: String, var yell: Long?, val wait1: String?, var operator: String?, val wait2: String?) {
        companion object {
            fun fromString(input: List<String>): Monkey {
                return Monkey(input[0], input.getOrNull(1)?.toLongOrNull(), input.getOrNull(1), input.getOrNull(2), input.getOrNull(3))
            }
        }
    }

    private class MonkeyYell(var monkeys: MutableList<Monkey> = mutableListOf()) : Exception("Yelling Monkeys!")

    private val monkeys =
        input.map { it.replace(":", "").split(" ") }
            .associate { it[0] to Monkey.fromString(it) }
            .toMutableMap()

    override fun solvePart1() = monkeys[ROOT]?.yelled ?: 0L

    var cache: MutableMap<String, String?> = HashMap()
    override fun solvePart2(): Long {
        //Drop the first because it's HUMAN
        monkeys[ROOT]!!.operator = "="
        monkeys[HUMAN] = Monkey.fromString(listOf(HUMAN))

        try {
            monkeys[ROOT]!!.yelled
        } catch (yelling: MonkeyYell) {
            return yelling.monkeys.solveYelling()
        }
        return 0L
    }

    private fun List<Monkey>.solveYelling(): Long {
        var longCompute = mutableListOf<Any>(HUMAN)
        var humanChain = HUMAN

        drop(1).forEach {
            val nest = mutableListOf<Any>()
            if (it.wait1 == humanChain) {
                nest.add(longCompute)
                nest.add(it.operator!!)
                nest.add(monkeys[it.wait2]!!.yelled)
            } else {
                nest.add(monkeys[it.wait1]!!.yelled)
                nest.add(it.operator!!)
                nest.add(longCompute)
            }
            longCompute = nest
            if (it.name == ROOT) {
                // Do some magic to unroll this and return
                var computed = 0L
                while (true) {
                    if (longCompute.any { v -> v == HUMAN })
                        return computed
                    val unsolvedIdx = longCompute.indexOfFirst { l -> l as? List<Any> != null }
                    val solvedIdx = if (unsolvedIdx == 0) 2 else 0
                    val solved = longCompute[solvedIdx] as Long
                    println("$computed = ${if (solvedIdx == 2) { "[HUMAN] ${longCompute[1]} $solved" } else { "$solved ${longCompute[1]} [HUMAN]" }}")
                    computed = when (longCompute[1]) {
                        "=" -> solved
                        "+" -> computed - solved
                        "-" -> if (solvedIdx == 2) computed + solved else solved - computed
                        "*" -> computed / solved
                        "/" -> if (solvedIdx == 2) computed * solved else solved / computed
                        else -> 0L
                    }
                    longCompute = longCompute[unsolvedIdx] as MutableList<Any>
                }
            }
            humanChain = it.name
        }
        return 0L
    }

    private val Monkey.yelled: Long
        get() {
            return try {
                yell ?: compute(monkeys[wait1]!!.yelled, operator!!, monkeys[wait2]!!.yelled)
            } catch (m: MonkeyYell) {
                m.monkeys.add(this)
                throw m
            } catch (ex: Exception) {
                val yelling = MonkeyYell()
                yelling.monkeys.add(this)
                throw yelling
            }
        }

    private val String.oppOperator: String
        get() = when (this) {
            "+" -> "-"
            "-" -> "+"
            "*" -> "/"
            "/" -> "*"
            else -> "="
        }

    private fun compute(m1: Long, operator: String, m2: Long): Long {
        return when (operator) {
            "+" -> m1 + m2
            "-" -> m1 - m2
            "*" -> m1 * m2
            "/" -> m1 / m2
            "=" -> abs(m1 - m2)
            else -> throw Exception("Unknown Operator: $operator")
        }
    }
}
