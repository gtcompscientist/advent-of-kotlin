/**
 * Copyright (c) 2022 by Charles Anderson
 * Advent of Code 2022, Day 15
 * Problem Description: http://adventofcode.com/2021/day/15
 */
package co.csadev.advent2022

import co.csadev.adventOfCode.BaseDay
import co.csadev.adventOfCode.Point2D
import co.csadev.adventOfCode.Resources.resourceAsList
import kotlin.math.max
import kotlin.math.min

class Day15(override val input: List<String> = resourceAsList("22day15.txt")) :
    BaseDay<List<String>, Int, Long> {

    private var sensors = mutableMapOf<Point2D, Point2D>()
    private var allPoints = mutableSetOf<Point2D>()
    private var minX: Int = Int.MAX_VALUE
    private var maxX: Int = Int.MIN_VALUE
    var p1Line = 2_000_000
    var p2Max = 4000000L

    init {
        input.forEach {
            val (sensor, beacon) = it.split(":")
            val s = sensor.asPoint
            val b = beacon.asPoint
            allPoints.add(s)
            allPoints.add(b)
            sensors[s] = b
        }
        minX = sensors.minOf { (s, b) -> min(s.x, b.x) }
        maxX = sensors.maxOf { (s, b) -> max(s.x, b.x) }
    }

    // Arbitrary distance added to each side to ensure we don't miss anything.
    override fun solvePart1() = (minX - p1Line * 5..maxX + p1Line * 5).asSequence()
        .map { Point2D(it, p1Line) }
        .filter { it !in allPoints }
        .count { it.tooClose }

    override fun solvePart2() =
        sensors.mapNotNull { (s, b) -> scanOuter(s, s.distanceTo(b)) }.first()

    private val String.asPoint: Point2D
        get() = Point2D(
            substringBefore(",").substringAfter("=").toInt(),
            substringAfter("y=").toInt()
        )

    private val Point2D.tooClose: Boolean
        get() = sensors.any { (s, b) -> distanceTo(s) <= s.distanceTo(b) }

    private val Point2D.isBeacon: Boolean
        get() = x in (0..p2Max) && y in (0..p2Max) && this !in allPoints && !tooClose

    private val explore = listOf(Point2D(1, 1), Point2D(-1, 1), Point2D(-1, -1), Point2D(1, -1))

    private fun scanOuter(s: Point2D, dist: Int): Long? {
        var cur = s + Point2D(0, -dist - 1)
        for (p in explore) for (step in 0..dist) {
            cur += p
            if (cur.isBeacon) {
                return (cur.x * 4_000_000L) + cur.y
            }
        }
        return null
    }
}
