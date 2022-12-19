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

    private val flowArr: Array<Int>
    private val valveArr: Array<List<Int>>
    private val openArr: Array<Boolean>
    private val aaIdx: Int

    init {
        val flows = mutableMapOf<String, Int>()
        val valves = mutableMapOf<String, List<String>>()
        val open = mutableMapOf<String, Boolean>()

        @Suppress("DestructuringDeclarationWithTooManyEntries")
        input.map {
            val (v, t) = it.split("; ")
            val (_, name, _, _, rate) = v.replace("rate=", "").split(" ")
            flows[name] = rate.toInt()
            val currV = t.split("valve")[1].replace("s ", "").trim().split(", ")
            valves[name] = currV
            open[name] = false
        }
        val nameMap = flows.keys.sorted().mapIndexed { index, s ->
            s to index
        }.toMap()
        aaIdx = nameMap["AA"]!!
        flowArr = flows.keys.sortedBy { nameMap[it]!! }.map { flows[it]!! }.toTypedArray()
        valveArr = valves.keys.sortedBy { nameMap[it]!! }.map { valves[it]!!.map { l -> nameMap[l]!! } }.toTypedArray()
        openArr = open.keys.sortedBy { nameMap[it]!! }.map { open[it]!! }.toTypedArray()
    }

    private val seen = mutableMapOf<String, Int>()
    private var max = 0
    private var maxTime = 30

    override fun solvePart1(): Int {
        listOf(0).findPath(1, arrayOf(aaIdx))
        return max
    }

    override fun solvePart2(): Int {
        seen.clear()
        max = 0
        maxTime = 26
        listOf(0).findPath(1, arrayOf(aaIdx, aaIdx))
        return max
    }

    private val currFlow: Int
        get() = openArr.mapIndexed { index, b -> if (b) flowArr[index] else 0 }.sum()

    private fun List<Int>.findPath(time: Int, pos: Array<Int>) {
        val key = "$time${pos.contentDeepHashCode()}"
        val currSum = sum()
        if (seen.getOrDefault(key, -1) >= currSum) {
            return
        }
        seen[key] = currSum
        if (time == maxTime) {
            max = max(max, currSum)
        } else if (openArr.all { it }) {
            (this + currFlow).findPath(time + 1, pos)
        } else {
            searchPath(time, 0, pos)
        }
    }

    private fun List<Int>.searchPath(time: Int, offset: Int, pos: Array<Int>) {
        val opening = pos.getOrNull(offset) ?: return
        if (!openArr[opening] && flowArr[opening] > 0) {
            openArr[opening] = true
            addDepth(currFlow, time, offset + 1, pos)
            openArr[opening] = false
        }
        val currentFlow = currFlow
        valveArr[opening].forEach { p ->
            val temp = pos[offset]
            pos[offset] = p
            addDepth(currentFlow, time, offset + 1, pos)
            pos[offset] = temp
        }
    }

    private fun List<Int>.addDepth(startFlow: Int, time: Int, offset: Int, pos: Array<Int>) {
        val pos2 = pos.getOrNull(offset)
        if (pos2 != null && offset > 0) {
            searchPath(time, offset, pos)
        } else {
            (this + startFlow).findPath(time + 1, pos)
        }
    }
}
