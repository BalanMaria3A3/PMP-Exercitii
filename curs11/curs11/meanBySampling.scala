package curs11

import com.cra.figaro.language._
import com.cra.figaro.library.atomic.discrete.Binomial
import com.cra.figaro.library.atomic.continuous.{Normal, Beta, Uniform}
import com.cra.figaro.algorithm.factored._

object MeanBySampling {

    val f = Binomial(30, Beta(2,5))

    val n = Normal(2.0, 0.5)

    var u = Uniform(0.0, 1.0)

    def main(args: Array[String]) {
        val sampleNo = 1000
        var sn = 0.0;
        for {i <- 0 until sampleNo} {
            n.generate()
            sn = sn + n.value
            print(n.value + "; ")
        }
        println();
        println("Approx mean for normal: " + sn/sampleNo)
    
        var sf = 0.0;
        for {i <- 0 until sampleNo} {
            f.generate()
            sf = sf + f.value
            print(f.value + "; ")
        }
        println();
        println("Approx mean for Binomial(30, Beta(2,5)): " + sf/sampleNo)

        var su = 0.0;
        for {i <- 0 until sampleNo} {
            u.generate()
            su = su + u.value
            print(u.value + "; ")
        }
        println();
        println("Approx mean for Uniform(0.0, 1.0): " + su/sampleNo)
    }
}