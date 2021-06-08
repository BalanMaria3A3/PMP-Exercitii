package curs3

	
import com.cra.figaro.algorithm.factored.VariableElimination
import com.cra.figaro.language.{Flip, Apply, Chain}
import com.cra.figaro.library.atomic.discrete.Binomial

object Ex10 {
    def main(args: Array[String]) {	
      val sunnyDaysInMonth = Binomial(30, 0.2)
      val teamWinsInMonth = Binomial(5, 0.4)
      val monthQuality = 
        Apply(sunnyDaysInMonth, teamWinsInMonth,
            (days: Int, wins: Int) => 
            {
                val x = days * wins
                if (x > 20) "good"; 
                else if (x > 10) "average"; 
                else "poor"
            }
        )
      val goodMood = Chain(monthQuality, (s: String) =>
        if (s == "good") Flip(0.9)
        else if (s == "average") Flip(0.6)
        else Flip(0.1))
      // step 1
      println(VariableElimination.probability(goodMood, false))
      // step 2
      goodMood.generate()
      println("A generated value: " + goodMood.value)
      // run it several times
    }
}