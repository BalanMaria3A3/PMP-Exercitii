package curs3

	
import com.cra.figaro.algorithm.factored.VariableElimination
import com.cra.figaro.language.{Flip, Select}
import com.cra.figaro.library.compound._

object Ex6 {
    def main(args: Array[String]) {	
      val sunnyToday = Flip(0.2)
      val e1 = Select(0.6 -> "Hello, world!", 0.4 -> "Howdy, universe!")
      val e2 = Select(0.2 -> "Hello, world!", 0.8 -> "Oh no, not again")
      val greetingToday = If(sunnyToday, e1,  e2)
      // step 1
      println(VariableElimination.probability(greetingToday, "Hello, world!"))
      // step 2
      greetingToday.generate()
      println("A generated value: " + greetingToday.value)
      // run it several times
    }
}