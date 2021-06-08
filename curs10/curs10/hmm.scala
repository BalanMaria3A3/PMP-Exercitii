package curs10

import com.cra.figaro.language._
import com.cra.figaro.library.compound.If
import com.cra.figaro.algorithm.factored.VariableElimination
import com.cra.figaro.algorithm.factored.beliefpropagation._

object Hmm {
  val length = 90
  val confident: Array[Element[Boolean]] = Array.fill(length)(Constant(false))
  val ourPossession: Array[Element[Boolean]] = Array.fill(length)(Constant(false))
  confident(0) = Flip(0.4)
  for { minute <- 1 until length } {
    confident(minute) = If(confident(minute - 1), Flip(0.6), Flip(0.3))
  }
  for { minute <- 0 until length } {
    ourPossession(minute) = If(confident(minute), Flip(0.7), Flip(0.3))
  }

  def main(args: Array[String]) {
    
    for { i <- 1 until 30 }
    {
        ourPossession(i*3).observe(true)
    }
    println("VE: " + 
      VariableElimination.probability(confident(89), true))
    val algorithm = BeliefPropagation(1000, confident(89)) // try various values for the number of iterations
    algorithm.start()
    println("BP: " + algorithm.probability(confident(89), true))
    algorithm.kill()
  }
}