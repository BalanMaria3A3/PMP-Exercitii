import com.cra.figaro.algorithm.factored.VariableElimination
import com.cra.figaro.language.{Chain, Select}
import com.cra.figaro.library.atomic.discrete.FromRange
import com.cra.figaro.library.compound.^^
//Now modify the game from exercise 7 so that the spinner has a tendency to get
//stuck and land on the same outcome two turns in a row. Using similar logic to
//makeStreaky, encode a constraint that two adjacent spins are more likely to have
//the same value than different values. You play the game twice in a row.
//a Compute the probability that you rolled a 7 on the second roll.
//b Compute the probability that you rolled a 7 on the second roll given that you
//rolled a 7 on the first roll


object Ex8 {
  def main(args: Array[String]): Unit ={
    val numberOfSides1= Select(0.2 -> 4, 0.2 -> 6, 0.2 -> 8, 0.2 -> 12, 0.2 -> 20)
    val rol1=Chain(numberOfSides1, ((i: Int) => FromRange(1, i+1)))

    val numberOfSides2= Select(0.2 -> 4, 0.2 -> 6, 0.2 -> 8, 0.2 -> 12, 0.2 -> 20)
    val rol2=Chain(numberOfSides1, ((i: Int) => FromRange(1, i+1)))

    def sticknessConstraint(sidesPair: (Int, Int)) = if (sidesPair._1 == sidesPair._2) 1.0 else 0.5

    val pairOfSides = ^^(numberOfSides1, numberOfSides2)

    //a
    pairOfSides.addConstraint(sticknessConstraint)
    println(VariableElimination.probability(rol2, 7))

    //b
    rol1.observe(7)
    println(VariableElimination.probability(rol2, 7))
  }
}
