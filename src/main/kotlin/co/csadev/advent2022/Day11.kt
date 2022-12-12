/*
 * Copyright (c) 2022 by Charles Anderson
 */

/**
 * Advent of Code 2022, Day 11
 * Problem Description: http://adventofcode.com/2021/day/11
 */
package co.csadev.advent2022

import co.csadev.adventOfCode.BaseDay
import co.csadev.adventOfCode.Resources.resourceAsText

class Day11(override val input: String = resourceAsText("22day11.txt")) :
    BaseDay<String, Long, Long> {

    data class Monkey(
        var items: MutableList<Long>,
        val operation: (Long) -> Long,
        val divBy: Int,
        val trueMonkey: Int,
        val falseMonkey: Int,
        var inspectCount: Int = 0
    )

    private fun List<String>.toOperation(): (Long) -> Long {
        val op: (Long) -> Long = { old ->
            val part1 = if (this[0] == "old") old else this[0].toLong()
            val part2 = if (this[2] == "old") old else this[2].toLong()
            if (this[1] == "+") (part1 + part2) else (part1 * part2)
        }
        return op
    }

    override fun solvePart1() = solver(20, 3)

    override fun solvePart2() = solver(10_000)

    private fun solver(rounds: Int, worryResolver: Int = 1): Long {
        val monkeys = input.split("\n\n").map {
            val monkeyLines = it.lines()
            Monkey(
                monkeyLines[1].substringAfter(": ").split(", ").map { i -> i.toLong() }.toMutableList(),
                monkeyLines[2].substringAfter("new = ").split(" ").toOperation(),
                monkeyLines[3].substringAfter("divisible by ").toInt(),
                monkeyLines[4].substringAfter("monkey ").toInt(),
                monkeyLines[5].substringAfter("monkey ").toInt()
            )
        }

        val monkeyCount = monkeys.size
        val gcd = monkeys.map { e -> e.divBy.toLong() }.reduce { a, b -> a * b }
        repeat(rounds) {
            (0 until monkeyCount).forEach { i ->
                val m = monkeys[i]
                val itemCount = m.items.size
                repeat(itemCount) {
                    val item = m.items.removeAt(0)
                    val newWorry = m.operation(item) / worryResolver
                    val newMonkey = if (newWorry % m.divBy == 0L) m.trueMonkey else m.falseMonkey
                    m.inspectCount++
                    monkeys[newMonkey].items.add(if (worryResolver == 1) newWorry % gcd else newWorry)
                }
            }
        }

        return monkeys.sortedByDescending { it.inspectCount }.take(2)
            .run { this[0].inspectCount.toLong() * this[1].inspectCount.toLong() }
    }
}
