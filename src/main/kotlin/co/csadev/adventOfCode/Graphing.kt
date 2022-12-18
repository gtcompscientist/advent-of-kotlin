/*
 * Copyright (c) 2022 by Charles Anderson
 */

package co.csadev.adventOfCode

import com.sksamuel.scrimage.pixels.Pixel
import kotlin.math.absoluteValue

data class Point2D(val x: Int, val y: Int) : Point, Comparable<Point2D> {
    operator fun plus(other: Point2D): Point2D =
        Point2D(x + other.x, y + other.y)

    operator fun times(by: Int): Point2D =
        Point2D(x * by, y * by)

    infix fun distanceTo(other: Point2D): Int =
        (x - other.x).absoluteValue + (y - other.y).absoluteValue

    fun rotateLeft(): Point2D =
        Point2D(x = y * -1, y = x)

    fun rotateRight(): Point2D =
        Point2D(x = y, y = x * -1)

    /**
     * Cardinal direction neighbors only
     */
    val adjacent: List<Point2D> by lazy {
        listOf(copy(x = x + 1), copy(x = x - 1), copy(y = y + 1), copy(y = y - 1))
    }

    /**
     * All eight neighbors of the given [Point2D]
     */
    override val neighbors: List<Point2D> by lazy {
        (x - 1..x + 1).flatMap { dx ->
            (y - 1..y + 1).mapNotNull { dy ->
                Point2D(dx, dy).takeUnless { it == this }
            }
        }
    }

    /**
     * All eight neighbors, plus itself of a given [Point2D]
     */
    val neighborsInc: List<Point2D> by lazy {
        (x - 1..x + 1).flatMap { dx ->
            (y - 1..y + 1).mapNotNull { dy ->
                Point2D(dx, dy)
            }
        }
    }

    /**
     * Outputs an image of this pixel with the given color for visualizations
     */
    fun toPixel(argb: Int) = Pixel(x, y, argb)

    companion object {
        val ORIGIN = Point2D(0, 0)
    }

    override fun compareTo(other: Point2D): Int = when {
        x == other.x && y == other.y -> 0
        y > other.y -> 1
        y == other.y && x > other.x -> 1
        else -> -1
    }
}

fun List<Point2D>.printGraph(activeChar: Char = '#', inactiveChar: Char = ' ') =
    (0..maxOf { it.y }).forEach { y ->
        (0..maxOf { it.x }).fold("") { acc, x ->
            acc + if (contains(Point2D(x, y))) activeChar else inactiveChar
        }.let { println(it) }
    }

interface Point {
    val neighbors: List<Point>
}

data class Point3D(val x: Int, val y: Int, val z: Int) : Point {
    override val neighbors: List<Point3D> by lazy {
        (x - 1..x + 1).flatMap { dx ->
            (y - 1..y + 1).flatMap { dy ->
                (z - 1..z + 1).mapNotNull { dz ->
                    Point3D(dx, dy, dz).takeUnless { it == this }
                }
            }
        }
    }

    val immediateNeighbors: List<Point3D> by lazy {
        listOf(
            copy(x = x - 1),
            copy(x = x + 1),
            copy(y = y - 1),
            copy(y = y + 1),
            copy(z = z - 1),
            copy(z = z + 1)
        )
    }

    val neighborsSequence: Sequence<Point3D> by lazy {
        sequenceOf(
            copy(x = x - 1),
            copy(x = x + 1),
            copy(y = y - 1),
            copy(y = y + 1),
            copy(z = z - 1),
            copy(z = z + 1)
        )
    }

    val hexNeighbors: List<Point3D> by lazy {
        HEX_OFFSETS.map { this + it.value }
    }

    operator fun plus(other: Point3D): Point3D =
        Point3D(x + other.x, y + other.y, z + other.z)

    fun hexNeighbor(dir: String): Point3D {
        require(dir in HEX_OFFSETS)
        return HEX_OFFSETS.getValue(dir) + this
    }

    companion object {
        val ORIGIN = Point3D(0, 0, 0)
        val HEX_OFFSETS = mapOf(
            "e" to Point3D(1, -1, 0),
            "w" to Point3D(-1, 1, 0),
            "ne" to Point3D(1, 0, -1),
            "nw" to Point3D(0, 1, -1),
            "se" to Point3D(0, -1, 1),
            "sw" to Point3D(-1, 0, 1),
        )
    }
}

data class Point4D(val x: Int, val y: Int, val z: Int, val w: Int) : Point {
    override val neighbors: List<Point4D> by lazy {
        (x - 1..x + 1).flatMap { dx ->
            (y - 1..y + 1).flatMap { dy ->
                (z - 1..z + 1).flatMap { dz ->
                    (w - 1..w + 1).mapNotNull { dw ->
                        Point4D(dx, dy, dz, dw).takeUnless { it == this }
                    }
                }
            }
        }
    }
}
