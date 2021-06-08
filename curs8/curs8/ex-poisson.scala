package curs8

import com.cra.figaro.language._
import com.cra.figaro.library.atomic.discrete.{Poisson}
import com.cra.figaro.algorithm.factored.VariableElimination
//import com.cra.figaro.algorithm.sampling.Importance

object ExPoisson {
    
  def main(args: Array[String]) {
    val p = Poisson(2)
    // val alg = Importance(10000, p)
    // alg.start()
    for {i <- 0 until 10}
        println(VariableElimination.probability(p, i))
  }
}