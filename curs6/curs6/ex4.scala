package curs6

import com.cra.figaro.language.{Flip, Constant}
import com.cra.figaro.algorithm.factored.VariableElimination
import com.cra.figaro.library.collection.FixedSizeArray

object Ex4 {
    def main(args: Array[String]) {
        if (args.length == 0) {
            println("No arguments!")
            return
        }
        val n =  args(0).toInt
        val flipArr = new FixedSizeArray(n, i => Flip(1.0/(i + 2.0)))
        for { 
            i <- 0 until n 
        } {
            println(i + ": " + 
                VariableElimination.probability(flipArr(i), true))
        }
    }
}