package curs11

import com.cra.figaro.language._
import com.cra.figaro.library.atomic.continuous.{Normal, Uniform}

object ImpSimplified {

    val sampleNo = 10000
    def main(args: Array[String]) {
        val u = Uniform(0.0, 1.0)
        val n = Normal(0.5, 1.0)
        var sw = 0.0
        for {i <- 0 until sampleNo-1} {
            u.generate()
            val z = u.value
            val w = n.density(z)/u.density(z)
            sw = sw + w
        }
        println("Importance weigths mean: " + sw/sampleNo)
 
    }
}