import com.cra.figaro.algorithm.factored.VariableElimination
import com.cra.figaro.language.{Constant, Flip}
import com.cra.figaro.library.compound.CPD

object Ex1 {
  def main(args: Array[String]): Unit ={
    val febra=Flip(0.06) //probabilitatea sa faca febra este de 0.06
    val tuse = Flip(0.04) //probabilitatea sa aiba tuse
    val covid = CPD(febra, tuse,
      (true, true) -> Constant(true),
      (true, false) -> Flip (0.9),
      (false, true) -> Flip(0.5),
      (false, false) -> Flip(0.1))

    covid.observe(true) //am covid
    val alg=VariableElimination(febra, tuse)
    alg.start()
    alg.stop()
    println("prob ptr febra sau tuse: " + alg.probability(febra, true)) //probabilitatea sa am febra, daca am covid
    println("prob ptr febra sau tuse: " + alg.probability(tuse, true)) //probabilitatea sa am tuse daca am covid

    covid.unobserve()

    febra.observe(false)
    tuse.observe(false)
    val alg1=VariableElimination(covid)
    alg1.start()
    alg1.stop()
    println("prob ptr febra sau tuse: " + alg1.probability(covid, true))
  }
}
