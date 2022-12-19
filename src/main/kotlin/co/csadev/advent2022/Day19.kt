/**
 * Copyright (c) 2022 by Charles Anderson
 * Advent of Code 2022, Day 19
 * Problem Description: http://adventofcode.com/2021/day/19
 */
package co.csadev.advent2022

import co.csadev.adventOfCode.BaseDay
import co.csadev.adventOfCode.Resources.resourceAsList
import co.csadev.adventOfCode.copyApply
import co.csadev.adventOfCode.product

class Day19(override val input: List<String> = resourceAsList("22day19.txt")) :
    BaseDay<List<String>, Int, Int> {

    data class Cost(val id: Int, val oO: Int, val cO: Int, val obO: Int, val obC: Int, val gO: Int, val gOb: Int)

    private val costs = input.map {
        it.replace(":", "").split(" ").mapNotNull { v -> v.toIntOrNull() }
    }.map { l ->
        Cost(l[0], l[1], l[2], l[3], l[4], l[5], l[6])
    }

    override fun solvePart1(): Int {
        return costs.sumOf { it.runSim(24) * it.id }
    }

    override fun solvePart2(): Int {
        return costs.take(3).map { it.runSim(32) }.product()
    }

    companion object {
        private const val Ore = 0
        private const val Clay = 1
        private const val Obsidian = 2
        private const val Geode = 3
    }

    private fun Cost.runSim(
        time: Int,
        bots: MutableList<Int> = MutableList(4) { 0 }.apply { this[Ore] = 1 },
        res: MutableList<Int> = MutableList(4) { 0 }
    ): Int {
        val nextRes = (0 until 4).map { res[it] + bots[it] }.toMutableList()
        val nextTime = time - 1

        // Build as many Geode bots as you can
        return if (nextTime == 0) {
            nextRes[Geode]
        } else if (res[Ore] >= gO && res[Obsidian] >= gOb) {
            runSim(nextTime, bots.apply { this[Geode]++ }, nextRes.apply { this[Ore] -= gO; this[Obsidian] -= gOb })
        } else if (res[Ore] >= obO && res[Clay] >= obC) {
            // Then build as many obsidian robots as you can
            runSim(nextTime, bots.apply { this[Obsidian]++ }, nextRes.apply { this[Ore] -= obO; this[Clay] -= obC })
        } else {
            // Past here we need to see what makes sense to build of the 3 options
            val options = mutableListOf<Int>()
            // Option 1: Build nothing
            if (res[Ore] < 4) {
                options += runSim(nextTime, bots.toMutableList(), nextRes.toMutableList())
            }
            // Option 2: Build an Ore robot
            if (res[Ore] >= oO) {
                options += runSim(nextTime, bots.copyApply { this[Ore] += 1 }, nextRes.copyApply { this[Ore] -= oO })
            }
            // Option 3: Build a Clay robot
            if (res[Ore] >= cO) {
                options += runSim(nextTime, bots.copyApply { this[Clay] += 1 }, nextRes.copyApply { this[Ore] -= cO })
            }
            options.maxOf { it }
        }
    }
}
