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

    private fun List<Int>.findPath(time: Int, pos: String, pos2: String? = null) {
        val key = "$time$pos$pos2"
        val currSum = sum()
        if (seen.getOrDefault(key, -1) >= currSum) {
            return
        }
        seen[key] = currSum
        if (time == maxTime) {
            max = max(max, currSum)
        } else if (open.all { it.value }) {
            (this + currFlow).findPath(time + 1, pos, pos2)
        } else {
            searchPath(time, pos, pos2)
        }
    }

    private fun List<Int>.searchPath(time: Int, pos: String, pos2: String?, first: Boolean = true) {
        val opening = if (!first && pos2 != null) pos2 else pos
        if (open[opening] != true && flows.getOrDefault(opening, 0) > 0) {
            open[opening] = true
            addDepth(currFlow, pos, pos2, time, first)
            open[opening] = false
        }
        val currentFlow = currFlow
        valves[opening]?.forEach { p ->
            addDepth(currentFlow, if (first) p else pos, if (first) pos2 else p, time, first)
        }
    }

    private fun List<Int>.addDepth(startFlow: Int, pos1: String, pos2: String?, time: Int, first: Boolean) {
        if (pos2 != null && first) {
            searchPath(time, pos1, pos2, false)
        } else {
            (this + startFlow).findPath(time + 1, pos1, if (first) null else pos2)
        }
    }
}
