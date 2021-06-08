package curs9

import com.cra.figaro.language.{Flip, Select}
import com.cra.figaro.library.compound.{CPD,^^}
import com.cra.figaro.algorithm.factored.VariableElimination

object ExTotalRule {
    val triple =
        Select( 0.16 -> ('people, 'small,'dark),
                0.04 -> ('people, 'small,'bright),
                0.16 -> ('people, 'medium,'dark),
                0.04 -> ('people, 'medium,'bright),
                0.32 -> ('people, 'large,'dark),
                0.08 -> ('people, 'large,'bright),
                0.015 -> ('landscape, 'small,'dark),
                0.035 -> ('landscape, 'small,'bright),
                0.03  -> ('landscape, 'medium,'dark),
                0.07 -> ('landscape, 'medium,'bright),
                0.015 -> ('landscape, 'large,'dark),
                0.035 -> ('landscape, 'large,'bright)
             )   

    def main(args: Array[String]) {
        // P(Subject, Size, Brightness | Size = Small)
        triple.addCondition((xyz: (Symbol, Symbol, Symbol)) => xyz._2 == 'small)
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