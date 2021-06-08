package curs3

import com.cra.figaro.language.{Flip}	
import com.cra.figaro.algorithm.factored.VariableElimination

object Ex1 {
    val sunnyToday = Flip(0.85)
    def main(args: Array[String]) {	
      // step 1
      println("Probab. for false is " + VariableElimination.probability(sunnyToday, false))
      // step 2
      
      sunnyToday.generate()
      println("A generated value: " + sunnyToday.value)
      sunnyToday.generate()
      println("Another generated value: " + sunnyToday.value)
      // play with various values for Flip() and run it several times
      
    }
}
