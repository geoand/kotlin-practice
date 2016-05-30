package geoand.kotlinpractice

import geoand.kotlinpractice.extensions.collate

/**
 * Created by gandrianakis on 30/5/2016.
 */
class IsPermutationOf {

    /**
     * Implements an algorithm that is O(N*M*LOG(M)) where N is the size of the base and M is the size of the candidate
     */
    fun <T : Comparable<T>> determine(base: List<T>, candidate: List<T>) : Boolean {
        if(candidate.size > base.size)  {
            return false
        }

        if(base.isEmpty()) {
            return candidate.isEmpty()
        }

        val parts = base.collate(candidate.size, 1) //creates sub-lists of the base that have the same size as the candidate (except perhaps the last item)

        return parts.any { doDetermine(it, candidate) }
    }

    /**
     * Determines whether two the second list is a permutation of the first
     * The size of the candidate MUST be at least equal to the size of the base part
     * This is a simple O(M*LOG(M)) algorithm where M is the size of both the candidate (should be the same of the basePart, otherwise it's obviously not a permutation)
     */
    private fun <T : Comparable<T>> doDetermine(basePart: List<T>, candidate: List<T>) : Boolean {
        require(candidate.size >= basePart.size) {"Should only be called for when the candidate size is at least the basePart size (candidate.size = ${candidate.size}, basePart.size = ${basePart.size})"}

        if(basePart.size != candidate.size) {
            return false
        }

        return basePart.sortedBy { it } ==  candidate.sortedBy { it }
    }
}