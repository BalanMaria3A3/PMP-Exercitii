package curs4

import com.cra.figaro.language.{Flip, Select}	
import com.cra.figaro.library.compound.{CPD}
import com.cra.figaro.algorithm.factored.beliefpropagation.{BeliefPropagation}

object Ex2 {
    val subject = Select(0.8 -> 'people, 0.2 -> 'landscape)
    val size = CPD(subject,
                    'people    -> Select(0.25 -> 'small, 0.25 -> 'medium, 0.5 -> 'large),
                    'landscape -> Select(0.25 -> 'small, 0.5 -> 'medium, 0.25 -> 'large)
                  )
    val brightness = CPD(subject,
                         'people    -> Select(0.8 -> 'dark, 0.2 -> 'bright),
                         'landscape -> Select(0.3 -> 'dark, 0.7 -> 'bright)
                        )
    def main(args: Array[String]) {
        val algorithm1 = BeliefPropagation(1, brightness) 
        algorithm1.start() 
        println(algorithm1.probability(brightness, 'dark))
        algorithm1.stop()

        val algorithm2 = BeliefPropagation(4, brightness)   // comment / uncomment
        algorithm2.start() 
        println(algorithm2.probability(brightness, 'dark))
        algorithm2.stop()

        val algorithm3 = BeliefPropagation(20, brightness)  // try to guess the number of iteratios needed to obtain the exact result
        algorithm3.start() 
        println(algorithm3.probability(brightness, 'dark))
        algorithm3.stop()

        val algorithm4 = BeliefPropagation(brightness)  // comment/uncomment
        algorithm4.start()
        Thread.sleep(1)
        println(algorithm4.probability(brightness, 'dark)) // try to guess the number of iteratios needed to obtain the exact result
        algorithm4.stop()
        
    }
}