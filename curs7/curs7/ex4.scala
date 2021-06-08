package curs07

import com.cra.figaro.util.memo 
import com.cra.figaro.library.atomic.discrete.Uniform
import com.cra.figaro.algorithm.factored.VariableElimination
import com.cra.figaro.library.compound.^^

object Ex4 {
    class Person() {
        val interest = Uniform("sport", "politics")
    }

    class Connection(person1: Person, person2: Person) {
        val connectionType = Uniform("acquaintance", "close friend", "family")
    }

    def generateConnection(pair: (Person, Person)) = new Connection(pair._1, pair._2)
    // val connection = generateConnection _  // you get a new connection every time
    val connection = memo(generateConnection _)  // you get the same connection every time

    val john = new Person()
    val peter = new Person()
    
    def main(args: Array[String]) {
        println("first: " + 
          VariableElimination.probability(connection(john, peter).connectionType, "close friend"))    
        connection(john, peter).connectionType.observe("close friend")
        println("second: " + 
           VariableElimination.probability(connection(john, peter).connectionType, "close friend"))
    }
    
}