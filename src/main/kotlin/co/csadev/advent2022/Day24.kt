/**
 * Copyright (c) 2022 by Charles Anderson
 * Advent of Code 2022, Day 24
 * Problem Description: http://adventofcode.com/2021/day/24
 */
package co.csadev.advent2022

import co.csadev.adventOfCode.BaseDay
import co.csadev.adventOfCode.Resources.resourceAsList
import java.util.*

class Day24(override val input: List<String> = resourceAsList("22day24.txt")) :
    BaseDay<List<String>, Int, Int> {

    private val height = input.size
    private val width = input.first().length
    private val gridRange = (0 until height).map { y -> (0 until width).map { x -> y to x } }.flatten().toSet()
    private var blizzardMaps: MutableMap<Int, MutableSet<Pair<Int, Int>>> = mutableMapOf()
    private val entryX = input[0].indexOfFirst { it != '#' }
    private val endX = input[height - 1].indexOfLast { it != '#' }

    // Cache the maps and generate them on demand
    private fun Int.map(): MutableSet<Pair<Int, Int>> {
        return blizzardMaps.getOrPut(this) {
            val newBlizzard = mutableSetOf<Pair<Int, Int>>()
            gridRange.map { (y, x) ->
                val newPoint = when (input[y][x]) {
                    '>' -> y to 1 + ((x - 1 + this).mod(width - 2))
                    '<' -> y to 1 + ((x - 1 - this).mod(width - 2))
                    'v' -> 1 + ((y - 1 + this).mod(height - 2)) to x
                    '^' -> 1 + ((y - 1 - this).mod(height - 2)) to x
                    else -> null
                }
                newPoint?.let { newBlizzard.add(it) }
            }
            newBlizzard
        }
    }

    private data class Hiking(val y: Int, val x: Int, val time: Int) {
        fun withNeighbors(): Sequence<Hiking> =
            listOf(this, copy(y = y + 1), copy(y = y - 1), copy(x = x + 1), copy(x = x - 1)).asSequence()
    }

    @Suppress("LoopWithTooManyJumpStatements")
    private fun findPath(startTime: Int = 0, startX: Int = entryX, startY: Int = 0, goal: Int = height - 1): Int {
        val hikingQueue = LinkedList<Hiking>()
        hikingQueue.add(Hiking(startY, startX, startTime))
        val knownHikers = mutableSetOf<Hiking>()
        var currBlizzard: MutableSet<Pair<Int, Int>>
        while (hikingQueue.size > 0) {
            var (y, x, time) = hikingQueue.poll()
            if (y to x !in gridRange || input[y][x] == '#') continue
            if (y == goal) return time
            val newHiker = Hiking(y, x, time)
            if (!knownHikers.add(newHiker)) continue
            time += 1
            currBlizzard = time.map()

            newHiker.copy(time = time).withNeighbors().filter { it.y to it.x !in currBlizzard }.forEach { hikingQueue.add(it) }
        }
        return Int.MIN_VALUE
    }

    private var firstSearchTime: Int? = null
    override fun solvePart1() = findPath().also { firstSearchTime = it }

    override fun solvePart2() = findPath(findPath(firstSearchTime ?: findPath(), endX, height - 2, 0))
}
