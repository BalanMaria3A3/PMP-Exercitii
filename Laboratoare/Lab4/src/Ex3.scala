import com.cra.figaro.algorithm.factored.VariableElimination
import com.cra.figaro.language.Flip

//In Figaro, you can use the code x === z as shorthand for
//Apply(x, z, (b1: Boolean, b2: Boolean) => b1 === b2
//In other words, it produces an element whose value is true if the values of its
//two arguments are equal. Without running Figaro, try to guess what the following two programs will produce:

object Ex3 {
  def main(args: Array[String]): Unit ={
    val x1 = Flip(0.4)
    val y1 = Flip(0.4)
    val z1 = x1
    val w1 = x1 === z1
    println(VariableElimination.probability(w1, true))

    val x = Flip(0.4)
    val y = Flip(0.4)
    val z = y
    val w = x === z
    println(VariableElimination.probability(w, true))
  }
}
