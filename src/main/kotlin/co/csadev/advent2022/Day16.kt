/**
 * Copyright (c) 2022 by Charles Anderson
 * Advent of Code 2022, Day 16
 * Problem Description: http://adventofcode.com/2021/day/16
 */
package co.csadev.advent2022

import co.csadev.adventOfCode.BaseDay
import co.csadev.adventOfCode.Resources.resourceAsList
import kotlin.math.max

class Day16(override val input: List<String> = resourceAsList("22day16.txt")) :
    BaseDay<List<String>, Int, Int> {

    private val flows = mutableMapOf<String, Int>()
    private val valves = mutableMapOf<String, List<String>>()
    private val open = mutableMapOf<String, Boolean>()

    init {
        @Suppress("DestructuringDeclarationWithTooManyEntries")
        input.map {
            val (v, t) = it.split("; ")
            val (_, name, _, _, rate) = v.replace("rate=", "").split(" ")
            flows[name] = rate.toInt()
            val currV = t.split("valve")[1].replace("s ", "").trim().split(", ")
            valves[name] = currV
            open[name] = false
        }
    }

    private val seen = mutableMapOf<String, Int>()
    private var max = 0
    private var maxTime = 30

    override fun solvePart1(): Int {
        seen.clear()
        max = 0

        listOf(0).findPath(1, "AA")
        return max
    }

    override fun solvePart2(): Int {
        seen.clear()
        max = 0
        maxTime = 26

        listOf(0).findPath(1, "AA", "AA")
        return max
    }

    private val currFlow: Int
        get() = open.filter { it.value }.mapNotNull { flows[it.key] }.sum()

    private fun List<Int>.findPath(time: Int, vararg pos: String) {
        val key = "$time${pos.joinToString("")}"
        val currSum = sum()
        if (seen.getOrDefault(key, -1) >= currSum) {
            return
        }
        seen[key] = currSum
        if (time == maxTime) {
            max = max(max, currSum)
        } else if (open.all { it.value }) {
            (this + currFlow).findPath(time + 1, *pos)
        } else {
            searchPath(time, 0, *pos)
        }
    }

    private fun List<Int>.searchPath(time: Int, offset: Int, vararg pos: String) {
        val opening = pos.getOrNull(offset) ?: return
        if (open[opening] != true && flows.getOrDefault(opening, 0) > 0) {
            open[opening] = true
            addDepth(currFlow, time, offset + 1, *pos)
            open[opening] = false
        }
        val currentFlow = currFlow
        valves[opening]?.forEach { p ->
            val subSearch = pos.toMutableList().apply { this[offset] = p }
            addDepth(currentFlow, time, offset + 1, *subSearch.toTypedArray())
        }
    }

    private fun List<Int>.addDepth(startFlow: Int, time: Int, offset: Int, vararg pos: String) {
        val pos2 = pos.getOrNull(offset)
        if (pos2 != null && offset > 0) {
            searchPath(time, offset, *pos)
        } else {
            (this + startFlow).findPath(time + 1, *pos)
        }
    }
}
