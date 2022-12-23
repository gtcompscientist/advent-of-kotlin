/**
 * Copyright (c) 2022 by Charles Anderson
 * Advent of Code 2022, Day 20
 * Problem Description: http://adventofcode.com/2021/day/20
 */
package co.csadev.hannukah5783

import co.csadev.adventOfCode.BaseDay

class Day04(override val input: String = "") :
    BaseDay<String, String, Int> {

    private val bakery = products.filter { it.sku.startsWith("BKY") }.map { it.sku }
    override fun solvePart1(): String {
        val most4amOrders = orders
            .asSequence()
            .filter { it.ordered.split("-", " ", ":")[3].toInt() == 4 }
            .filter { it.items.any { item -> item.sku in bakery } }
            .groupBy { it.customerid }
            .mapValues { (_, orders) -> orders.size }
        val tinderCustomers = customers
            .filter { it.customerid in most4amOrders.keys }
            .maxByOrNull { most4amOrders[it.customerid] ?: -1 }
        return tinderCustomers?.phone ?: "NONE"
    }

    override fun solvePart2() = 0
}
