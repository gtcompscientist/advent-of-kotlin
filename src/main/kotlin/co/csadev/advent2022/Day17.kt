/**
 * Copyright (c) 2022 by Charles Anderson
 * Advent of Code 2022, Day 17
 * Problem Description: http://adventofcode.com/2021/day/17
 */
package co.csadev.advent2022

import co.csadev.adventOfCode.BaseDay
import co.csadev.adventOfCode.Resources.resourceAsList

class Day17(override val input: List<String> = resourceAsList("22day17.txt")) :
    BaseDay<List<String>, Long, Long> {

    override fun solvePart1() = dropRocks()

    override fun solvePart2() = dropRocks(1000000000000L)

    private var jets = input.first().map { if (it == '<') Jet.LEFT else Jet.RIGHT }

    private val shapes = """
        ####

        .#.
        ###
        .#.

        ..#
        ..#
        ###

        #
        #
        #
        #

        ##
        ##
    """.trimIndent().split("\n\n")
        .map { r ->
            r.lines().map { l ->
                l.map { it == '#' }.toBooleanArray()
            }.toTypedArray()
        }.toTypedArray()

    private enum class Jet {
        LEFT, RIGHT
    }

    data class Seen(val ins: Int, val shape: Int, val view: List<Int>)
    data class Value(val rock: Long, val height: Int)

    private fun Array<BooleanArray>.canMove(
        x: Int,
        y: Int,
        layers: Map<Int, BooleanArray>
    ): Boolean {
        for (sY in indices) {
            val actualY = y + (size - sY)
            val layer = layers[actualY] ?: continue
            val shapeLayer = this[sY]
            if (shapeLayer.indices.any { sX -> layer[x + sX] && shapeLayer[sX] }) {
                return false
            }
        }
        return true
    }

    private val Long.shape: Int
        get() = (this % shapes.size).toInt()

    private val MutableMap<Int, BooleanArray>.keyList: List<Int>
        get() {
            val h = keys.reversed()
            val view = IntArray(7) { -1 }
            for (i in 0..6) {
                for (j in h.indices) {
                    if (this[h[j]]!![i]) {
                        view[i] = height - h[j]
                        break
                    }
                }
            }
            return view.toList()
        }

    private fun Array<BooleanArray>.addLayer(x: Int, y: Int) {
        for (sY in indices) {
            val actualY = y + (size - sY)
            val shapeLayer = this[sY]
            val layer = layers.computeIfAbsent(actualY) { BooleanArray(7) }
            for (sX in shapeLayer.indices) {
                layer[x + sX] = layer[x + sX] or shapeLayer[sX]
            }
        }
    }

    private var height = 0
    private var ins = 0
    private val layers = sortedMapOf<Int, BooleanArray>()
    private val seen = mutableMapOf<Seen, Value>()
    private var extraHeight: Long = 0
    private var rock: Long = 0
    private fun dropRocks(rounds: Long = 2022L): Long {
        while (rock < rounds) {
            val shape = shapes[rock.shape]
            var x = 2
            var y = height + 3
            while (true) {
                val jet = jets[ins++ % jets.size]
                if (jet == Jet.LEFT && x > 0 && shape.canMove(x - 1, y, layers)) {
                    x--
                } else if (jet == Jet.RIGHT && x < 7 - shape[0].size && shape.canMove(x + 1, y, layers)) {
                    x++
                }
                if (shape.canMove(x, y - 1, layers) && y > 0) {
                    y--
                } else {
                    shape.addLayer(x, y)
                    break
                }
            }
            height = layers.keys.last()
            checkSeen(rounds)
            rock++
        }
        return height + extraHeight
    }

    private fun checkSeen(rounds: Long) {
        val key = Seen((ins - 1) % jets.size, rock.shape, layers.keyList)
        if (key in seen) {
            val old = seen[key]!!
            val repeat = (rounds - rock) / (rock - old.rock)
            rock += (rock - old.rock) * repeat
            extraHeight += repeat * (height - old.height)
        }
        seen[key] = Value(rock, height)
    }
}
