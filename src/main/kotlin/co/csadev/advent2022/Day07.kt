/*
 * Copyright (c) 2022 by Charles Anderson
 */

/**
 * Advent of Code 2022, Day 7
 * Problem Description: http://adventofcode.com/2021/day/7
 */
package co.csadev.advent2022

import co.csadev.adventOfCode.BaseDay
import co.csadev.adventOfCode.Resources.resourceAsList
import java.util.InputMismatchException

class Day07(override val input: List<String> = resourceAsList("22day07.txt")) :
    BaseDay<List<String>, Long, Long> {
    data class Dir(
        val name: String,
        val parent: Dir? = null,
        val children: MutableList<Dir> = mutableListOf()
    ) {
        val total: Long get() = size + children.sumOf { it.total }
        var size = 0L
    }

    private val base = Dir("/")
    private val dirs = mutableListOf<Dir>()

    init {
        var current: Dir = base
        input.forEach { line ->
            val split = line.split(" ")
            when {
                split[1] == "ls" -> Unit
                split[1] == "cd" -> current = if (split[2] == "..") {
                    current.parent ?: throw InputMismatchException("Bad input")
                } else {
                    current.children.firstOrNull { it.name == split[2] } ?: base
                }
                split[0] == "dir" -> Dir(split[1], current).apply {
                    current.children += this
                    dirs += this
                }
                else -> current.size += split[0].toLong()
            }
        }
    }

    override fun solvePart1() = dirs.filter { it.total < 100_000L }.sumOf { it.total }

    private val missingSpace = 30_000_000 - (70_000_000 - base.total)
    override fun solvePart2() = dirs.filter { it.total >= missingSpace }.minOf { it.total }
}
