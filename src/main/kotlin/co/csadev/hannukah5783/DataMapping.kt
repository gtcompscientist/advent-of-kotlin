@file:Suppress("SpellCheckingInspection")

package co.csadev.hannukah5783

import co.csadev.adventOfCode.Resources
import com.google.gson.Gson

internal val gson = Gson()

data class Customer(
    val customerid: Int,
    val name: String,
    val address: String,
    val citystatezip: String,
    val birthdate: String,
    val phone: String
) {
    fun lastName() = name.split(" ")
        .run { if (size > 2) this[2] else this[1] }
}

data class Product(
    val sku: String,
    val desc: String,
    val wholesale_cost: Double
)

data class Order(
    val orderid: Int,
    val customerid: Int,
    val ordered: String,
    val shipped: String,
    val items: List<Item>,
    val total: Double
)

data class Item(
    val sku: String,
    val qty: Int,
    val unit_price: Double
)

val customers by lazy { Resources.resourceAsList("83noahs-customers.jsonl").map { gson.fromJson(it, Customer::class.java) } }
val products by lazy { Resources.resourceAsList("83noahs-products.jsonl").map { gson.fromJson(it, Product::class.java) } }
val orders by lazy { Resources.resourceAsList("83noahs-orders.jsonl").map { gson.fromJson(it, Order::class.java) } }
