package geoand.kotlinpractice

import java.math.BigInteger


/**
 * Created by George Andrianakis on 11/6/2016.
 */

object Factorial {

    @JvmStatic fun calculate(num: Int) : BigInteger {
        require(num >= 1)

        return (1..num).fold(BigInteger.ONE) { accumulator, index ->
            accumulator * BigInteger.valueOf(index.toLong())
        }
    }
}
