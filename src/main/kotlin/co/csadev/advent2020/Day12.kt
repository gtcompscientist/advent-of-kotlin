/*
 * Copyright (c) 2021 by Charles Anderson
 */

package co.csadev.advent2020

import co.csadev.adventOfCode.Point2D
import kotlin.math.abs

class Day12(input: List<String>) {
    private val directions = input.map { it[0] to it.substring(1).toInt() }

    enum class Direction {
        East,
        North,
        West,
        South;

        fun turn(t: Char, amount: Int) = when (t) {
            'L' -> values()[(this.ordinal + (amount / 90) + 4) % 4]
            else -> values()[(this.ordinal - (amount / 90) + 4) % 4]
        }
    }

    private data class Ship(
        var direction: Direction = Direction.East,
        var point: Point2D = Point2D(0, 0),
        var waypoint: Point2D = Point2D(10, 1)
    ) {
        fun move(dir: Char, amount: Int) {
            point = when (dir) {
                'E' -> point.move(Direction.East, amount)
                'N' -> point.move(Direction.North, amount)
                'W' -> point.move(Direction.West, amount)
                'S' -> point.move(Direction.South, amount)
                'F' -> point.move(direction, amount)
                else -> {
                    direction = direction.turn(dir, amount)
                    point
                }
            }
        }

        fun moveByWaypoint(dir: Char, amount: Int) {
            when (dir) {
                'E' -> waypoint = waypoint.move(Direction.East, amount)
                'N' -> waypoint = waypoint.move(Direction.North, amount)
                'W' -> waypoint = waypoint.move(Direction.West, amount)
                'S' -> waypoint = waypoint.move(Direction.South, amount)
                'F' -> point += waypoint.times(amount)
                else -> {
                    repeat(amount / 90) {
                        waypoint = when (dir) {
                            'L' -> waypoint.rotateLeft()
                            else -> waypoint.rotateRight()
                        }
                    }
                }
            }
        }
    }

    fun solvePart1(): Int {
        val s = Ship()
        directions.forEach { (d, a) ->
            s.move(d, a)
        }
        return abs(s.point.x) + abs(s.point.y)
    }

    fun solvePart2(): Int {
        val s = Ship()
        directions.forEach { (d, a) ->
            s.moveByWaypoint(d, a)
        }
        return abs(s.point.x) + abs(s.point.y)
    }
}

private fun Point2D.move(dir: Day12.Direction, amt: Int) = when (dir) {
    Day12.Direction.East -> copy(x = x + amt)
    Day12.Direction.North -> copy(y = y + amt)
    Day12.Direction.West -> copy(x = x - amt)
    else -> copy(y = y - amt)
}
