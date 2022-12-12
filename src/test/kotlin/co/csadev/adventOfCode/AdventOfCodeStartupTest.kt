/*
 * Copyright (c) 2022 by Charles Anderson
 */

package co.csadev.adventOfCode

import org.junit.jupiter.api.Test
import java.io.File
import java.util.Calendar

/**
 * Run this to generate everything to start a new year fresh
 * ./gradlew test --tests "co.csadev.adventOfCode.AdventOfCodeStartupTest.Generate new year of Advent of Code"
 */
@Suppress("Indentation", "MaximumLineLength", "MaxLineLength")
class AdventOfCodeStartupTest {
    // Get year
    private val year = Calendar.getInstance().get(Calendar.YEAR)
    private val yearLastTwo = year.toString().substring(2)
    private val srcRoot = File("src")

    init {
        println("Setting up $year Advent Of Code ($yearLastTwo)")
    }

    @Test
    fun `Generate new year of Advent of Code`() {
        // Create main package and test package
        val mainPackage = File(srcRoot, "main/kotlin/co/csadev/advent$year")
        mainPackage.mkdirs()
        val testPackage = File(srcRoot, "test/kotlin/co/csadev/advent$year")
        testPackage.mkdirs()

        // Create README w/links and blank titles
        generateReadme(mainPackage)

        // Create Day files and Test files
        repeat(25) {
            val day = it + 1
            val padDay = day.toString().padStart(2, '0')
            generateWorkingFile(mainPackage, day, padDay)
            generateInputFile(padDay)
            generateTestFile(testPackage, padDay)
        }
        assert(true)
    }

    private fun generateReadme(main: File) {
        File(main, "README.md").run {
            if (exists()) return@run
            val readmeText = """
                $year Advent Of Code Index

                |   Day   | Title                                         |  Links                                       |
                | --------|-----------------------------------------------|--------------------------------------------- |
            """.trimIndent()
            writeText(readmeText)
            appendText("\n")
            repeat(25) {
                val day = it + 1
                val padDay = day.toString().padStart(2, ' ')
                appendText(
                    """
                |   $padDay   |  TBD                                          | [\[Solution\]](/Day$padDay.kt) [\[AoC\]](http://adventofcode.com/$year/day/$day) |
                    """.trimIndent()
                )
                appendText("\n")
            }
        }
    }

    private fun generateWorkingFile(mainPackage: File, day: Int, padDay: String) {
        File(mainPackage, "Day$padDay.kt").run {
            if (exists()) return@run
            val dayFile = """
                    /*
                     * Copyright (c) $year by Charles Anderson
                     */

                    /**
                     * Advent of Code $year, Day $padDay
                     * Problem Description: http://adventofcode.com/$year/day/$day
                     */
                    package co.csadev.advent$year

                    import co.csadev.adventOfCode.BaseDay
                    import co.csadev.adventOfCode.Resources.resourceAsList

                    class Day$padDay(override val input: List<String> = resourceAsList("${yearLastTwo}day$padDay.txt")) :
                        BaseDay<List<String>, Int, Int> {

                        override fun solvePart1() = 0

                        override fun solvePart2() = 0
                    }
                    
                    """.trimIndent()
            writeText(dayFile)
        }
    }

    private fun generateInputFile(padDay: String) {
        // Input file
        File(srcRoot, "main/resources/${yearLastTwo}day$padDay.txt").run {
            if (exists()) return@run
            writeText("")
        }
    }

    private fun generateTestFile(testPackage: File, padDay: String) {
        File(testPackage, "Day${padDay}Test.kt").run {
            if (exists()) return@run
            val testFile = """
                    /*
                     * Copyright (c) $year by Charles Anderson
                     */

                    package co.csadev.advent$year

                    import org.assertj.core.api.Assertions
                    import org.junit.jupiter.api.Test

                    class Day${padDay}Test {

                        private val input = ""${'"'}
                        ""${'"'}.trimIndent().lines()

                        private val example = Day$padDay(input)
                        private val real = Day$padDay()

                        @Test
                        fun `Matches example`() {
                            val part1 = example.solvePart1()
                            Assertions.assertThat(part1).isEqualTo(0)

                            val part2 = example.solvePart2()
                            Assertions.assertThat(part2).isEqualTo(0)
                        }

                        @Test
                        fun `Actual answer`() {
                            val part1 = real.solvePart1()
                            Assertions.assertThat(part1).isEqualTo(0)

                            val part2 = real.solvePart2()
                            Assertions.assertThat(part2).isEqualTo(0)
                        }
                    }
                    
                    """.trimIndent()
            writeText(testFile)
        }
    }
}
