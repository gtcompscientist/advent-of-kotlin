package co.csadev.adventOfCode

import kotlin.math.pow

infix fun Int.pow(exponent: Int): Int = toDouble().pow(exponent).toInt()

infix fun Long.pow(exponent: Int): Long = toDouble().pow(exponent).toLong()
