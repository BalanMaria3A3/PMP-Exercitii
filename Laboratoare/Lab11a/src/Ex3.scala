import com.cra.figaro.algorithm.factored.VariableElimination
import com.cra.figaro.algorithm.factored.beliefpropagation.BeliefPropagation
import com.cra.figaro.algorithm.sampling.Importance
import com.cra.figaro.language.Flip
import com.cra.figaro.library.compound.If

//Capitolul 9 - Pag 311/282 - Ex 3

//Assume that 1 in 40 million US citizens become president of the United States.

//Rezolvarea facuta de profa
object Ex3 {
  def main(args: Array[String]): Unit ={
    val isPresident = Flip(1.0/40000000.0) //probabilitatea de a deveni presedinte

    val isLeftHandedPresident=Flip(1.0/2.0) //50% dintre presedintii USA sunt stangaci
    val isLeftHandedGeneral=Flip(1.0/10.0) //10% din populatia USA este stangace

    val isLeftHanded = If(isPresident, isLeftHandedPresident, isLeftHandedGeneral)
    //a)What is the probability someone became the president of
    //the United States, given that he or she is left-handed?

    isLeftHanded.observe(true)
    println('a')
    println(VariableElimination.probability(isPresident, true))
    println(BeliefPropagation.probability(isPresident, true))
    println(Importance.probability(isPresident, true))
    println()
    isLeftHanded.unobserve()

    //b)Now assume that 15% of US presidents went to Harvard, compared to 1 in
    //2,000 for the general population. What is the probability that someone became
    //the president of the United States, given that he or she went to Harvard?

    val wentToHarvardPresident=Flip(3.0/20.0) //15% dintre presedinti au mers la universitatea Harvard
    val wentToHavardGeneral=Flip(1.0/2000.0) //1 din 2000 dintre americani merg la Harvard

    val wentHarvard=If(isPresident, wentToHarvardPresident, wentToHavardGeneral)

    wentHarvard.observe(true)
    println('b')
    println(VariableElimination.probability(isPresident, true))
    println(BeliefPropagation.probability(isPresident, true))
    println(Importance.probability(isPresident, true))
    println()

    //c)Assuming left-handedness and going to Harvard are conditionally independent, given whether someone became president, what’s the probability that
    //someone became the president of the United States, given that he or she is
    //left-handed and went to Harvard?

    isLeftHanded.observe(true)
    println(VariableElimination.probability(isPresident, true))
    println(BeliefPropagation.probability(isPresident, true))
    println(Importance.probability(isPresident, true))
  }
}
