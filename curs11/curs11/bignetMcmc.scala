package curs11

import com.cra.figaro.language._
import com.cra.figaro.library.compound.{If, RichCPD, *, OneOf}
import com.cra.figaro.algorithm.factored.VariableElimination
import com.cra.figaro.algorithm.factored.beliefpropagation._
import com.cra.figaro.algorithm.sampling.{MetropolisHastings, ProposalScheme}

object BigNetMcmc {
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
        val algorithm1 = MetropolisHastings(1000, ProposalScheme.default, elem(5)(i))
        algorithm1.start()
        println("MetropolisHastings(10000): " + 
            algorithm1.probability(elem(5)(i), true)) 
        algorithm1.kill()

        val algorithm2 = MetropolisHastings(ProposalScheme.default, elem(5)(i)) 
        algorithm2.start()
        Thread.sleep(10000)
        println("MetropolisHastings after sleep 10000: " + 
            algorithm2.probability(elem(5)(i), true))
        algorithm2.kill()
        
        val algorithm3 = MetropolisHastings(1000, ProposalScheme.default, 100, elem(5)(i))
        algorithm3.start()
        println("MetropolisHastings(10000) with burning 1000: " + 
            algorithm3.probability(elem(5)(i), true)) 
        algorithm3.kill()

        val algorithm4 = MetropolisHastings(ProposalScheme.default, 1000, 3, elem(5)(i)) 
        algorithm4.start()
        Thread.sleep(10000)
        println("MetropolisHastings after sleep 10000 with burning 1000 and step 3: " + 
            algorithm4.probability(elem(5)(i), true))
        algorithm4.kill()
        
      }
  }
}