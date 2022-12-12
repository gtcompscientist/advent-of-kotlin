/*
 * Copyright (c) 2021 by Charles Anderson
 */
package co.csadev.adventOfCode

/**
 * Checks that the given [String] contains all of each `t` [String]
 */
fun String.match(vararg t: String) = t.all { s -> s.all { c -> contains(c) } }

/**
 * Sorts a string alphabetically
 */
fun String.sort() = toList().sorted().joinToString("")
