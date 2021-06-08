package curs4

import com.cra.figaro.language.{Flip}
import com.cra.figaro.library.atomic.discrete.{Binomial}	
import com.cra.figaro.library.atomic.continuous.{Beta, Normal}
import com.cra.figaro.algorithm.sampling.MetropolisHastings
import com.cra.figaro.library.compound.^^

object Ex4 {
    val bias = Beta(2,5)
    val numberOfHeads = Binomial(100, bias)
    val toss101 = Flip(bias)

    val x = Normal(0.75, 0.2)
    val y = Normal(0.4, 0.2)
    x.setCondition((d: Double) => d > 0 && d < 1) 
    y.setCondition((d: Double) => d > 0 && d < 1) 
    val pair = ^^(x, y)
    
    def main(args: Array[String]) {
        println(MetropolisHastings.probability(pair,    // be patient, it takes a while
            (xy: (Double, Double)) => xy._1 > 0.5 && xy._2 > 0.5))
    }
}