package curs3

	
import com.cra.figaro.algorithm.factored.VariableElimination
import com.cra.figaro.language.{Flip, Dist}
import com.cra.figaro.library.compound._

object Ex7 {
    def main(args: Array[String]) {	
      val goodMood = Dist(2.0 -> Flip(0.6), 8.0 -> Flip(0.2))
      // step 1
      println(VariableElimination.probability(goodMood, false))
      // step 2
      goodMood.generate()
      println("A generated value: " + goodMood.value)
      // run it several times
    }
}