package geoand.kotlinpractice.extensions

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatExceptionOfType
import org.jetbrains.spek.api.Spek

/**
 * Created by gandrianakis on 30/5/2016.
 */
class ListCollateSpec : Spek({

    fun assertAppropriateExceptionThrown(block: () -> Unit) {
        assertThatExceptionOfType(IllegalArgumentException::class.java).isThrownBy(block)
    }

    describe("Sequence.collate invalid arguments") {
        val list = (1..12).toList()

        it("should throw exception because size is negative") {
            assertAppropriateExceptionThrown { list.collate(-1, 2) }
        }

        it("should throw exception because step is larger than size") {
            assertAppropriateExceptionThrown { list.collate(1, 2) }
        }

        it("should throw exception because step is again larger than size") {
            assertAppropriateExceptionThrown { list.collate(10, 11)}
        }

    }

    describe("Sequence.collate no step cases") {

        val list = (1..12).toList()

        it("should return 12 parts of size 1 with no common elements") {
            val collatedList = list.collate(1)

            assertThat(collatedList).containsExactly(listOf(1), listOf(2), listOf(3), listOf(4), listOf(5), listOf(6), listOf(7), listOf(8), listOf(9), listOf(10), listOf(11), listOf(12))
        }

        it("should return 6 parts of size 2 with no common elements") {
            val collatedList = list.collate(2)

            assertThat(collatedList).containsExactly(listOf(1, 2), listOf(3, 4), listOf(5, 6), listOf(7, 8), listOf(9, 10), listOf(11, 12))
        }

        it("should return 4 parts of size 3 with no common elements") {
            val collatedList = list.collate(3)

            assertThat(collatedList).containsExactly(listOf(1, 2, 3), listOf(4, 5, 6), listOf(7, 8, 9), listOf(10, 11, 12))
        }

        it("should return 3 parts of size 4 with no common elements") {
            val collatedList = list.collate(4)

            assertThat(collatedList).containsExactly(listOf(1, 2, 3, 4), listOf(5, 6, 7, 8), listOf(9, 10, 11, 12))
        }

        it("should return 2 parts of size 6 with no common elements") {
            val collatedList = list.collate(6)

            assertThat(collatedList).containsExactly(listOf(1, 2, 3, 4, 5, 6), listOf(7, 8, 9, 10, 11, 12))
        }

        it("should return 1 part of size 12 with no common elements") {
            val collatedList = list.collate(12)

            assertThat(collatedList).containsExactly((1..12).toList())
        }

        it("should return a list where the last item is smaller") {
            val collatedList = (1..8).toList().collate(3)

            assertThat(collatedList).containsExactly(listOf(1, 2, 3), listOf(4, 5, 6), listOf(7, 8))
        }
    }

    describe("Sequence.collate with step cases") {

        val list = (1..12).toList()

        it("should return 11 parts of size 2 and a final part of size 1 containing the last element") {
            val collatedList = list.collate(2, 1)

            assertThat(collatedList).containsExactly(
                    listOf(1, 2), listOf(2, 3), listOf(3, 4), listOf(4, 5), listOf(5, 6), listOf(6, 7),
                    listOf(7, 8), listOf(8, 9), listOf(9, 10), listOf(10, 11), listOf(11, 12), listOf(12)
            )
        }

        it("should return 3 parts of size 6 and a final part of size 3") {
            val collatedList = list.collate(6, 3)

            assertThat(collatedList).containsExactly(listOf(1, 2, 3, 4, 5, 6), listOf(4, 5, 6, 7, 8, 9), listOf(7, 8, 9, 10, 11, 12), listOf(10, 11, 12))
        }

        it("should return on part of size 7 and one of size 6") {
            val collatedList = list.collate(7, 6)

            assertThat(collatedList).containsExactly(listOf(1, 2, 3, 4, 5, 6, 7), listOf(7, 8, 9, 10, 11, 12))
        }
    }

})