/**
 * Copyright (c) 2022 by Charles Anderson
 * Advent of Code 2022, Day 13
 * Problem Description: http://adventofcode.com/2021/day/13
 */
package co.csadev.advent2022

import co.csadev.adventOfCode.BaseDay
import co.csadev.adventOfCode.Resources.resourceAsText

class Day13(override val input: String = resourceAsText("22day13.txt")) :
    BaseDay<String, Int, Int> {

    private val packets = input.split("\n\n").map { it.lines().map { l -> Packet(l) } }

    override fun solvePart1() =
        packets.indices.filter { packets[it].run { first() > last() } }.sumOf { it + 1 }

    private val newPackets = listOf("[[2]]", "[[6]]")
    override fun solvePart2(): Int {
        val mPackets = packets.flatten().toMutableList().apply {
            addAll(newPackets.map { Packet(it) })
        }.sorted().reversed()
        return mPackets.indices.filter { newPackets.contains(mPackets[it].str) }
            .fold(1) { acc, p -> acc * (p + 1) }
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
            for (c in packet) {
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
