import com.cra.figaro.algorithm.factored.VariableElimination
import com.cra.figaro.language.{Flip, Select}
import com.cra.figaro.library.compound.If

//culegere - pag 50- Tabelul cu probabilitatile

object HelloWorld {
  //Define the model
  val sunnyToday = Flip(0.2); //probabilitatea ca azi sa fie sunny este 0.2
  val greetingToday= If (sunnyToday,
    Select(0.6 -> "Hello, world!", 0.4 -> "Howdy, universe!"),
    Select(0.2 -> "Hello, world!", 0.8 -> "Oh no, not again!"))
  val sunnyTomorrow = If (sunnyToday, Flip(0.8), Flip(0.05))
  val greetingTomorrow = If (sunnyTomorrow,
    Select(0.6 -> "Hello, world!", 0.4 -> "Howdy, universal!"),
    Select(0.2 -> "Hello, world!", 0.8 -> "Oh no, not again!"))

  //Predict today's greeting using an inference algorithm
  def predict(): Unit ={
    val result = VariableElimination.probability(greetingToday, "Hello, world!")
    println("Today's greeting is \"Hello, world!\" " + "with probability " + result + ".")
  }

  //Use an inference algorithm to infer today's weather, given the observation that today's greeting is "Hello, world!"
  def infer(): Unit ={
    greetingToday.observe("Hello, world!")
    val result1 = VariableElimination.probability(sunnyToday, true)
    println("If today's  greeting is \"Hello, world!\", today's " + "weather is sunny with probability " + result1 + ".")
  }

  //Learn from observing that today's greeting is "Hello, world" to predict tomorrow's greeting using an inference algorithm
  def learnAndPredict(): Unit = {
    greetingToday.observe("Hello, world!")
    val result2=VariableElimination.probability(greetingTomorrow, "Hello, world!")
    println("If today's greeting is \"Hello, world!\", " + "tomorrow's greeting will be \"Hello, world!\" " + "with probability " + result2 + ".")
  }

  //Main method that performs all the tasks
  def main(args: Array[String]): Unit = {
    predict()
    infer()
    learnAndPredict()
  }
}
