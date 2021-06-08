import com.cra.figaro.algorithm.factored.VariableElimination
import com.cra.figaro.language.{Chain, Select}
import com.cra.figaro.library.atomic.discrete.FromRange
//Imagine a game where you have a spinner and five dice with different numbers
//of sides. The spinner has five possible outcomes with equal probability: 4, 6, 8,
//12, and 20. In the game, you first spin the spinner. Then you roll a fair die
//whose number of sides is the result of the spinner. Write a Figaro program to
//represent this game.
//a Compute the probability that you rolled a 12-sided die.
//b Compute the probability that you rolled a 7.
//c Compute the probability that you rolled a 12-sided die given that you rolled a 7.
//d Compute the probability that you rolled a 7 given that you rolled a 12-sided die


object Ex7 {
  def main(args: Array[String]): Unit ={
    val numberOfSides = Select(0.2 -> 4, 0.2-> 6, 0.2-> 8, 0.2-> 12, 0.2-> 20)
    val roll=Chain(numberOfSides, ((i: Int) => FromRange(1, i+1)))

    //a

    println(VariableElimination.probability(numberOfSides, 12))

    //b
    println(VariableElimination.probability(roll, 7))

    //c
    roll.observe(7)
    println(VariableElimination.probability(numberOfSides, 12))
    roll.unobserve()

    //d
    numberOfSides.observe(12)
    println(VariableElimination.probability(roll, 7))
  }
}
