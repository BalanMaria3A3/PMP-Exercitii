package curs3

import com.cra.figaro.algorithm.sampling.Importance
import com.cra.figaro.language.{Flip, Select, Dist}
import com.cra.figaro.library.atomic.continuous.Normal
import com.cra.figaro.library.compound._

object Ex8 {
    def main(args: Array[String]) {	
      val tempMean = Normal(40, 9)
      val temperature1 = Normal(tempMean, 100)
      println("Probab. of temperature1 = " +
         Importance.probability(temperature1, (d: Double) => d > 50))
      val tempVariance = Select(0.5 -> 80.0, 0.5 -> 105.0)
      val temperature2 = Normal(tempMean, tempVariance)
      println("Probab. of temperature2 = " +
         Importance.probability(temperature2, (d: Double) => d > 50))
    }
}