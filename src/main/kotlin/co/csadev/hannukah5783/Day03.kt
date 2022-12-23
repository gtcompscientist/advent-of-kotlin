/**
 * Copyright (c) 2022 by Charles Anderson
 * Advent of Code 2022, Day 20
 * Problem Description: http://adventofcode.com/2021/day/20
 */
package co.csadev.hannukah5783

import co.csadev.adventOfCode.BaseDay

class Day03(override val input: String = "") :
    BaseDay<String, String, Int> {

    private val yearOfDog = listOf(2030, 2018, 2006, 1994, 1982, 1970, 1958)
    private val ariesMonth = (80..109)
    private val customerOrders = orders.map { it.customerid }
    override fun solvePart1(): String {
        val dogCustomers = customers
            .asSequence()
            .map { it.birthday() to it }
            .filter { (birthday, _) -> birthday.year in yearOfDog }
            .filter { (birthday, _) -> birthday.dayOfYear in ariesMonth }
            .filter { (_, c) -> c.customerid in customerOrders }
            .map { (_, c) -> c }
            .filter { it.citystatezip == "South Ozone Park, NY 11420" }
            .toSet()
        return dogCustomers.first().run {
            orders.filter { it.customerid == customerid }.forEach { o ->
                println(o)
            }
            phone
        }
    }

    override fun solvePart2() = 0
}
