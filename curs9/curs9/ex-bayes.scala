
package curs9

import com.cra.figaro.language.{Flip, Select}
import com.cra.figaro.library.compound.{CPD,^^}
import com.cra.figaro.algorithm.factored.VariableElimination

object ExBayesRule {
    val subject = Select(0.8 -> 'people, 0.2 -> 'landscape)
    val size = CPD(subject,
                    'people    -> Select(0.25 -> 'small, 0.25 -> 'medium, 0.5 -> 'large),
                    'landscape -> Select(0.25 -> 'small, 0.5 -> 'medium, 0.25 -> 'large)
                  )

    def main(args: Array[String]) {
        size.observe('large)
        println(VariableElimination.probability(subject, 'people))
        println(VariableElimination.probability(subject, 'landscape))
    }
}