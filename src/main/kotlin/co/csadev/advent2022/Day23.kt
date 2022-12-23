/**
 * Copyright (c) 2022 by Charles Anderson
 * Advent of Code 2022, Day 23
 * Problem Description: http://adventofcode.com/2021/day/23
 */
package co.csadev.advent2022

import co.csadev.adventOfCode.BaseDay
import co.csadev.adventOfCode.Point2D
import co.csadev.adventOfCode.Resources.resourceAsList
import co.csadev.adventOfCode.printArea
import kotlin.math.abs

class Day23(override val input: List<String> = resourceAsList("22day23.txt")) :
    BaseDay<List<String>, Int, Int> {

    private val elvesStart = input.flatMapIndexed { y, s -> s.mapIndexedNotNull { x, c -> if (c == '#') Point2D(x, y) else null } }.toSet()

    private val northern = listOf(Point2D(0, -1), Point2D(1, -1), Point2D(-1, -1))
    private val southern = listOf(Point2D(0, 1), Point2D(1, 1), Point2D(-1, 1))
    private val western = listOf(Point2D(-1, 0), Point2D(-1, -1), Point2D(-1, 1))
    private val eastern = listOf(Point2D(1, 0), Point2D(1, -1), Point2D(1, 1))
    private val directionList = listOf(northern, southern, western, eastern)

    override fun solvePart1(): Int {
        val state = elvesStart.toMutableSet()
        val dirs = directionList.toMutableList()
//        state.print()
        repeat(10) {
            state.moveElves(dirs)
            dirs.add(dirs.removeAt(0))
//            state.print()
        }
        val ySize = abs(state.maxOf { it.y } - state.minOf { it.y }) + 1
        val xSize = abs(state.maxOf { it.x } - state.minOf { it.x }) + 1
        return (xSize * ySize) - state.size
    }

    @Suppress("unused")
    private fun Set<Point2D>.print() {
        val min = Point2D(minOf { p -> p.x }, minOf { p -> p.y })
        val max = Point2D(maxOf { p -> p.x }, maxOf { p -> p.y })
        printArea(min, max) { c, _ -> if (c) '#' else '.' }
        println()
    }

    override fun solvePart2(): Int {
        val state = elvesStart.toMutableSet()
        val dirs = directionList.toMutableList()
//        var num = 0
        return generateSequence {
            state.moveElves(dirs).also {
                dirs.add(dirs.removeAt(0))
//                state.print()
            }
        }.takeWhile {
//            println("Round ${num++}")
            it
        }.count() + 1
    }

    private fun MutableSet<Point2D>.moveElves(dirList: List<List<Point2D>>): Boolean {
        var moved = false
        val proposal = mutableMapOf<Point2D, MutableList<Point2D>>()
        for (p in this) {
            if (p.neighbors.none { n -> n in this }) continue

            dirList.firstOrNull { dir ->
                dir.map { n -> n + p }.none { p -> p in this }
            }?.let { propDir ->
                (proposal.getOrPut(p + propDir.first()) { mutableListOf() }) += p
            }
        }
        proposal.filter { it.value.size == 1 }.forEach { (newP, oldP) ->
            moved = true
            if (remove(oldP[0])) add(newP)
        }
        return moved
    }
}
