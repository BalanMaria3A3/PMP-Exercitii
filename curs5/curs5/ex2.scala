package curs5

import com.cra.figaro.language.{Select, Constant, Flip, Chain}	
import com.cra.figaro.library.compound.{^^}
import com.cra.figaro.algorithm.factored.VariableElimination

object Ex2 {
    val color1 = Flip(0.3)
    val color2 = Flip(0.6)
    val pair = ^^(color1, color2)
    
    def sameColorConstraint(pair: (Boolean, Boolean)) =
        if (pair._1 == pair._2) 0.3; else 0.1  // try various weigths
    pair.setConstraint(sameColorConstraint _)


    val color3 = Flip(0.3)
    val color4 = Flip(0.6)
    val sameColorConstraintValue =
        Chain(color3, color4,
            (b1: Boolean, b2: Boolean) =>
            if (b1 == b2) Flip(0.3); else Flip(0.1))
    sameColorConstraintValue.observe(true)

    def main(args: Array[String]) {	
      // using Constraint
      println("Probab. for color1 true " + 
      VariableElimination.probability(color1, true))
      println("Probab. for color2 true " + 
      VariableElimination.probability(color2, true))
      // another way to get the same queries
      println("Probab. for pair._1 true " + 
      VariableElimination.probability(pair._1, true))
      println("Probab. for pair._2 true " + 
      VariableElimination.probability(pair._2, true))

      // using Observe
      println("Probab. for color3 true " + 
      VariableElimination.probability(color3, true))
      println("Probab. for color4 true " + 
      VariableElimination.probability(color4, true))
    }
}
