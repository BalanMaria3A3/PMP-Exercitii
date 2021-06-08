package curs3

import com.cra.figaro.language.{Select}	
import com.cra.figaro.algorithm.factored.VariableElimination

object Ex2 {
    val greeting = 
      Select(0.88 -> "Hello, world!", 
             0.77 -> "Howdy, universe!", 
             0.1-> "Oh no, not again"
            )
    def main(args: Array[String]) {	
      // step 1
      println("Probab. for Hello, world! is " + 
              VariableElimination.probability(greeting, "Hello, world!"))
      // step 2
      greeting.generate()
      println("A generated value: " + greeting.value)
      // run it several times
    }
}