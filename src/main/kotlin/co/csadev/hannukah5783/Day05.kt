/**
 * Copyright (c) 2022 by Charles Anderson
 * Advent of Code 2022, Day 20
 * Problem Description: http://adventofcode.com/2021/day/20
 */
package co.csadev.hannukah5783

import co.csadev.adventOfCode.BaseDay

class Day05(override val input: String = "") :
    BaseDay<String, String, Int> {

    private val cat = products.filter { it.desc.contains("Cat") }.map { it.sku }
    private val catCustomers = orders
        .asSequence()
        .filter { it.items.any { item -> item.sku in cat } }
        .map { it.customerid }
    override fun solvePart1(): String {
        val queensCustomers = customers
            .asSequence()
            .filter { it.citystatezip.contains("Queens Village") }
            .filter { it.customerid in catCustomers }
            .sortedBy { catCustomers.count { c -> c == it.customerid } }
            .toList()
        return queensCustomers.last().phone
    }

    override fun solvePart2() = 0
}
