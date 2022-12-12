/*
 * Copyright (c) 2021 by Charles Anderson
 */

package co.csadev.advent2020

class Day13(input: List<String>) {
    private val start = input.first().toInt()
    private val shuttles = input.last().split(",").mapNotNull { i -> i.toIntOrNull() }
    private val shuttleMinutes = input.last().split(",")
        .mapIndexedNotNull { idx, i -> i.toIntOrNull()?.let { it to idx } }

    fun solvePart1() = shuttles.map { it to it * ((start / it) + 1) - start }
        .minByOrNull { it.second }!!
        .run { first * second }

    fun solvePart2() = findTime2(shuttleMinutes)

    private fun findTime2(buses: List<Pair<Int, Int>>): Long = longBuses(buses)
        .fold(Pair(0L, 1L)) { acc, next -> applyBus(acc.first, acc.second, next.first, next.second) }.first

    private fun applyBus(sum: Long, interval: Long, bus: Long, timeDif: Long) =
        Pair(findTimeWithRightDiff(sum, interval, timeDif, bus), interval * bus)

    private tailrec fun findTimeWithRightDiff(start: Long, interval: Long, expected: Long, bus: Long): Long =
        if ((start + expected) % bus == 0L) {
            start
        } else {
            findTimeWithRightDiff(start + interval, interval, expected, bus)
        }

    private fun longBuses(buses: List<Pair<Int, Int>>) = buses.map { Pair(it.first.toLong(), it.second.toLong()) }

    fun findTime(buses: List<Pair<Long, Long>>): Long {
        return chineseRemainder(buses)
    }

    /* returns x where (a * x) % b == 1 */
    private fun multInv(a: Long, b: Long): Long {
        if (b == 1L) return 1L
        var aa = a
        var bb = b
        var x0 = 0L
        var x1 = 1L
        while (aa > 1) {
            x0 = (x1 - (aa / bb) * x0).also { x1 = x0 }
            bb = (aa % bb).also { aa = bb }
        }
        if (x1 < 0) x1 += b
        return x1
    }

    private fun chineseRemainder(buses: List<Pair<Long, Long>>): Long {
        val prod = buses.map { it.first }.fold(1L) { acc, i -> acc * i }
        var sum = 0L
        for (i in buses.indices) {
            val p = prod / buses[i].first
            sum += buses[i].second * multInv(p, buses[i].first) * p
        }
        return sum % prod
    }
}
