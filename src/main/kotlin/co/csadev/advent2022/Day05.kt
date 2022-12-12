/*
 * Copyright (c) 2022 by Charles Anderson
 */

/**
 * Advent of Code 2022, Day 5
 * Problem Description: http://adventofcode.com/2021/day/5
 */
package co.csadev.advent2022

import co.csadev.adventOfCode.BaseDay
import co.csadev.adventOfCode.Resources.resourceAsText

class Day05(override val input: String = resourceAsText("22day05.txt")) :
    BaseDay<String, String, String> {

    private val stacks: List<List<String>>
    private val instructions: List<List<Int>>

    init {
        val parts = input.split("\n\n")
        val tempStacks = parts.first().lines().map {
            it.chunked(4).map { s -> s.replace("[", "").replace("]", "").trim() }.toMutableList()
        }.dropLast(1)
        stacks = (0 until tempStacks.maxOf { it.size }).map { col ->
            tempStacks.mapNotNull { if (it.getOrNull(col).isNullOrBlank()) null else it[col] }
        }
        val tempInstr = parts[1].lines()
        instructions = tempInstr.map {
            val line = it.split(" ")
            listOf(line[1].toInt(), line[3].toInt() - 1, line[5].toInt() - 1)
        }
    }

    override fun solvePart1(): String {
        val boxes = stacks.map { it.toMutableList() }
        instructions.forEach { (number, from, to) ->
            repeat(number) {
                boxes[to].add(0, boxes[from].removeAt(0))
            }
        }
        return boxes.joinToString("") { it.first() }
    }

    override fun solvePart2(): String {
        val boxes = stacks.map { it.toMutableList() }
        instructions.forEach { (number, from, to) ->
            (number downTo 1).forEach { b ->
                boxes[to].add(0, boxes[from].removeAt(b - 1))
            }
        }
        return boxes.joinToString("") { it.first() }
    }
}
