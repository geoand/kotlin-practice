package geoand.kotlinpractice

import spock.lang.Specification
import spock.lang.Unroll

/**
 * Created by gandrianakis on 30/5/2016.
 */
class IsPermutationOfSpec extends Specification {

    final IsPermutationOf sut = new IsPermutationOf()

    @Unroll
    def "candidate is longer that base, obviously can't be a permutation"(List base, List candidate) {
        expect:
            !sut.determine(base, candidate)

        where:
            base | candidate
            "" as List<Character> | "a" as List<Character>
            "a" as List<Character> | "ab" as List<Character>
            "123a" as List<Character> | "123abc" as List<Character>
            [] | [1]
            [1, 2] | [1, 2, 3]
    }

    @Unroll
    def "candidate is the exactly the same as base"(List base, List candidate) {
        expect:
            sut.determine(base, candidate)

        where:
            base | candidate
            "" as List<Character> | "" as List<Character>
            "a" as List<Character> | "a" as List<Character>
            "123a" as List<Character> | "123a" as List<Character>
            [] | []
            [1, 2] | [1, 2]
    }

    @Unroll
    def "candidate is smaller that base, and is a permutation of it"(List base, List candidate) {
        expect:
            sut.determine(base, candidate)

        where:
            base | candidate
            [1,2,3] | [1]
            [1,2,3] | [1,2]
            [1,2,3] | [1,2,3]
            [3,2,1] | [1]
            [3,2,1] | [1,2]
            [3,2,1] | [1,2,3]
            [3,1,2] | [1]
            [3,1,2] | [1,2]
            [3,1,2] | [1,2,3]
            [1, 10, 6, 4, 7, 2, 99] | [2, 99, 7]
            [1, 10, 6, 4, 7, 2, 99] | [10, 7, 2, 4, 6, 99]
    }

    @Unroll
    def "is not permutation because although all the characters of the candidate exist in base, the exist with other elements in between"(List base, List candidate) {
        expect:
            !sut.determine(base, candidate)

        where:
            base | candidate
            [1,2,3] | [1,3]
            [3,2,1] | [3, 1]
            [1, 10, 6, 4, 7, 2, 99] | [1, 2]
            [1, 10, 6, 4, 7, 2, 99] | [10, 6, 2, 99]
    }
}
