package geoand.kotlinpractice.extensions

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatExceptionOfType
import org.jetbrains.spek.api.Spek
import java.util.*

/**
 * Created by gandrianakis on 30/5/2016.
 */
class ListTailSpec: Spek({

    describe("List.tail") {

        it("should throw exception when calling tail on empty list") {
            assertThatExceptionOfType(NoSuchElementException::class.java).isThrownBy({
                listOf<Int>().tail()
            })
        }

        it("should return an empty list when the list only has one element") {
            assertThat(listOf(1).tail()).isEmpty()
        }

        it("should return all but the first element on a list of size > 1") {
            assertThat(listOf(1, 2, 3).tail()).containsExactly(2, 3)
        }
    }
})