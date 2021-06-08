package curs6

import com.cra.figaro.language.{Flip, Constant}
import com.cra.figaro.algorithm.factored.VariableElimination
import com.cra.figaro.library.collection.FixedSizeArray
import com.cra.figaro.library.collection.Container

object Ex6 {
    def main(args: Array[String]) {
        val list = List(Flip(0.3), Flip(0.8), Flip(0.6))  // Scala list
        val cont = Container(list:_*)  // conversion to Figaro container
        println(VariableElimination.probability(cont(2), true))
        // conversion from Figaro Container to Scala list
        val list2 = cont.chain(if (_) Constant(1) else Constant(2)).elements
        for (elem <- list2)
            println(VariableElimination.probability(elem, 1))
        // the following does not work
        /*
        for (elem <- cont)
            println(VariableElimination.probability(elem, true))
        */
    }
}