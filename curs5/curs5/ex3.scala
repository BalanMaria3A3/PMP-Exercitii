package curs5

import com.cra.figaro.language.{Select, Constant, Flip, Chain}	
import com.cra.figaro.library.compound.{CPD}
import com.cra.figaro.algorithm.factored.VariableElimination

object Ex3 {
    // fig. 5.7, 5.8
    val material = Select(0.7 -> 'oil, 0.3 -> 'acrylic)
    val subject = Select(0.8 -> 'people, 0.2 -> 'landscape)
    val size = CPD(subject,
                    'people    -> Select(0.25 -> 'small, 0.25 -> 'medium, 0.5 -> 'large),
                    'landscape -> Select(0.25 -> 'small, 0.5 -> 'medium, 0.25 -> 'large)
                  )
    val brightness = CPD(subject, material,
                         ('people, 'oil)   -> Select(0.8 -> 'dark, 0.2 -> 'bright),
                         ('people, 'acrylic)   -> Select(0.4 -> 'dark, 0.6 -> 'bright),
                         ('landscape, 'oil) -> Select(0.3 -> 'dark, 0.7 -> 'bright),
                         ('landscape, 'acrylic) -> Select(0.65 -> 'dark, 0.35 -> 'bright)
                        )

    def main(args: Array[String]) {	
        // fig. 5.7
        // Subject and Material are independent when nothing is observed
        /*
        material.observe('oil)
        println("Probab. for subject = people knowing material = oil: " + 
              VariableElimination.probability(subject, 'people))
        material.unobserve()
        subject.observe('landscape)
        println("Probab. for material = oil knowing subject = landscape: " + 
              VariableElimination.probability(material, 'oil))
        subject.unobserve()
        // Subject and Material aren’t conditionally independent, given Brightness
        brightness.observe('dark)
        material.observe('oil)
        println("Probab. for subject = people knowing material = oil and brightness = dark: " + 
              VariableElimination.probability(subject, 'people))
        material.unobserve()
        subject.observe('landscape)
        println("Probab. for material = oil knowing subject = landscape and brightness = dark: " + 
              VariableElimination.probability(material, 'oil))
        subject.unobserve()

        println("Probab. for size = medium knowing brightness = dark: " + 
              VariableElimination.probability(size, 'medium))
        brightness.unobserve()
        // fig. 5.8
        // knowing nothing
        // size does not depend on material
        println("Probab. for size = medium: " + 
              VariableElimination.probability(size, 'medium))
        material.observe('oil)
        println("Probab. for size = medium knowing material = oil: " + 
              VariableElimination.probability(size, 'medium))
        material.unobserve()
        // material does depend on size
        println("Probab. for material = oil: " + 
              VariableElimination.probability(material, 'oil))
        size.observe('medium)
        println("Probab. for material = oil knowing size = medium: " + 
              VariableElimination.probability(material, 'oil))
        size.unobserve()
        
        // observing subject
        subject.observe('landscape)
        // size does not depend on material
        println("Probab. for size = medium knowing subject = landscape: " + 
              VariableElimination.probability(size, 'medium))
        material.observe('oil)
        println("Probab. for size = medium knowing subject = landscape and material = oil: " + 
              VariableElimination.probability(size, 'medium))
        material.unobserve()
        // material does not depend on size
        println("Probab. for material = oil knowing subject = landscape: " + 
              VariableElimination.probability(material, 'oil))
        size.observe('medium)
        println("Probab. for material = oil knowing subject = landscape and size = medium: " + 
              VariableElimination.probability(material, 'oil))
        size.unobserve()
        subject.unobserve()
*/
        // observing brightness
        brightness.observe('dark)
        // size does depend on material
        println("Probab. for size = medium knowing brightness = dark: " + 
              VariableElimination.probability(size, 'medium))
        material.observe('oil)
        println("Probab. for size = medium knowing brightness = dark and material = oil: " + 
              VariableElimination.probability(size, 'medium))
        material.unobserve()
        // material does depend on size
        println("Probab. for material = oil knowing  brightness = dark: " + 
              VariableElimination.probability(material, 'oil))
        size.observe('medium)
        println("Probab. for material = oil knowing brightness = dark and size = medium: " + 
              VariableElimination.probability(material, 'oil))
        size.unobserve()
        subject.unobserve()



    }
}
