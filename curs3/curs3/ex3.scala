package curs3

import com.cra.figaro.library.atomic.discrete.Binomial	
import com.cra.figaro.algorithm.factored.VariableElimination

object Ex3 {
    def main(args: Array[String]) {	
      val numSunnyDaysInWeek = Binomial(7, 0.2)
      // step 1
      println("Probab. for 3 is " + 
              VariableElimination.probability(numSunnyDaysInWeek, 3))
      // step 2
      numSunnyDaysInWeek.generate()
      println("A generated value: " + numSunnyDaysInWeek.value)
      // run it several times
    }
}