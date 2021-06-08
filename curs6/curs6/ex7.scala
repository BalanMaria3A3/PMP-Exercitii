package curs6

import com.cra.figaro.library.atomic.discrete.{Binomial}
import com.cra.figaro.library.atomic.continuous.{Beta}
import com.cra.figaro.algorithm.sampling.Importance
import com.cra.figaro.library.collection.VariableSizeArray
import com.cra.figaro.language.Universe

object Ex7 {
    def main(args: Array[String]) {
        Universe.createNew()
        val arrv = VariableSizeArray(Binomial(20, 0.3), i => Beta(1, i + 1))
        val total = arrv.foldLeft(0.0)(_ + _)

        val algorithm = Importance(total)
        algorithm.start()
        Thread.sleep(1000)
        algorithm.stop()
        println("Expected value = " + algorithm.mean(total))
        algorithm.kill()
    }
}