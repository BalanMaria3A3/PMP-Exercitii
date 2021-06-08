import com.cra.figaro.algorithm.factored.VariableElimination
import com.cra.figaro.language.{Chain, Constant, Flip, Select}
import com.cra.figaro.library.compound.If


//In the original Hello World program, observe that today’s greeting is “Oh no,
//not again!” and query today’s weather. Now observe the same evidence and ask
//the same query on your modified program from exercise 1. What happened to
//the answer to the query? Can you explain the result intuitively?

object Ex2 {
  val sunnyToday=Flip(0.2)
  val greetingToday= If(sunnyToday,
    Select(0.6 -> "Hello, world!", 0.4 -> "Howdy, universe!"),
    Select(0.2 -> "Hello, world!", 0.8-> "Oh no, not again!"))

  val sunnyToday1 = Flip(0.2)

  val greetingToday1 = If(sunnyToday1,
    Select(0.6 -> "Hello, world!", 0.4 -> "Howdy, universe!"),
    Select(0.2 -> "Hello, world!", 0.8 -> "Oh no, not again!"))

  val bedSide1 = Flip(0.4)
  val greeting1 = Chain(bedSide1, (side: Boolean) =>
    if (!side) {
      Constant("Oh no, not again!")
    }
    else
      greetingToday1
  )

  def main(args: Array[String]) {
    greetingToday.observe("Oh no, not again!")
    println(VariableElimination.probability(sunnyToday, true))


    greeting1.observe("Oh no, not again!")
    println(VariableElimination.probability(sunnyToday1, true))
  }
}
