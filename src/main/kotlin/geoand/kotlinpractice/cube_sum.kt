package geoand.kotlinpractice

/**
 * Created by gandrianakis on 27/5/2016.
 */

/**
 * Prints numbers that are expressible as the sum of two cubes in two different ways
 */
fun solve(max: Int) {
    val range = 1.rangeTo(max)

    val resultsCache = mutableMapOf<Int, MutableList<Pair<Int, Int>>>()

    enumerateRangeCommutative(range, { pair ->
        val sum = sum(pair)

        val list = resultsCache[sum] ?: mutableListOf()
        list.add(pair)

        resultsCache[sum] = list
    })

    resultsCache.filter { entry -> entry.value.size > 1 }.toSortedMap().forEach { entry ->
        println("The result ${entry.key} is produced by ${entry.value}")
    }
}

private fun enumerateRangeCommutative(range: IntRange, consumer: (Pair<Int, Int>) -> Unit) {
    range.forEach { i ->
        //when performing a commutative enumeration we want to avoid consuming the pair(b,a) when a<b because they have already been consumed by pair(a,b) beforehand
        i.rangeTo(range.last).forEach { j ->
            consumer(Pair(i, j))
        }
    }
}

private fun sum(pair: Pair<Int, Int>) = power(pair.first) + power(pair.second)

private fun power(i: Int): Int = Math.pow(i * 1.0, 3.0).toInt()

fun main(args : Array<String>) {
    solve(if (args.size == 1) args.first().toInt() else 20)
}