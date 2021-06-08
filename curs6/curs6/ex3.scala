package curs6

import com.cra.figaro.library.atomic.continuous.{Uniform}
import com.cra.figaro.algorithm.sampling.Importance

object Ex3 {
    def main(args: Array[String]) {
        val fairProb = Uniform(0.0, 1.0)
        
        val algorithm = Importance(fairProb)
        algorithm.start()
        Thread.sleep(2000)
        algorithm.stop()
        println("Probab for x in [0.2, 0.3] = " + 
                algorithm.probability(fairProb, 
                    (x: Double) => 0.2 <= x && x <= 0.3))
        println("Probab for x in [0.7, 0.8] = " + 
                algorithm.probability(fairProb, 
                    (x: Double) => 0.7 <= x && x <= 0.8))
        println("Probab for x in [2, 3] = " + 
                algorithm.probability(fairProb, 
                    (x: Double) => 2 <= x && x <= 3))
        algorithm.kill()
    }
}