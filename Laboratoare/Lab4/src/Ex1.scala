import com.cra.figaro.algorithm.factored.VariableElimination
import com.cra.figaro.language.{Chain, Constant, Flip, Select}
import com.cra.figaro.library.compound.If

//Extend the Hello World program to add a variable representing the side of bed
//you got out of (right or wrong). If you got out of the wrong side of the bed, the
//greeting is always “Oh no, not again!” If you got out of the right side of the bed,
//the greeting logic is the same as before.

object Ex1 {
  val sunnyToday=Flip(0.2)
  val greetingToday= If(sunnyToday,
    Select(0.6-> "Hello, world", 0.4 -> "Howdy, universe!"),
    Select(0.2 -> "Hello, world!", 0.8-> "Oh no, not again!"))

  val bedSide = Flip(0.4)
  val greeting = Chain(bedSide, (side: Boolean) =>
    if (!side) {
      Constant("Oh no, not again!")
    }
    else
      greetingToday)

  def main(args: Array[String]): Unit = {
    val result = VariableElimination.probability(greeting, "Oh no, not again!")
    println("Today's greeting \"Oh no, not again!\" "+result)

    greeting.observe("Oh no, not again!")
    println(VariableElimination.probability(sunnyToday, true))
  }
}
