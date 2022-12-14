/*
 * Copyright (c) 2022 by Charles Anderson
 */

/**
 * Advent of Code 2022, Day 13
 * Problem Description: http://adventofcode.com/2021/day/13
 */
package co.csadev.advent2022

import co.csadev.adventOfCode.BaseDay
import co.csadev.adventOfCode.Resources.resourceAsText

class Day13(override val input: String = resourceAsText("22day13.txt")) :
    BaseDay<String, Int, Int> {

    private val packets = input.split("\n\n").map { it.lines().map { l -> Packet(l) } }

    override fun solvePart1(): Int {
        var count = 0
        packets.forEachIndexed { index, packets ->
            if (packets.first() > packets.last()) {
                count += index + 1
            }
        }
        return count
    }

    override fun solvePart2(): Int {
        var decoderKey = 1
        val mPackets = packets.flatten().toMutableList().apply {
            add(Packet("[[2]]"))
            add(Packet("[[6]]"))
            sort()
            reverse()
        }
        mPackets.forEachIndexed { index, packet ->
            if (packet.str == "[[2]]" || packet.str == "[[6]]") decoderKey *= index + 1
        }
        return decoderKey
    }
}

class Packet(val str: String) : Comparable<Packet> {
    private val children = mutableListOf<Packet>()
    private var integer = true
    var value = 0

    init {
        var packet = str
        if (packet == "[]") {
            value = -1
        }
        if (!packet.startsWith("[")) {
            value = packet.toInt()
        } else {
            packet = packet.substring(1, packet.length - 1)
            var depth = 0
            var curr = ""
            for (c in packet.toCharArray()) {
                if (c == ',' && depth == 0) {
                    children.add(Packet(curr))
                    curr = ""
                } else {
                    depth += if (c == '[') 1 else if (c == ']') -1 else 0
                    curr += c
                }
            }
            if (curr != "") {
                children.add(Packet(curr))
            }
            integer = false
        }
    }

    override fun compareTo(other: Packet): Int {
        return if (integer && other.integer) {
            return other.value - value
        } else if (!integer && !other.integer) {
            (0 until children.size.coerceAtMost(other.children.size)).mapNotNull {
                val value = children[it].compareTo(other.children[it])
                if (value != 0) {
                    value
                } else {
                    null
                }
            }.firstOrNull() ?: (other.children.size - children.size)
        } else {
            val lst1 = if (integer) Packet("[$value]") else this
            val lst2 = if (other.integer) Packet("[" + other.value + "]") else other
            lst1.compareTo(lst2)
        }
    }
}
