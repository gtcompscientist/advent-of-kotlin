/*
 * Copyright (c) 2021 by Charles Anderson
 */

package co.csadev.advent2020

class Day04(input: String) {
    private val passports: List<String> = input.split("\n\n")

    fun solvePart1(): Int =
        passports
            .count { passport -> expectedFields.all { passport.contains(it) } }

    fun solvePart2(): Int =
        passports
            .count { passport -> fieldPatterns.all { it.containsMatchIn(passport) } }

    companion object {

        private val expectedFields = listOf("byr:", "iyr:", "eyr:", "hgt:", "hcl:", "ecl:", "pid:")

        private val fieldPatterns = listOf(
            """\bbyr:(19[2-9][0-9]|200[0-2])\b""",
            """\biyr:(201[0-9]|2020)\b""",
            """\beyr:(202[0-9]|2030)\b""",
            """\bhgt:((1([5-8][0-9]|9[0-3])cm)|((59|6[0-9]|7[0-6])in))\b""",
            """\bhcl:#[0-9a-f]{6}\b""",
            """\becl:(amb|blu|brn|gry|grn|hzl|oth)\b""",
            """\bpid:[0-9]{9}\b"""
        ).map { it.toRegex() }
    }
}
