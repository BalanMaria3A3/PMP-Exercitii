
package curs9

import com.cra.figaro.language.{Flip, Select}
import com.cra.figaro.library.compound.{CPD,^^}
import com.cra.figaro.algorithm.factored.VariableElimination

object ExChainRule {
    val subject = Select(0.8 -> 'people, 0.2 -> 'landscape)
    val size = CPD(subject,
                    'people    -> Select(0.25 -> 'small, 0.25 -> 'medium, 0.5 -> 'large),
                    'landscape -> Select(0.25 -> 'small, 0.5 -> 'medium, 0.25 -> 'large)
                  )
    val brightness = CPD(subject,
                         'people    -> Select(0.8 -> 'dark, 0.2 -> 'bright),
                         'landscape -> Select(0.3 -> 'dark, 0.7 -> 'bright)
                        )

    val triple = ^^(subject, size, brightness)
    
    def main(args: Array[String]) {
        println(VariableElimination.probability(triple, ((xyz: (Symbol, Symbol, Symbol)) => 
            xyz._1 == 'people && xyz._2 == 'small && xyz._3 == 'dark)))
        println(VariableElimination.probability(triple, ((xyz: (Symbol, Symbol, Symbol)) => 
            xyz._1 == 'people && xyz._2 == 'small && xyz._3 == 'bright)))
        println(VariableElimination.probability(triple, ((xyz: (Symbol, Symbol, Symbol)) => 
            xyz._1 == 'people && xyz._2 == 'medium && xyz._3 == 'dark)))
        println(VariableElimination.probability(triple, ((xyz: (Symbol, Symbol, Symbol)) => 
            xyz._1 == 'people && xyz._2 == 'medium && xyz._3 == 'bright)))
        println(VariableElimination.probability(triple, ((xyz: (Symbol, Symbol, Symbol)) => 
            xyz._1 == 'people && xyz._2 == 'large && xyz._3 == 'dark)))
        println(VariableElimination.probability(triple, ((xyz: (Symbol, Symbol, Symbol)) => 
            xyz._1 == 'people && xyz._2 == 'large && xyz._3 == 'bright)))
        println(VariableElimination.probability(triple, ((xyz: (Symbol, Symbol, Symbol)) => 
            xyz._1 == 'landscape && xyz._2 == 'small && xyz._3 == 'dark)))
        println(VariableElimination.probability(triple, ((xyz: (Symbol, Symbol, Symbol)) => 
            xyz._1 == 'landscape && xyz._2 == 'small && xyz._3 == 'bright)))
        println(VariableElimination.probability(triple, ((xyz: (Symbol, Symbol, Symbol)) => 
            xyz._1 == 'landscape && xyz._2 == 'medium && xyz._3 == 'dark)))
        println(VariableElimination.probability(triple, ((xyz: (Symbol, Symbol, Symbol)) => 
            xyz._1 == 'landscape && xyz._2 == 'medium && xyz._3 == 'bright)))
        println(VariableElimination.probability(triple, ((xyz: (Symbol, Symbol, Symbol)) => 
            xyz._1 == 'landscape && xyz._2 == 'large && xyz._3 == 'dark)))
        println(VariableElimination.probability(triple, ((xyz: (Symbol, Symbol, Symbol)) => 
            xyz._1 == 'landscape && xyz._2 == 'large && xyz._3 == 'bright)))
    }
}