/*
 * Copyright (c) 2021 by Charles Anderson
 */

/**
 * Advent of Code 2021, Day 19
 * Problem Description: http://adventofcode.com/2021/day/19
 */
package co.csadev.advent2021

import co.csadev.advent2021.Day19.Beacon.Companion.minus
import co.csadev.advent2021.Day19.Beacon.Companion.plus
import co.csadev.adventOfCode.BaseDay
import co.csadev.adventOfCode.Resources.resourceAsText
import com.github.shiguruikai.combinatoricskt.combinations
import kotlin.math.abs

class Day19(override val input: String = resourceAsText("21day19.txt")) :
    BaseDay<String, Int, Int> {

    private val scannerViews = input.split("\n\n").mapIndexed { idx, each ->
        each.lines().drop(1).map { l ->
            l.split(",").let { p ->
                Beacon(p[0].toInt(), p[1].toInt(), p[2].toInt())
            }
        }.run { Scanner("$idx", toMutableList()) }
    }.toMutableList()

    private var scanners: List<Beacon>
    private val combined = scannerViews.removeAt(0).apply { combine(scannerViews).also { scanners = it } }

    override fun solvePart1() = combined.beacons.size

    override fun solvePart2() = scanners.combinations(2).maxOf { abs(it.first().distanceTo(it.last())) }

    data class Beacon(val x: Int, val y: Int, val z: Int) {
        val rotations: List<Beacon>
            get() {
                var moved = this
                val r = mutableListOf<Beacon>()
                repeat(2) {
                    repeat(3) {
                        moved = moved.roll
                        r.add(moved)
                        repeat(3) {
                            moved = moved.turn
                            r.add(moved)
                        }
                    }
                    moved = moved.roll.turn.roll
                }
                return r
            }

        fun distanceTo(other: Beacon) = abs(other.x - x) + abs(other.y - y) + abs(other.z - z)

        private val roll: Beacon
            get() = copy(y = z, z = -y)

        private val turn: Beacon
            get() = copy(x = -y, y = x)

        companion object {
            operator fun Beacon.minus(other: Beacon) = Beacon(x - other.x, y - other.y, z - other.z)

            operator fun Beacon.plus(other: Beacon) = Beacon(x + other.x, y + other.y, z + other.z)
        }
    }

    internal inner class Scanner(private val name: String, val beacons: MutableList<Beacon>) {
        fun combine(scanList: MutableList<Scanner>): List<Beacon> {
            val scanners = mutableListOf(Beacon(0, 0, 0))
            while (scanList.isNotEmpty()) {
                for (sc in scanList.toList()) {
                    val t = sc.rotations.mapNotNull { r ->
                        findTranslation(r)?.let { r to it }
                    }.firstOrNull() ?: continue
                    add(t.first, t.second)
                    scanners.add(t.second)
                    scannerViews.remove(sc)
                }
            }
            return scanners
        }

        private fun findTranslation(peer: Scanner): Beacon? {
            val diffs = beacons.flatMap { c -> peer.beacons.map { d -> c - d } }
                .groupingBy { it }.eachCount()
            return diffs.keys.firstOrNull { (diffs[it] ?: -1) >= 12 }
        }

        private val rotations: List<Scanner>
            get() = beacons.map { it.rotations }.run {
                (0 until first().size).map { idx ->
                    Scanner("$name:rot:$idx", map { r -> r[idx] }.toMutableList())
                }
            }

        private fun add(t: Scanner, trans: Beacon) =
            beacons.addAll(t.beacons.map { c -> c + trans }.filter { !beacons.contains(it) })
    }
}
