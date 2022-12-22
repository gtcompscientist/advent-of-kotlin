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

    private data class Monkey(var name: String, var yell: Long?, val wait1: String?, var operator: Operator?, val wait2: String?) {
        fun contains(name: String) = wait1 == name || wait2 == name
    }

    private enum class Operator(val op: String, val compute: (Long, Long) -> Long, val solve: (Long, Long, Int) -> Long) {
        Plus("+", { l, r -> l + r }, { curr, l, _ -> curr - l }),
        Minus("-", { l, r -> l - r }, { curr, l, idx -> if (idx == 2) (l + curr) else (l - curr) }),
        Mult("*", { l, r -> l * r }, { curr, l, _ -> curr / l }),
        Div("/", { l, r -> l / r }, { curr, l, idx -> if (idx == 2) (l * curr) else (l / curr) }),
        Equal("=", { l, r -> abs(l - r) }, { curr, l, _ -> curr + l });

        override fun toString() = op
    }

    private fun List<String>.asMonkey(): Monkey =
        Monkey(this[0], getOrNull(1)?.toLongOrNull(), getOrNull(1), getOrNull(2)?.toOperator(), getOrNull(3))

    private fun String.toOperator() = Operator.values().firstOrNull { it.op == this }

    private val monkeys = input.map { it.replace(":", "").split(" ") }
        .associate { it[0] to it.asMonkey() }

    override fun solvePart1() = monkeys[ROOT]!!.yelled

    override fun solvePart2(): Long {
        monkeys[ROOT]!!.operator = Operator.Equal
        monkeys[HUMAN]!!.yell = null
        return generateSequence(monkeys[HUMAN]) { m ->
            monkeys.entries.firstOrNull { (_, e) -> e.contains(m.name) }?.value
        }.drop(1).stopYelling()
    }

    private fun Sequence<Monkey>.stopYelling(): Long {
        var humanChain = HUMAN
        return fold(emptyList<Any?>()) { acc, monkey ->
            if (monkey.wait1 == humanChain) {
                listOf(acc, monkey.operator, monkeys[monkey.wait2]?.yelled)
            } else {
                listOf(monkeys[monkey.wait1]?.yelled, monkey.operator, acc)
            }.also { humanChain = monkey.name }
        }.solve()
    }

    private val Monkey.yelled: Long
        get() = yell ?: operator!!.compute(monkeys[wait1]!!.yelled, monkeys[wait2]!!.yelled)

    private fun List<Any?>.solve(): Long {
        // Do some magic to unroll this and return
        var computed = 0L
        var currEq = this
        while (currEq.isNotEmpty()) {
            val unsolvedIdx = if (currEq[0] is List<*>) 0 else 2
            val solvedIdx = if (unsolvedIdx == 0) 2 else 0
            val solved = currEq[solvedIdx] as Long
            println("$computed = ${if (solvedIdx == 2) ("[ME] ${currEq[1]} $solved") else ("$solved ${currEq[1]} [ME]")}")
            computed = (currEq[1] as Operator).solve(computed, solved, solvedIdx)
            currEq = currEq[unsolvedIdx] as List<Any?>
        }
        return computed
    }
}
