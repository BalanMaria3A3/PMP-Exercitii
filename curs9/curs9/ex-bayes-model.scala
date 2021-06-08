package curs9

import com.cra.figaro.language.{Flip}
import com.cra.figaro.library.compound.{CPD,^^}
import com.cra.figaro.library.atomic.discrete.Binomial
import com.cra.figaro.library.atomic.continuous.Beta
import com.cra.figaro.language.Flip
import com.cra.figaro.algorithm.sampling.{Importance}

object ExBayesModel {
    val bias = Beta(1,1)
    val NumberOfHeads = Binomial(100, bias)
    val toss101 = Flip(bias)
    
    def main(args: Array[String]) {
        
        val algorithm1 = Importance(bias, toss101)
        algorithm1.start()
        Thread.sleep(1000)
        algorithm1.stop()
        println("Average bias before = " + algorithm1.mean(bias))
        println("Probability of head on toss101 = " + 
            algorithm1.probability(toss101, true))
        algorithm1.kill()

        NumberOfHeads.observe(63)
        
        val algorithm2 = Importance(bias, toss101)
        algorithm2.start()
        Thread.sleep(1000)
        algorithm2.stop()
        println("Average bias after = " + algorithm2.mean(bias))
        println("Probability of head on toss101 = " + 
            algorithm2.probability(toss101, true))

        println(algorithm2.distribution(toss101))

        algorithm2.kill()

    }
}