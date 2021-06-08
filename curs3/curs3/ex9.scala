package curs3

	
import com.cra.figaro.algorithm.factored.VariableElimination
import com.cra.figaro.language.{Apply}
import com.cra.figaro.library.atomic.discrete.Binomial
import com.cra.figaro.library.compound._

object Ex9 {
    def main(args: Array[String]) {	
      val sunnyDaysInMonth = Binomial(30, 0.2)
      def getQuality(i: Int): String =
        if (i > 10) "good"; 
        else if (i > 5) "average"; 
        else "poor"
      val monthQuality = Apply(sunnyDaysInMonth, getQuality)
      // step 1
      println(VariableElimination.probability(monthQuality, "good"))
      // step 2
      monthQuality.generate()
      println("A generated value: " + monthQuality.value)
      // run it several times
    }
}