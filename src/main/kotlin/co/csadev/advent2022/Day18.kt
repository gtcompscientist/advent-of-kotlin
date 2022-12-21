/**
 * Copyright (c) 2022 by Charles Anderson
 * Advent of Code 2022, Day 18
 * Problem Description: http://adventofcode.com/2021/day/18
 */
package co.csadev.advent2022

import co.csadev.adventOfCode.BaseDay
import co.csadev.adventOfCode.Point3D
import co.csadev.adventOfCode.Resources.resourceAsList

class Day18(override val input: List<String> = resourceAsList("22day18.txt")) :
    BaseDay<List<String>, Int, Int> {

    private val lava = input.map { it.split(",").asPoint3D }.toSet()

    private val List<String>.asPoint3D: Point3D
        get() = Point3D(this[0].toInt(), this[1].toInt(), this[2].toInt())

    override fun solvePart1(): Int {
        val totalSides = lava.size * 6
        val touching = lava.sumOf { it.adjacent.count { n -> n in lava } }
        return totalSides - touching
    }

    override fun solvePart2(): Int {
        val map = mutableMapOf<Point3D, Boolean>()
        lava.forEach { l -> map[l] = false }
        map[Point3D(-2, -2, -2)] = true

        val searchRange = (-2..22)
        // Add and mark visited nodes
        var added = 1
        while (added > 0) {
            added = 0
            map.filter { it.value }
                .forEach { (p, _) ->
                    p.adjacent.filter { it.x in searchRange }
                        .filter { it.y in searchRange }
                        .filter { it.z in searchRange }
                        .filter { map.getOrDefault(it, 0) == 0 }
                        .forEach {
                            added++
                            map[it] = true
                        }
                }
        }

        return map.filter { !it.value }
            .flatMap { it.key.adjacent }
            .count { map.getOrDefault(it, false) }
    }
}
