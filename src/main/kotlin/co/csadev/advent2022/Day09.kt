/**
 * Copyright (c) 2022 by Charles Anderson
 * Advent of Code 2022, Day 9
 * Problem Description: http://adventofcode.com/2021/day/9
 */
package co.csadev.advent2022

import co.csadev.adventOfCode.BaseDay
import co.csadev.adventOfCode.Point2D
import co.csadev.adventOfCode.Resources.resourceAsList
import co.csadev.adventOfCode.printArea
import kotlin.math.sign

class Day09(override val input: List<String> = resourceAsList("22day09.txt")) :
    BaseDay<List<String>, Int, Int> {

    private val min = Point2D(-37, -62)
    private val max = Point2D(212, 341)
    private var minX = 0
    private var maxX = 0
    private var minY = 0
    private var maxY = 0

    private fun lastKnotVisits(knotCount: Int, print: Boolean = false): Int {
        val knots = MutableList(knotCount) { Point2D.ORIGIN }
        val visits = MutableList(knotCount) { mutableSetOf(Point2D.ORIGIN) }
        input.forEach {
            val (dir, dis) = it.split(" ")
            val distance = dis.toInt()
            val move = Moves.valueOf(dir).move
            repeat(distance) {
                knots[0] = knots[0].plus(move).apply { minMax() }
                knots.drop(1).indices.forEach { index ->
                    val head = knots[index]
                    var tail = knots[index + 1]
                    if (tail !in head.neighbors) {
                        tail = Point2D(
                            tail.x + (head.x - tail.x).sign,
                            tail.y + (head.y - tail.y).sign
                        )
                    }
                    knots[index + 1] = tail.apply { minMax() }
                }
//                knots.printArea(min,max)
                repeat(knotCount) { k ->
                    visits[k] += knots[k]
                }
            }
        }
//        println("Min: ${Point2D(minX, minY)}")
//        println("Max: ${Point2D(maxX, maxY)}")
        if (print) {
            visits.last().printArea(min, max) { contained, _ -> if (contained) '#' else ' ' }
        }
        return visits.last().size
    }

    override fun solvePart1() = lastKnotVisits(2)

    override fun solvePart2() = lastKnotVisits(10, true)

    private fun Point2D.minMax() {
        minX = minX.coerceAtMost(x)
        minY = minY.coerceAtMost(y)
        maxX = maxX.coerceAtLeast(x)
        maxY = maxY.coerceAtLeast(y)
    }

    private enum class Moves(val move: Point2D) {
        L(Point2D(-1, 0)),
        R(Point2D(1, 0)),
        U(Point2D(0, 1)),
        D(Point2D(0, -1))
    }
}
