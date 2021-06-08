import com.cra.figaro.algorithm.factored.VariableElimination
import com.cra.figaro.language.Apply
import com.cra.figaro.library.atomic.discrete.FromRange

//In Monopoly, doubles happen when you roll two fair six-sided dice and both
//dice show the same number. If you roll three doubles in a row, you go to jail.
//Write a Figaro program to compute the probability that this will happen to you
//on any given turn.

object Ex6 {
  def main(args: Array[String]): Unit = {
    val die1=FromRange(1,7)
    val die2=FromRange(1,7)
    val die3=FromRange(1,7)
    val die4=FromRange(1,7)
    val die5=FromRange(1,7)
    val die6=FromRange(1,7)

    val double1 = Apply(die1, die2, (i1: Int, i2: Int) => i1 == i2)
    val double2 = Apply (die3, die4, (i3: Int, i4: Int) => i3==i4)
    val double3= Apply(die5, die6, (i5: Int, i6: Int) => i5 == i6)

    val jail = Apply(double1, double2, double3, (d1: Boolean, d2:Boolean, d3:Boolean) => d1 && d2 && d3)

    val doubles = Apply(die1, die3, die5, (d1: Int, d2: Int, d3:Int) => (d1, d2, d3))
    doubles.addCondition((doubles: (Int, Int, Int)) => doubles._1 != doubles._2 && doubles._2 != doubles._3 && doubles._1 != doubles._3)

    println(VariableElimination.probability(jail, true))
  }
}
