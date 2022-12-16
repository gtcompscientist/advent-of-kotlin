/**
 * Copyright (c) 2021 by Charles Anderson
 * Advent of Code 2021, Day 22
 * Problem Description: http://adventofcode.com/2021/day/212
 */
package co.csadev.advent2021

import co.csadev.adventOfCode.BaseDay
import co.csadev.adventOfCode.Resources.resourceAsList
import kotlin.math.max
import kotlin.math.min

class Day22(override val input: List<String> = resourceAsList("21day22.txt")) :
    BaseDay<List<String>, Long, Long> {
    private val instructions = input.map {
        it.split(" ", ",", "=", "..")
    }.map {
        CubeRule(
            Cube(it[2].toInt(), it[3].toInt(), it[5].toInt(), it[6].toInt(), it[8].toInt(), it[9].toInt()),
            (it[0] == "on")
        )
    }

    private fun getReactor(part2: Boolean = false): List<Cube> {
        var reactor = mutableListOf<Cube>()
        var temp: MutableList<Cube>
        instructions.forEachIndexed { index, rule ->
            println("Running instruction #$index")
            if (!part2 && rule.cube.initSpace) return@forEachIndexed
            temp = reactor.flatMap { r ->
                r.abjunction(rule.cube)
            }.toMutableList()

            if (rule.on) temp.add(rule.cube)
            reactor = temp
            temp = mutableListOf()
        }
        return reactor.toList()
    }

    private data class CubeRule(val cube: Cube, val on: Boolean)

    private data class Cube(
        val minX: Int,
        val maxX: Int,
        val minY: Int,
        val maxY: Int,
        val minZ: Int,
        val maxZ: Int
    ) {
        val initSpace = (minX < -50 || maxX > 50 || minY < -50 || maxY > 50 || minZ < -50 || maxZ > 50)
        val valid = if (minX <= maxX && minY <= maxY && minZ <= maxZ) this else null
        val width = maxX - minX + 1
        val height = maxY - minY + 1
        val depth = maxZ - minZ + 1
        val volume: Long = width.toLong() * height * depth

        fun intersect(a: Cube, b: Cube): Cube? {
            return Cube(
                max(a.minX, b.minX),
                min(a.maxX, b.maxX),
                max(a.minY, b.minY),
                min(a.maxY, b.maxY),
                max(a.minZ, b.minZ),
                min(a.maxZ, b.maxZ)
            ).valid
        }

        fun abjunction(b: Cube): List<Cube> {
            val i = intersect(this, b) ?: return listOf(this)
            val cubes = mutableListOf<Cube>()
            if (i.minX > minX) {
                cubes.add(Cube(minX, i.minX - 1, minY, maxY, minZ, maxZ))
            }
            if (i.maxX < maxX) {
                cubes.add(Cube(i.maxX + 1, maxX, minY, maxY, minZ, maxZ))
            }
            if (i.minY > minY) {
                cubes.add(Cube(i.minX, i.maxX, minY, i.minY - 1, minZ, maxZ))
            }
            if (i.maxY < maxY) {
                cubes.add(Cube(i.minX, i.maxX, i.maxY + 1, maxY, minZ, maxZ))
            }
            if (i.minZ > minZ) {
                cubes.add(Cube(i.minX, i.maxX, i.minY, i.maxY, minZ, i.minZ - 1))
            }
            if (i.maxZ < maxZ) {
                cubes.add(Cube(i.minX, i.maxX, i.minY, i.maxY, i.maxZ + 1, maxZ))
            }
            return cubes
        }
    }

    override fun solvePart1() = getReactor().sumOf { it.volume }

    override fun solvePart2() = getReactor(true).sumOf { it.volume }
}
