import com.cra.figaro.algorithm.factored.VariableElimination
import com.cra.figaro.language.Apply
import com.cra.figaro.library.atomic.discrete.FromRange
//Write a Figaro program to compute the probability that the first die is a 6 when
//you roll two fair six-sided dice and the total is greater than 8.

object Ex5 {
  def main(args: Array[String]): Unit ={
    val die1= FromRange(1,7)
    val die2= FromRange(1,7)
    val suma=Apply(die1, die2, (i1: Int, i2: Int) => i1+i2)
    suma.addCondition((suma: Int) => suma > 8)
    println(VariableElimination.probability(die1, 6))
  }
}
