package curs6

import com.cra.figaro.language.Flip
import com.cra.figaro.algorithm.factored.VariableElimination

object Ex2 {
    def main(args: Array[String]) {
        val n = 3
        val arr = Array.fill(n)(Flip(1.0/(n+1)))
        for {
            i <- 0 until n 
        } {
            println(i + " :" + VariableElimination.probability(arr(i), true))
        }
        
        val arr2 = Array.tabulate(n)((i) => Flip(1.0/(i+1)))
        for {
            i <- 0 until n 
        } {
            println(i + " :" + VariableElimination.probability(arr2(i), true))
        }
        
    }
}