/**
 * Copyright (c) 2022 by Charles Anderson
 * Advent of Code 2022, Day 19
 * Problem Description: http://adventofcode.com/2021/day/19
 */
package co.csadev.advent2022

import co.csadev.adventOfCode.BaseDay
import co.csadev.adventOfCode.Resources.resourceAsList
import co.csadev.adventOfCode.product
import kotlin.math.max

class Day19(override val input: List<String> = resourceAsList("22day19.txt")) :
    BaseDay<List<String>, Int, Int> {

    data class Cost(val id: Int, val oO: Int, val cO: Int, val obO: Int, val obC: Int, val gO: Int, val gOb: Int)

    private val costs = input.map {
        it.replace(":", "").split(" ").mapNotNull { v -> v.toIntOrNull() }
    }.map { l ->
        Cost(l[0], l[1], l[2], l[3], l[4], l[5], l[6])
    }

    private val maxLowend = costs.maxOf { max(it.oO, it.cO) }
    private val maxClay = costs.maxOf { it.obC }
    private val maxOb = costs.maxOf { it.gOb }

    override fun solvePart1(): Int {
        return costs.sumOf { it.runSim(24, 1, 0, 0, 0, 0, 0, 0, 0) * it.id }
    }

    override fun solvePart2(): Int {
        return costs.take(3).map { it.runSim(32, 1, 0, 0, 0, 0, 0, 0, 0) }.product()
    }

    @Suppress("LongParameterList") // 80% runtime reduction in using [Int]s over [List]s
    private fun Cost.runSim(time: Int, bOre: Int, bClay: Int, bOb: Int, bGeo: Int, rOre: Int, rClay: Int, rOb: Int, rGeo: Int): Int {
        val (nOre, nClay, nOb, nGeo) = listOf(rOre + bOre, rClay + bClay, rOb + bOb, rGeo + bGeo)
        val nextTime = time - 1

        // Build as many Geode bots as you can
        return if (nextTime == 0) {
            nGeo
        } else if (rOre >= gO && rOb >= gOb) {
            runSim(nextTime, bOre, bClay, bOb, bGeo + 1, nOre - gO, nClay, nOb - gOb, nGeo)
        } else if (bOb < maxOb && rOre >= obO && rClay >= obC) {
            // Then build as many obsidian robots as you can
            runSim(nextTime, bOre, bClay, bOb + 1, bGeo, nOre - obO, nClay - obC, nOb, nGeo)
        } else {
            // Past here we need to see what makes sense to build of the 3 options
            val options = mutableListOf<Int>()
            // Option 1: Build nothing
            if (rOre < maxLowend) {
                options += runSim(nextTime, bOre, bClay, bOb, bGeo, nOre, nClay, nOb, nGeo)
            }
            // Option 2: Build an Ore robot
            if (bClay < maxClay && rOre >= oO) {
                options += runSim(nextTime, bOre + 1, bClay, bOb, bGeo, nOre - oO, nClay, nOb, nGeo)
            }
            // Option 3: Build a Clay robot
            if (rOre >= cO) {
                options += runSim(nextTime, bOre, bClay + 1, bOb, bGeo, nOre - cO, nClay, nOb, nGeo)
            }
            options.maxOf { it }
        }
    }
}
