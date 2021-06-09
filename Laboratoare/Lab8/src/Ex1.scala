import com.cra.figaro.algorithm.factored.VariableElimination
import com.cra.figaro.language.{Chain, Flip}
import com.cra.figaro.library.compound.^^
//Cap 7 - Pag 257/228 - Ex 1

object Ex1 {

  class ResearchAndDevelopment {
      val state = Flip(0.5)
  }

  class Production(val rd: ResearchAndDevelopment, val hr: HumanResources) {
    val state=Chain(rd.state, hr.state, (rd: Boolean, hr: Boolean) => {
      if (rd && hr) Flip(0.9)
      else if (rd && !hr) Flip(0.5)
      else if (!rd && hr) Flip(0.5)
      else Flip(0.1)})
  }

  class Sales (val p: Production) {
    val state = Chain(p.state, (p:Boolean) => {
      if (p) Flip(0.9)
      else Flip(0.1)
    })
  }

  class HumanResources {
    val state = Flip(0.5)
  }

  class Finance (val hr: HumanResources, val s: Sales) {
    val state=Chain(hr.state, s.state, (hr: Boolean, s: Boolean) => {
      if (hr && s) Flip(0.9)
      else if (hr && !s) Flip(0.5)
      else if(!hr && s) Flip(0.5)
      else Flip(0.1)
    })
  }

  class Firma(val rd: ResearchAndDevelopment, val hr: HumanResources, val p: Production, val s: Sales, val f: Finance) {
    val departments = ^^(rd.state, hr.state, p.state, s.state, f.state)
    val health = Chain(departments, (d: (Boolean, Boolean, Boolean, Boolean, Boolean)) =>{
      val (rd, hr, p, s, f) =d
      if (rd && hr && p && s && f) Flip(0.9)
      else if(p && f && s) Flip(0.8)
      else if (hr && f) Flip(0.5)
      else Flip(0.2)
    })
  }

  def main(args: Array[String]): Unit ={
    val rd = new ResearchAndDevelopment()
    val hr = new HumanResources()
    val p= new Production(rd, hr)
    val s = new Sales(p)
    val f = new Finance(hr, s)
    val firma = new Firma(rd, hr, p, s, f)

    hr.state.observe(true)
    rd.state.observe(true)
    println(VariableElimination.probability(firma.health, true))
  }
}
