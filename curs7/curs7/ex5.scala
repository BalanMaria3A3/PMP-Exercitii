package curs07

import com.cra.figaro.library.atomic.discrete.Uniform
import com.cra.figaro.algorithm.factored.VariableElimination
import com.cra.figaro.library.compound.^^

object Ex5 {
    class Person() {
        val interest = Uniform("sport", "politics")
        // private val interest = Uniform("sport", "politics") // compilation error
    }
    val john = new Person()
    val peter = john
    def main(args: Array[String]) {
        println("john: " + VariableElimination.probability(john.interest, "sport"))
        println("peter: " + VariableElimination.probability(peter.interest, "sport"))
        john.interest.observe("politics")
        println("john after: " + VariableElimination.probability(john.interest, "sport"))
        println("peter after: " + VariableElimination.probability(peter.interest, "sport"))
    }
    
}