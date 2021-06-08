package curs3

	
import com.cra.figaro.algorithm.sampling.Importance
import com.cra.figaro.library.atomic.continuous.Uniform

object Ex5 {
    def main(args: Array[String]) {	
      val temperature = Uniform(10, 70)
      def greaterThan50(d: Double) = d >= 50
      // step 1
      println(Importance.probability(temperature, greaterThan50 _))
      // step 2
      temperature.generate()
      println("A generated value: " + temperature.value)
      // run it several times
    }
}