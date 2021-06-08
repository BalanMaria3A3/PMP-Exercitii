package curs11

import com.cra.figaro.language._
import com.cra.figaro.library.compound.{If, RichCPD, *, OneOf}
import com.cra.figaro.algorithm.factored.VariableElimination
import com.cra.figaro.algorithm.factored.beliefpropagation._
import com.cra.figaro.algorithm.sampling.Importance

object BigNetImp {
  // val length = 10 // it is OK
  val length = 100  // it last a lot
  val level = 10
  val elem: Array[Array[Element[Boolean]]] = 
    Array.tabulate(level, length)((i: Int, j: Int) => Flip((i+1.0)/(i+j+1.0)))
  for { k <- 1 until level } {
    for { i <- 0 until length-(k+1)*5 } {
        elem(k)(i) = 
          RichCPD(elem(k-1)(i), elem(k-1)(i+1), elem(k-1)(i+2), elem(k-1)(i+3), elem(k-1)(i+4),
            (OneOf(true), *, *, *, *) -> Flip(i/(i + 10.0)),
            (*, OneOf(true), *, *, *) -> Flip(i/(1.2*i + 9.0)),
            (*, *, OneOf(true), *, *) -> Flip(i/(1.4*i + 8.0)),
            (*, *, *, OneOf(true), *) -> Flip(i/(1.6*i + 7.0)),
            (*, *, *, *, OneOf(true)) -> Flip(i/(1.8*i + 6.0)),
            (*, *, *, *, *)    -> Flip(i/(2.0*i + 5.0))
        )
    }
  }

  def main(args: Array[String]) {
       //for { i <- 5 until 6} {  // it is OK
    for { i <- 30 until 31} {  
        println("i: " + i)
        //println("VE: " + 
        //  VariableElimination.probability(elem(5)(i), true)) // it last a lot
        /*
        val algorithm = BeliefPropagation(10, elem(5)(i)) // try various values for the number of iterations
        algorithm.start()
        println("BP: " + algorithm.probability(elem(5)(i), true))
        println("BP: " + algorithm.probability(elem(5)(i), false))
        algorithm.kill()
        */
        val algorithm1 = Importance(100, elem(5)(i))
        algorithm1.start()
        println("Importance(100): " + 
            algorithm1.probability(elem(5)(i), true)) 
        algorithm1.kill()

        val algorithm2 = Importance(elem(5)(i)) 
        algorithm2.start()
        Thread.sleep(5000)
        println("Importance after sleep 1000: " + algorithm2.probability(elem(5)(i), true))
        algorithm2.kill()
        
      }
  }
}