/*
 * Copyright (c) 2022 by Charles Anderson
 */

package co.csadev.adventOfCode

/**
 * Prints out a given map
 */
fun <T> Map<Point2D, T>.printArea(visualization: (T) -> Char = { it.toString()[0] }) {
    val xRange = (keys.minOf { it.x })..(keys.maxOf { it.x })
    val yRange = (keys.minOf { it.y })..(keys.maxOf { it.y })
    for (y in yRange) {
        for (x in xRange) {
            val value = get(Point2D(x, y))
            if (value != null) {
                print(visualization(value))
            } else {
                print(" ")
            }
        }
        println()
    }
}
