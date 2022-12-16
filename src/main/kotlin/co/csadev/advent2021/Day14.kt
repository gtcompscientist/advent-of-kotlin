/**
 * Copyright (c) 2021 by Charles Anderson
 * Advent of Code 2021, Day 14
 * Problem Description: http://adventofcode.com/2021/day/14
 */
package co.csadev.advent2021

import co.csadev.adventOfCode.BaseDay
import co.csadev.adventOfCode.Resources.resourceAsList

class Day14(override val input: List<String> = resourceAsList("21day14.txt")) :
    BaseDay<List<String>, Long, Long> {
    private var polymer = input.first().map { it.toString() }
    private val rules = input.drop(2).associate { it.split(" -> ").let { s -> s[0] to s[1] } }

    override fun solvePart1() = expSeq.take(10).last()

    override fun solvePart2() = expSeq.take(40).last()

    private val expSeq: Sequence<Long> by lazy {
        sequence {
            var polymerPairs = polymer.zipWithNext().map { "${it.first}${it.second}" }.groupingBy { it }.eachCount()
                .mapValues { it.value.toLong() }
            while (true) {
                polymerPairs = polymerPairs.expand()
                val charPairs = mutableMapOf<Char, Long>()
                polymerPairs.forEach { (p, count) ->
                    charPairs[p.first()] = count + charPairs.getOrDefault(p.first(), 0L)
                }
                yield(charPairs.maxOf { it.value } - charPairs.minOf { it.value } + 1)
            }
        }
    }

    private fun List<String>.expand(count: Int): Long {
        var polymerPairs = zipWithNext()
            .map { "${it.first}${it.second}" }
            .groupingBy { it }
            .eachCount()
            .mapValues { it.value.toLong() }
        repeat(count) {
            val newPairs = mutableMapOf<String, Long>()
            polymerPairs.forEach { (p, count) ->
                val seq = rules[p]!!
                newPairs[p.first() + seq] = count + newPairs.getOrDefault(p.first() + seq, 0L)
                newPairs[seq + p.last()] = count + newPairs.getOrDefault(seq + p.last(), 0L)
            }
            polymerPairs = newPairs
        }
        val charPairs = mutableMapOf<Char, Long>()
        polymerPairs.forEach { (p, count) ->
            charPairs[p.first()] = count + charPairs.getOrDefault(p.first(), 0L)
        }
        return charPairs.maxOf { it.value } - charPairs.minOf { it.value } + 1
    }

    private fun Map<String, Long>.expand(): Map<String, Long> {
        val newPairs = mutableMapOf<String, Long>()
        forEach { (p, count) ->
            val seq = rules[p]!!
            newPairs[p.first() + seq] = count + newPairs.getOrDefault(p.first() + seq, 0L)
            newPairs[seq + p.last()] = count + newPairs.getOrDefault(seq + p.last(), 0L)
        }
        return newPairs
    }
}
