/**
 * Copyright (c) 2022 by Charles Anderson
 * Advent of Code 2022, Day 22
 * Problem Description: http://adventofcode.com/2021/day/212
 */
package co.csadev.advent2022

import co.csadev.adventOfCode.BaseDay
import co.csadev.adventOfCode.Point2D
import co.csadev.adventOfCode.Resources.resourceAsList

@Suppress("CyclomaticComplexMethod") // HAVE YOU SEEN THOSE MOVES?
class Day22(override val input: List<String> = resourceAsList("22day22.txt")) :
    BaseDay<List<String>, Int, Int> {

    private val maxWidth = input.dropLast(2).maxOf { it.length }
    private val maxHeight = input.size - 2
    private val board = input.dropLast(2).map { it.padEnd(maxWidth, ' ').toCharArray() }.toTypedArray()
    private val start = Point2D(board[0].indexOfFirst { it == '.' }, 0)
    private val steps: Array<Int>
    private val turns: BooleanArray

    init {
        val path = input[input.size - 1]
        steps = path.split("L", "R").map { it.toInt() }.toTypedArray()
        turns = path.replace("\\d+".toRegex(), " ").trim { it <= ' ' }.split(" ").map { it == "L" }.toBooleanArray()
    }

    private enum class Dir(val p: Point2D, val char: Char) {
        RIGHT(Point2D(1, 0), '>'),
        DOWN(Point2D(0, 1), 'v'),
        LEFT(Point2D(-1, 0), '<'),
        UP(Point2D(0, -1), '^');

        companion object {
            private val vals by lazy { values() }
        }

        val turnRight: Dir
            get() = vals[(ordinal + 1).mod(vals.size)]

        val turnLeft: Dir
            get() = vals[(ordinal - 1).mod(vals.size)]
    }

    private enum class Side {
        A, B, C, D, E, F;

        companion object {
            val vals = values()
        }
    }

    private fun Side.turn(xOff: Int = 0, yOff: Int = 0) =
        Point2D(sideMapping[ordinal].first.first() + xOff, sideMapping[ordinal].second.first() + yOff)

    @Suppress("unused")
    private fun Point2D.printLocation(dir: Dir) {
        board.forEachIndexed { bY, chars ->
            if (y == bY) chars[x] = dir.char
            println(chars)
//            if (y == bY) chars[x] = '.'
        }
        println()
    }

    private fun Point2D.password(dir: Dir) = 1000 * (y + 1) + 4 * (x + 1) + (dir.ordinal)

    override fun solvePart1() = move { curr, dir ->
        curr.flatMove(dir)
    }

    override fun solvePart2() = move { curr, dir ->
        curr.cubeMove(dir)
    }

    private fun move(next: (Point2D, Dir) -> Pair<Point2D, Dir>): Int {
        var currPos = start
        var currDir = Dir.RIGHT
        steps.forEachIndexed { idx, move ->
            @Suppress("unused")
            for (i in 0 until move) {
//                currPos.printLocation()
                var testPos = currPos + currDir.p
                testPos = testPos.mod(maxWidth, maxHeight)
                val (nextPos, nextDir) = if (board[testPos.y][testPos.x] != ' ') (testPos to currDir) else next(currPos, currDir)
                if (board[nextPos.y][nextPos.x] == '#') {
                    break
                } else {
                    currPos = nextPos
                    currDir = nextDir
                }
            }
            val turn = turns.getOrNull(idx)
            turn?.let { currDir = if (it) currDir.turnLeft else currDir.turnRight }
        }
        return currPos.password(currDir)
    }

    private fun Point2D.flatMove(dir: Dir) = when (dir) {
        Dir.DOWN -> Point2D(x, board.indexOfFirst { it[x] != ' ' })
        Dir.UP -> Point2D(x, board.indexOfLast { it[x] != ' ' })
        Dir.RIGHT -> Point2D(board[y].indexOfFirst { it != ' ' }, y)
        Dir.LEFT -> Point2D(board[y].indexOfLast { it != ' ' }, y)
    } to dir

    var sideSize = 50
    var sideMapping = listOf(
        (50..99) to (0..49),
        (100..149) to (0..49),
        (50..99) to (50..99),
        (50..99) to (100..149),
        (0..49) to (100..149),
        (0..49) to (150..199),
    )

    private fun Point2D.cubeMove(currDir: Dir): Pair<Point2D, Dir> {
        val currSide = side
        return when {
            currSide == Side.A && currDir == Dir.UP -> Side.F.turn(yOff = x - sideSize) to Dir.RIGHT
            currSide == Side.A && currDir == Dir.LEFT -> Side.E.turn(yOff = sideSize - y - 1) to Dir.RIGHT
            currSide == Side.B && currDir == Dir.UP -> Side.F.turn(x - (sideSize * 2), sideSize - 1) to Dir.UP
            currSide == Side.B && currDir == Dir.RIGHT -> Side.D.turn(sideSize - 1, sideSize - 1 - y) to Dir.LEFT
            currSide == Side.B && currDir == Dir.DOWN -> Side.C.turn(sideSize - 1, x - 2 * sideSize) to Dir.LEFT
            currSide == Side.C && currDir == Dir.RIGHT -> Side.B.turn(y - sideSize, sideSize - 1) to Dir.UP
            currSide == Side.C && currDir == Dir.LEFT -> Side.E.turn(y - sideSize) to Dir.DOWN
            currSide == Side.E && currDir == Dir.LEFT -> Side.A.turn(yOff = sideSize - (y - 2 * sideSize) - 1) to Dir.RIGHT
            currSide == Side.E && currDir == Dir.UP -> Side.C.turn(yOff = x) to Dir.RIGHT
            currSide == Side.D && currDir == Dir.DOWN -> Side.F.turn(sideSize - 1, x - sideSize) to Dir.LEFT
            currSide == Side.D && currDir == Dir.RIGHT -> Side.B.turn(sideSize - 1, sideSize - (y - sideSize * 2) - 1) to Dir.LEFT
            currSide == Side.F && currDir == Dir.RIGHT -> Side.D.turn(y - 3 * sideSize, sideSize - 1) to Dir.UP
            currSide == Side.F && currDir == Dir.LEFT -> Side.A.turn(y - 3 * sideSize) to Dir.DOWN
            currSide == Side.F && currDir == Dir.DOWN -> Side.B.turn(x) to Dir.DOWN
            else -> throw NumberFormatException("Unknown position direction combination: $currSide, $currDir")
        }
    }

    private val Point2D.side: Side
        get() = Side.vals[sideMapping.indexOfFirst { x in it.first && y in it.second }]
}
