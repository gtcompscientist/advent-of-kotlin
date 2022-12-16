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

        findPath(1, "AA", listOf(0))
        return max
    }

    override fun solvePart2(): Int {
        seen.clear()
        max = 0
        maxTime = 26

        findPath(1, "AA", listOf(0), "AA")
        return max
    }

    private val currFlow: Int
        get() = open.filter { it.value }.mapNotNull { flows[it.key] }.sum()

    private fun findPath(time: Int, position: String, flow: List<Int>, position2: String? = null) {
        val key = "$time$position$position2"
        val currSum = flow.sum()
        if ((seen[key] ?: Int.MIN_VALUE) >= currSum) {
            return
        }
        seen[key] = currSum
        if (time == maxTime) {
            max = max(max, currSum)
            return
        }

        if (open.all { it.value }) {
            findPath(time + 1, position, flow + currFlow, position2)
        }

        // Open valve?
        position.searchPath(time, flow, position2)
    }

    private fun String.searchPath(time: Int, flow: List<Int>, other: String?) {
        for (offset in 0..1) {
            if (offset == 0) {
                if (open[this]!! || flows[this]!! <= 0) {
                    continue
                }
                open[this] = true
                other?.findSecondaryPath(time, this, flow) ?: findPath(time + 1, this, flow + currFlow)
                open[this] = false
            } else {
                val currentFlow = currFlow
                valves[this]?.forEach { p ->
                    other?.findSecondaryPath(time, p, flow) ?: findPath(time + 1, p, flow + currentFlow)
                }
            }
        }

    }

    private fun String.findSecondaryPath(time: Int, position: String, flow: List<Int>) {
        for (offset in 0..1) {
            if (offset == 0) {
                if (open[this]!! || flows[this]!! <= 0) {
                    continue
                }
                open[this] = true
                findPath(time + 1, position, flow + currFlow, this)
                open[this] = false
            } else {
                val currentFlow = currFlow
                valves[this]?.forEach { p ->
                    findPath(time + 1, position, flow + currentFlow, p)
                }
            }
        }
    }
}
