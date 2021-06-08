package curs07

import com.cra.figaro.language.{Flip, Apply}
import com.cra.figaro.library.atomic.continuous.Beta
import com.cra.figaro.algorithm.factored.VariableElimination
import com.cra.figaro.algorithm.sampling.Importance

object Ex2 {
    class Person(alpha: Int, beta: Int) {
        val bias = Beta(alpha,beta)
        val interest = Apply(Flip(bias), (b: Boolean) => if (b) "sport" else "politics")
        
        def learn(tr: Array[String]) {
            val pers = Array.fill(tr.length)(interest)
            for { i <- 0 until tr.length} {
                pers(i).observe(tr(i))
            }
        }
        
    }
    val john = new Person(3, 5)
    val peter = new Person(6,7)
    
    
    def main(args: Array[String]) {

        // john.learn(args)

        println("john: " + VariableElimination.probability(john.interest, "sport"))
        println("peter: " + VariableElimination.probability(peter.interest, "sport"))
        
        val algorithm = Importance(john.bias, peter.bias)
        algorithm.start()
        Thread.sleep(1000)
        algorithm.stop()
        println("john.bias mean: " + algorithm.mean(john.bias))
        println("peter.bias mean: " + algorithm.mean(peter.bias))
        
        
        john.learn(args)
        algorithm.resume()
        Thread.sleep(1000)
        algorithm.stop()
        println("john.bias mean after: " + algorithm.mean(john.bias))
        println("peter.bias mean after: " + algorithm.mean(peter.bias))
        
        algorithm.kill()
    }
    
}