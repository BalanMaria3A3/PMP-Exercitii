package curs4

import com.cra.figaro.language.{Flip, Select}	
import com.cra.figaro.library.compound.{CPD}
import com.cra.figaro.algorithm.factored.VariableElimination

object Ex1 {
    val rembrandt = Flip(0.1)
    val subject = Select(0.8 -> 'people, 0.2 -> 'landscape)
    val size = CPD(subject,
                    'people    -> Select(0.25 -> 'small, 0.25 -> 'medium, 0.5 -> 'large),
                    'landscape -> Select(0.25 -> 'small, 0.5 -> 'medium, 0.25 -> 'large)
                  )
    val brightness = CPD(subject,
                         'people    -> Select(0.8 -> 'dark, 0.2 -> 'bright),
                         'landscape -> Select(0.3 -> 'dark, 0.7 -> 'bright)
                        )
                        
    val price = CPD(rembrandt, subject,
                    (false, 'people) -> Flip(0.2),
                    (false, 'landscape) -> Flip(0.1),
                    (true, 'people) -> Flip(0.9),
                    (true, 'landscape) -> Flip(0.4)
                   )
                   
    def main(args: Array[String]) {
        println("Probab. for brightness dark: " + VariableElimination.probability(brightness, 'dark))
        /*
        val algorithm = VariableElimination(brightness, size)
        algorithm.start()
        println("Probab. for brightness dark and size medium: " + VariableElimination.probability(brightness, 'dark) + "; "
                                                                + VariableElimination.probability(size, 'medium))
        brightness.observe('bright)
        brightness.unobserve()   // comment / uncomment
        subject.observe('landscape)   // comment / uncomment
        println("Probab. for brightness dark and size medium: " + VariableElimination.probability(brightness, 'dark) + "; "
                                                                + VariableElimination.probability(size, 'medium))
    */
    }
}
