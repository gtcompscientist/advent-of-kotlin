/*
 * Copyright (c) 2021 by Charles Anderson
 */

package co.csadev.advent2020

class Day08(input: List<String>) {
    data class Instruction(var instr: String, val movement: Int, var count: Int = 0) {
        val inc: Int
            get() = when (instr) {
                "acc" -> movement
                else -> 0
            }
        val move: Int
            get() = when (instr) {
                "jmp" -> movement
                else -> 1
            }
    }

    private val instructionMap =
        input.map { i ->
            i.split(" ").let { instr ->
                Instruction(
                    instr[0],
                    instr[1].replace("+", "").toInt()
                )
            }
        }

    fun solvePart1(): Int {
        var acc = 0
        var pos = 0
        var lastInstruction = instructionMap[pos]
        while (lastInstruction.count < 1) {
            acc += lastInstruction.inc
            pos += lastInstruction.move
            lastInstruction.count++
            lastInstruction = instructionMap[pos]
        }
        return acc
    }

    fun solvePart2(): Int = instructionMap.indices.asSequence().mapNotNull { idx ->
        when (instructionMap[idx].instr) {
            "jmp" -> checkMap(instructionMap.map { it.copy() }.toMutableList().also { it[idx].instr = "nop" })
            "nop" -> checkMap(instructionMap.map { it.copy() }.toMutableList().also { it[idx].instr = "jmp" })
            else -> null
        }
    }.first()

    private fun checkMap(instrMap: List<Instruction>): Int? {
        var acc = 0
        var pos = 0
        var lastInstruction = instrMap[pos]
        while (lastInstruction.count < 1) {
            acc += lastInstruction.inc
            pos += lastInstruction.move
            if (pos >= instrMap.size) {
                return acc
            }
            lastInstruction.count++
            lastInstruction = instrMap[pos]
        }
        return null
    }
}
