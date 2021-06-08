package curs8

import com.cra.figaro.language._
import com.cra.figaro.algorithm.factored.VariableElimination

object ExUnivers{
    def main(args: Array[String]) {

        val fstUnivers = Universe.createNew()  // new Universe
        val x = Constant(1)
        Constant(2)("two",fstUnivers)
        println(VariableElimination.probability(x, 1))
        println(VariableElimination.probability(fstUnivers.get[Int]("two"), 2))
        
        val sndUnivers = Universe.createNew()
        val x1 = Constant(3)
        Constant(4)("two", sndUnivers)
        println(VariableElimination.probability(x, 1))
        println(VariableElimination.probability(x1, 3))
        println(VariableElimination.probability(sndUnivers.get[Int]("two"), 2))
        println(VariableElimination.probability(sndUnivers.get[Int]("two"), 4))
    
    }
}
