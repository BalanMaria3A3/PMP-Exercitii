package curs4

import com.cra.figaro.language.{Flip}
import com.cra.figaro.library.atomic.discrete.{Binomial}	
import com.cra.figaro.library.atomic.continuous.{Beta}
import com.cra.figaro.algorithm.sampling.Importance

object Ex3 {
    val bias = Beta(2,5)
    val numberOfHeads = Binomial(100, bias)
    val toss101 = Flip(bias)
    def main(args: Array[String]) {
        val algorithm1 = Importance(10000, toss101) 
        algorithm1.start() 
        println(algorithm1.probability(toss101, true))

        val algorithm2 = Importance(toss101) 
        algorithm2.start()
        Thread.sleep(10) // try various values
        println(algorithm2.probability(toss101, true))
    }
}