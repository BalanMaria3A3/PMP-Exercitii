package curs07


import com.cra.figaro.language.{Element, Select}
import com.cra.figaro.algorithm.factored.VariableElimination

object Ex3 {
    abstract class Person() {  // error if not abstract
        val interest: Element[String];
    }

    class YoungPerson extends Person {
        val interest = Select(0.7 -> "sport", 0.3 -> "politics")
    }
    
    class SeniorPerson extends Person {
        val interest = Select(0.4 -> "sport", 0.6 -> "politics")
    }

    val john = new YoungPerson()
    val peter = new SeniorPerson()
    
    def main(args: Array[String]) {
        println("john: " + VariableElimination.probability(john.interest, "sport"))
        println("peter: " + VariableElimination.probability(peter.interest, "sport"))
        peter.interest.observe("politics")
        println("john after: " + VariableElimination.probability(john.interest, "sport"))
    }
    
}