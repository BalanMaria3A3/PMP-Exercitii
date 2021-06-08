import com.cra.figaro.algorithm.factored.VariableElimination
import com.cra.figaro.language.Apply
import com.cra.figaro.library.atomic.discrete.FromRange

//In the following exercises, you’ll find the FromRange element useful. FromRange
//takes two integers m and n and produces a random integer from m to n – 1.
//For example, FromRange(0, 3) produces 0, 1, or 2 with equal probability. Write
//a Figaro program to compute the probability of rolling a total of 11 when you
//roll two fair six-sided dice

object Ex4 {
  def main(args: Array[String]) {
    val nr1 = FromRange(1, 7) //alege o valoare random intre 1 si 6
    val nr2 = FromRange(1, 7)
    val nr3 = FromRange(1, 7)
    val total = Apply(nr1, nr2, nr3, (i1: Int, i2: Int, i3: Int) => i1 + i2 + i3) //calculez suma celor 3 variabile
    println(VariableElimination.probability(total, 11))
  }
}
