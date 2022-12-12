/*
 * Copyright (c) 2022 by Charles Anderson
 */

package co.csadev.adventOfCode

import java.util.InputMismatchException
import java.util.LinkedList

/**
 * Creates a path from an end point going through parent nodes back to the beginning
 */
fun Map<Point2D, Point2D>.pathThroughList(end: Point2D): List<Point2D> {
    var cur = end
    val path = mutableListOf<Point2D>()
    while (containsKey(cur)) {
        path.add(cur)
        cur = this[cur] ?: throw InputMismatchException("Parent not found!")
    }
    return path
}

/**
 * Maps the shortest path from [start] to [end]
 * Optionally can use [includeDiagonal]
 */
fun shortestPath(
    start: Point2D,
    end: Point2D,
    includeDiagonal: Boolean = false,
    validator: (current: Point2D, next: Point2D) -> Boolean
): List<Point2D>? {
    val best = mutableMapOf<Point2D, Int>()
    val tracking = mutableMapOf<Point2D, Point2D>()
    val queue = LinkedList<Point2D>()
    best[start] = 0
    queue.add(start)
    while (queue.size > 0) {
        val cur: Point2D = queue.poll()
        if (cur == end) {
            return tracking.pathThroughList(cur)
        }
        for (c in (if (includeDiagonal) cur.neighbors else cur.adjacent)) {
            // skip if outside bounds or if height is more than one above current
            if (!validator(cur, c)) continue
            val testPath = best[cur]!! + 1
            if (testPath < best.getOrDefault(c, Int.MAX_VALUE)) {
                best[c] = testPath
                tracking[c] = cur
                queue.add(c)
            }
        }
    }
    return null
}
