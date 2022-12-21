/**
 * Copyright (c) 2022 by Charles Anderson
 * Advent of Code 2022, Day 21
 * Problem Description: http://adventofcode.com/2021/day/21
 */
package co.csadev.advent2022

import co.csadev.adventOfCode.BaseDay
import co.csadev.adventOfCode.Resources.resourceAsList
import kotlin.math.abs

class Day21(override val input: List<String> = resourceAsList("22day21.txt")) :
    BaseDay<List<String>, Long, Long> {

    companion object {
        private const val ROOT = "root"
        private const val HUMAN = "humn"
    }

    private data class Monkey(var name: String, var yell: Long?, val wait1: String?, var operator: Operator?, val wait2: String?)

    private enum class Operator(val op: String, val compute: (Long, Long) -> Long, val solve: (Long, Long, Int) -> Long) {
        Plus("+", { l, r -> l + r }, { curr, l, _ -> curr - l }),
        Minus("-", { l, r -> l - r }, { curr, l, idx -> if (idx == 2) (l + curr) else (l - curr) }),
        Mult("*", { l, r -> l * r }, { curr, l, _ -> curr / l }),
        Div("/", { l, r -> l / r }, { curr, l, idx -> if (idx == 2) (l * curr) else (l / curr) }),
        Equal("=", { l, r -> abs(l - r) }, { curr, l, _ -> curr + l })
    }

    private fun List<String>.asMonkey(): Monkey =
        Monkey(this[0], getOrNull(1)?.toLongOrNull(), getOrNull(1), getOrNull(2)?.toOperator(), getOrNull(3))

    private fun String.toOperator() = Operator.values().firstOrNull { it.op == this }

    private class MonkeyYell(ex: Exception? = null, var monkeys: MutableList<Monkey> = mutableListOf()) :
        Exception("Yelling Monkeys! ${ex?.message}")

    private val monkeys = input.map { it.replace(":", "").split(" ") }
        .associate { it[0] to it.asMonkey() }
        .toMutableMap()

    override fun solvePart1() = monkeys[ROOT]?.yelled ?: throw MonkeyYell()

    override fun solvePart2(): Long {
        // Drop the first because it's HUMAN
        monkeys[ROOT]!!.operator = Operator.Equal
        monkeys[HUMAN] = listOf(HUMAN).asMonkey()

        return try {
            monkeys[ROOT]!!.yelled
        } catch (yelling: MonkeyYell) {
            yelling.monkeys.stopYelling()
        }
    }

    private fun List<Monkey>.stopYelling(): Long {
        var longCompute = mutableListOf<Any?>(HUMAN)
        var humanChain = HUMAN

        drop(1).forEach {
            val nest = mutableListOf<Any?>()
            if (it.wait1 == humanChain) {
                nest.addAll(listOf(longCompute, it.operator, monkeys[it.wait2]?.yelled))
            } else {
                nest.addAll(listOf(monkeys[it.wait1]?.yelled, it.operator, longCompute))
            }
            longCompute = nest
            humanChain = it.name
        }
        return longCompute.solve()
    }

    @Suppress("TooGenericExceptionCaught")
    private val Monkey.yelled: Long
        get() = try {
            yell ?: operator!!.compute(monkeys[wait1]!!.yelled, monkeys[wait2]!!.yelled)
        } catch (m: MonkeyYell) {
            throw m.also { it.monkeys.add(this) }
        } catch (ex: NullPointerException) {
            throw MonkeyYell(ex).also { it.monkeys.add(this) }
        }

    @Suppress("UNCHECKED_CAST")
    private fun MutableList<Any?>.solve(): Long {
        // Do some magic to unroll this and return
        var computed = 0L
        var longCompute = this
        while (longCompute.none { v -> v == HUMAN }) {
            val unsolvedIdx = longCompute.indexOfFirst { l -> l as? List<*> != null }
            val solvedIdx = if (unsolvedIdx == 0) 2 else 0
            val solved = longCompute[solvedIdx] as Long
            //println("$computed = ${if (solvedIdx == 2) ("[ME] ${longCompute[1]} $solved") else ("$solved ${longCompute[1]} [ME]")}")
            computed = (longCompute[1] as Operator).solve(computed, solved, solvedIdx)
            longCompute = longCompute[unsolvedIdx] as MutableList<Any?>
        }
        return computed
    }
}
