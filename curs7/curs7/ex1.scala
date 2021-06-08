package curs07

import com.cra.figaro.library.atomic.discrete.Uniform
import com.cra.figaro.algorithm.factored.VariableElimination
import com.cra.figaro.library.compound.^^
import com.cra.figaro.language.Select

object Ex1 {
    class Person() {
        // val interest = Uniform("sport", "politics")
        val interest = Select(0.3 -> "sport", 0.7 -> "politics")
        // private val interest = Uniform("sport", "politics") // compilation error
    }
    val john = new Person()
    val peter = new Person()
    
    val pair = ^^(john.interest, peter.interest)
    def constraint(pair: (String, String)) = {
        val (topicMatch1, topicMatch2) = pair
        if (topicMatch1 == topicMatch2) 0.75 else 0.25
    }
    pair.addConstraint(constraint _)
    //peter.interest.observe("politics")
    
    def main(args: Array[String]) {
        println("john: " + VariableElimination.probability(john.interest, "sport"))
        println("peter: " + VariableElimination.probability(peter.interest, "sport"))
    }
    
}