/**
 * Copyright (c) 2022 by Charles Anderson
 * Advent of Code 2022, Day 20
 * Problem Description: http://adventofcode.com/2021/day/20
 */
package co.csadev.hannukah5783

import co.csadev.adventOfCode.BaseDay

class Day00(override val input: String? = null) :
    BaseDay<String?, Int, Int> {

    override fun solvePart1(): Int {
        // No need to resolve this every time
        return 5777
//        val output = resourceAsFile("83day00.json")
//        output.mkdirs()
//        if (!output.exists()) {
//            output.createNewFile()
//        }
//        return generateSequence(0) { it + 1 }.first {
//            try {
//                ZipFile(resourceAsFile("83data.zip"), "$it".toCharArray()).extractAll(output.absolutePath)
//                true
//            } catch (ex: ZipException) {
//                false
//            }
//        }
    }

    override fun solvePart2() = 0
}
