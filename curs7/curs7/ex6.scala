package curs07

import com.cra.figaro.util.memo 
import com.cra.figaro.library.atomic.discrete.Uniform
import com.cra.figaro.algorithm.factored.VariableElimination
import com.cra.figaro.library.compound.^^

object Ex6 {
    class Person() {
        val interest = Uniform("sport", "politics")
    }

    class Connection(person1: Person, person2: Person) {
        val connectionType = Uniform("acquaintance", "close friend", "family")
    }

    def generateConnection(pair: (Person, Person)) = new Connection(pair._1, pair._2)
    // val connection = generateConnection _  // you get a new connection every time
    val connection = memo(generateConnection _)  // you get the same connection every time

    class PersMatch(val lhs: Person, val rhs: Person) {  // val makes parameters public
    /*class PersMatch(lhs: Person, rhs: Person) {
        val p1 = lhs
        val p2 = rhs
    */
        val persMatch = lhs.interest === rhs.interest
        val pair = ^^(persMatch, connection(lhs,rhs).connectionType)
        
        def constraint(pair: (Boolean, String)) = {
            val (persMatch, connectionType) = pair
            if (persMatch) {
                if (connectionType == "family") 3.0
                else if (connectionType == "close friend") 8.0
                else 2.0
            } else 1.0
        }
        pair.addConstraint(constraint _)
       
    }

    val john = new Person()
    val peter = new Person()

    val jp = new PersMatch(john, peter)
    
    def main(args: Array[String]) {
        println("john == peter: " + VariableElimination.probability(jp.persMatch, true))
    }
    
}