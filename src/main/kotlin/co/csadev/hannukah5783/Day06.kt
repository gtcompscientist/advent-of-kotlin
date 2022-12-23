/**
 * Copyright (c) 2022 by Charles Anderson
 * Advent of Code 2022, Day 20
 * Problem Description: http://adventofcode.com/2021/day/20
 */
package co.csadev.hannukah5783

import co.csadev.adventOfCode.BaseDay

class Day06(override val input: String = "") :
    BaseDay<String, String, Int> {

    private val ordersLoss = orders.map {
        it to it.items.sumOf { item ->
            item.unit_price - products.first { p -> p.sku == item.sku }.wholesale_cost
        }
    }
        .filter { (_, p) -> p < 0.0 }
        .sortedByDescending { (_, p) -> p }
    private val orderLossCustomers = ordersLoss.map { (o, _) -> o.customerid }

    override fun solvePart1(): String {
        val saleFinders = customers
            .asSequence()
            .filter { it.citystatezip.contains("Queens Village") }
            .filter { it.customerid in orderLossCustomers }
            .toList()
        return saleFinders.first().phone
    }

    override fun solvePart2() = 0
}
