package curs6

import com.cra.figaro.language.{Flip, Constant, Select}
import com.cra.figaro.algorithm.factored.VariableElimination
import com.cra.figaro.library.collection.Container
import com.cra.figaro.library.collection.FixedSizeArray

object Ex5
 {
    def main(args: Array[String]) {
        val toss1 = Flip(0.2)
        val toss2 = Flip(0.4)
        val toss3 = Flip(0.7)
        val tosses = Container(toss1, toss2, toss3)

        tosses(1).observe(false)
        println(VariableElimination.probability(tosses(2), true))
        tosses(1).unobserve()

        val out1 = Select ( 0.3 -> 'white, 0.7 -> 'black )
        val out2 = Select ( 0.5 -> 'white, 0.5 -> 'black )
        val depCont = tosses.chain(if (_) out1 else out2)
        /* [if (tosses(0)) out1 else ou2, if (tosses(1)) out1 else ou2, ...*/

        println("Apriori:")
        println(VariableElimination.probability(depCont(1), 'white))
        println(VariableElimination.probability(depCont(2), 'white))
        tosses(1).observe(false)
        println("Aposteriori:")
        println(VariableElimination.probability(depCont(1), 'white))
        println(VariableElimination.probability(depCont(2), 'white))

        // arrays are containers
        val n = 4;
        val flipArr = new FixedSizeArray(n, i => Flip(1.0/(i + 2.0)))
        val depArr = flipArr.chain(if (_) Constant(1) else Constant(2))
        println("Arrays:")
        for { 
            i <- 0 until n 
        } {
            println(i + ": " + 
                VariableElimination.probability(flipArr(i), true) +
                "; " +
                VariableElimination.probability(depArr(i), 1))
        }
        
    }
}