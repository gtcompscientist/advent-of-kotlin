/**
 * Copyright (c) 2022 by Charles Anderson
 * Advent of Code 2022, Day 20
 * Problem Description: http://adventofcode.com/2021/day/20
 */
package co.csadev.hannukah5783

import co.csadev.adventOfCode.BaseDay

class Day02(override val input: String = "") :
    BaseDay<String, String, Int> {

    override fun solvePart1(): String {
        val jdCustomers = customers
            .filter { it.name.first() == 'J' }
            .filter { it.lastName().first() == 'D' }
        val jdIds = jdCustomers.map { it.customerid }
        val bagels = products.filter { it.desc.contains("bagel", ignoreCase = true) }
            .map { it.sku }
        val jd2017BagelCustomers = orders.asSequence()
            .filter { it.ordered == it.shipped }
            .filter { it.ordered.startsWith("2017") }
            .filter { it.customerid in jdIds }
            .filter { it.items.any { item -> item.sku in bagels } }
            .map { it.customerid }.toList()
        val customer = jdCustomers.first { it.customerid in jd2017BagelCustomers }
        return customer.phone
    }

    override fun solvePart2() = 0
}
