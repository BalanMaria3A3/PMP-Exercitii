package curs11

import com.cra.figaro.language._
import com.cra.figaro.library.atomic.continuous.{Normal, Uniform}

object McmcSimplified {

    val sampleNo = 100
    def main(args: Array[String]) {
        var x = Array.fill(sampleNo)(1.0)
        val u = Uniform(0.0, 1.0)
        x(0) = 0.5
        for {i <- 0 until sampleNo-1} {
            val xc = Normal.density(2.0, 0.5, 1.0)(x(i))
            u.generate()
            if (u.value < ((1.0).min(Normal.density(2.0, 0.5, 1.0)
                                    (xc)/Normal.density(2.0, 0.5, 1.0)(x(i)))))
                x(i+1) = xc
            else 
                x(i+1) = x(i)
        }
        for {i <- 0 until sampleNo} {
            println(x(i))
        }
    }
}