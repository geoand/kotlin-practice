package geoand.kotlinpractice

import spock.lang.Specification
import spock.lang.Unroll

/**
 * Created by George Andrianakis on 11/6/2016.
 */
class FactorialSpec extends Specification {

    def "invalid input"() {
        when:
            Factorial.calculate(input)

        then:
            thrown IllegalArgumentException

        where:
            input << [0, -1]

    }

    @Unroll
    def "valid input"(Integer input, String expectedResult) {
        expect:
            Factorial.calculate(input).toString() == expectedResult

        where:
            input | expectedResult
            1 | "1"
            2 | "2"
            3 | "6"
            5 | "120"
            25 | "15511210043330985984000000"

    }
}
