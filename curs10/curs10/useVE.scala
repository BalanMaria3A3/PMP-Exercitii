package curs10

import com.cra.figaro.language.{Flip, Select, Chain}
// import com.cra.figaro.library.compound.{CPD,^^}
import com.cra.figaro.algorithm.factored.VariableElimination

object UseVE {
    val elem1 = Select(0.5 -> 2.0, 0.2 -> 5.0, 0.3 -> 8.0)
    val elem2 = Chain(elem1, (x: Double) => 
            if (x < 3.0) Select(0.4 -> 10.0, 0.6 -> 20.0)  
            else Select(0.2 -> 10.0, 0.8 -> 20.0)
        )
    
    def main(args: Array[String]) {
        val algorithm1 = VariableElimination(elem1, elem2)
        algorithm1.start()
        println(algorithm1.probability(elem2, 10.0))
        println(algorithm1.probability(elem1, (x: Double) => x > 2.5))
        println(algorithm1.distribution(elem1).toList)
        println(algorithm1.mean(elem1))
        println(algorithm1.expectation(elem1, (x: Double) => x))
        elem1.observe(5)
        val algorithm2 = VariableElimination(elem2)
        algorithm2.start()
        println(algorithm2.distribution(elem2).toList)
        println(algorithm2.mean(elem2))
        println(algorithm2.expectation(elem2, (x: Double) => x))
        println(algorithm2.expectation(elem2, (x: Double) => x * x))
    }
}