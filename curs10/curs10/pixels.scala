package curs10

import com.cra.figaro.language._
import com.cra.figaro.library.compound._
import com.cra.figaro.algorithm.factored._
import com.cra.figaro.algorithm.factored.beliefpropagation._

object Pixels {
  val pixels = Array.fill(10, 10)(Flip(0.4))
  def setConstraint(i1: Int, j1: Int, i2: Int, j2: Int) {
    val pixel1 = pixels(i1)(j1)
    val pixel2 = pixels(i2)(j2)
    val pair = ^^(pixel1, pixel2)
    pair.addConstraint(bb => if (bb._1 == bb._2) 0.9; else 0.1)
  }
  for {
    i <- 0 until 10
    j <- 0 until 10
  } {
    if (i <= 8) setConstraint(i, j, i+1, j)
    if (j <= 8) setConstraint(i, j, i, j+1)
  }


  def main(args: Array[String]) {
  
    val algorithm1 = VariableElimination(pixels(7)(8)) 
    pixels(6)(8).observe(false)
    algorithm1.start()
    println(algorithm1.probability(pixels(7)(8), true))
    
    val algorithm = BeliefPropagation(1000, pixels(7)(8))
    algorithm.start()
    print(algorithm.probability(pixels(7)(8), true))
    
  }
}
