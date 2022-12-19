@file:Suppress("TooManyFunctions")

/*
 * Copyright (c) 2021 by Charles Anderson
 */

package co.csadev.adventOfCode

import java.util.*

/**
 * Multiplication of an iterable list of Longs
 */
fun Iterable<Long>.product(): Long = reduce { a, b -> a * b }

/**
 * Counts the number of increases between [Int]s in a list
 */
fun List<Int>.countIncreases() = zipWithNext().count { it.second > it.first }

/**
 * Counts the number of increases between [Int]s in a list
 */
fun <E> Set<E>.match(vararg t: Set<E>) = t.all { s -> s.all { c -> contains(c) } }

/**
 * Selects the longer of two lists in a [Pair<List<T>, List<T>>]
 */
fun <T> Pair<List<T>, List<T>>.longest(): List<T> = if (first.size >= second.size) first else second

/**
 * Selects the shorter of two lists in a [Pair<List<T>, List<T>>]
 */
fun <T> Pair<List<T>, List<T>>.shortest(): List<T> = if (first.size < second.size) first else second

/**
 * Returns the range of keys from a [SortedMap]
 */
val SortedMap<Int, Int>.range: IntRange
    get() = firstKey()..lastKey()

/**
 * Returns the product of the [Int]s in a list
 */
fun Iterable<Int>.product(): Int = reduce { a, b -> a * b }

/**
 * Gets the midpoint value from a List
 */
fun <T> List<T>.midpoint(): T =
    this[lastIndex / 2]

inline fun <T> Iterable<T>.indexOrMax(predicate: (T) -> Boolean): Int {
    var index = 0
    for (item in this) {
        index++
        if (predicate(item)) {
            return index
        }
    }
    return index
}

/**
 * Prints out a given list
 */
fun Iterable<Point2D>.printArea(
    min: Point2D,
    max: Point2D,
    visualization: (Boolean, Int) -> Char = { contained, index -> if (contained) index.digitToChar() else '.' }
) {
    for (y in (min.y..max.y)) {
        for (x in (min.x..max.x)) {
            Point2D(x, y).run { print(visualization(contains(this), indexOf(this))) }
        }
        println()
    }
}

/**
 * Creates a deep copy of a nested list
 */
fun <T> MutableList<MutableList<T>>.deepCopy() = map { it.toMutableList() }.toMutableList()

fun <T> List<T>.copyApply(block: MutableList<T>.() -> Unit): MutableList<T> {
    return toMutableList().apply { block() }
}
