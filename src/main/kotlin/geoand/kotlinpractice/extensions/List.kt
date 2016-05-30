package geoand.kotlinpractice.extensions

import java.util.*

/**
 * Created by gandrianakis on 30/5/2016.
 */

fun <T> List<T>.tail(): List<T> {
    if(this.isEmpty()) {
        throw NoSuchElementException("Cannot access tail() for an empty list") //used the same error as Groovy provides
    }

    return drop(1)
}

fun <T> List<T>.collate(size: Int, step: Int = size): List<List<T>> {
    require(size >= 0) {"size must not be negative"}
    require(step <= size) {"step must be at most equal to size"}

    return collateRec(this, size, step, mutableListOf())
}

private tailrec fun <T> collateRec(list : List<T>, size: Int, step: Int, accumulator: MutableList<List<T>>) : List<List<T>> {
    if(list.isEmpty()) {
        return accumulator
    }

    if(list.size < size) {
        return appendToAccumulatorAndReturn(accumulator, list)
    }

    return collateRec(list.subList(step, list.size), size, step, appendToAccumulatorAndReturn(accumulator, list.subList(0, size)))
}

private fun <T> appendToAccumulatorAndReturn(accumulator: MutableList<List<T>>, list: List<T> ) : MutableList<List<T>> {
    accumulator.add(list)
    return accumulator
}
