import com.cra.figaro.language.{Constant, Flip}
import com.cra.figaro.library.compound.{CPD, If}
import com.cra.figaro.algorithm.factored.VariableElimination
import com.cra.figaro.library.atomic.continuous.Beta
import com.cra.figaro.library.atomic.discrete.Binomial
import com.cra.figaro.algorithm.sampling.Importance

object Alarma {
  def main(args: Array[String]) {
  /*
  În fiecare seară trebuie să îți setezi alarma pentru a doua zi,
  cu scopul de a prinde autobuzul cu care trebuie să ajungi la serviciu.
  Este posibil, cu o rată de 10% să uiți să setezi alarma.
   Modelat cu valori booleene: am uitat să setez alrama ~ true
  */
  val uitatSetatAlarma = Flip(0.1)
  /*
  Dimineața, dacă alarma este setată și sună ceasul, uneori închizi alarma,
  adormi la loc și te trezești prea tarziu.
   Modelat cu valori booleene:   mă trezesc prea tarziu ~ true
  Acest lucru se întâmplă cu o probabilitate de 0.1.
   --> modelat cu Flip(0,1)
   */

  val amAdormit = If(uitatSetatAlarma, Flip(0.1), Constant(false))
  /*
  Însă dacă uiți să setezi alarma, în 9 din 10 cazuri te trezești prea târziu.
    --> modelat cu Flip(0.9)
  Avem dependență între preaTarziu și uitatSetatAlarma, pe care o descriem functional cu If
  */
  val preaTarziu = If(uitatSetatAlarma, Flip(0.9), amAdormit)
  /*
  Autobuzul se poate, de asemenea, să întârzie cu o probabilitate de 20%.
    Autobuzul întârzie ~ true
  */
  val autobuzIntarzie = Flip(0.2)
  /*
  Probabilitatea sa ajungi la timp la serviciu este redata în tabelul următor:
  Avem dependeță între ajungLaTimp și preaTarziu și autobuzIntarzie,
  pe care o descriem cu un CPD:
  */
  val ajungLaTimp = CPD(preaTarziu, autobuzIntarzie,
    (true, true) -> Flip(0.1),
    (true, false) -> Flip(0.3),
    (false, true) -> Flip(0.2),
    (false, false) -> Flip(0.9)
  )

  /*
  Presupunând că  rata de 10% să uiți să setezi alarma a fost incorect previzionată,
  cum ar putea fi învățată?
  */

  val bias = Beta(2,7)
  val uitatSetatAlarmaBiased = Binomial(100, bias)
  val uitatSetatAlarmaLearnt = Flip(bias)


    // 2.a)
    amAdormit.observe(true)
    println("Probabilitatea să ajungi la serviciu la timp, având în vedere ca ai adormit: " +
      VariableElimination.probability(ajungLaTimp, true))
    amAdormit.unobserve()
    // 2.b)
    ajungLaTimp.observe(true)
    println("Probabilitatea să îți fi setat alarma, știind ai ajuns la timp la serviciu :" +
      VariableElimination.probability(uitatSetatAlarma, false))
    ajungLaTimp.unobserve()
    // 2.c)
    println("Probabilitatea să adorm :" +
      VariableElimination.probability(amAdormit, true))
    // 3

    val algorithm = Importance(1000, uitatSetatAlarmaLearnt)
    algorithm.start()
    println("Probabilitatea să uit, învățată :" +
      algorithm.probability(uitatSetatAlarmaLearnt, true))

    // Solutie alternativa pentru 3.
    val tr = Array.fill(args.size)(Flip(bias))
    for { i <- 0 until args.size} {
      tr(i).observe(args(0)(i) == 'T')
    }
    val uitatSetatAlarmaLearnt2 = Flip(bias)
    val algorithm2 = Importance(1000, uitatSetatAlarmaLearnt2)
    algorithm2.start()
    println("Probabilitatea să uit, învățată :" +
      algorithm2.probability(uitatSetatAlarmaLearnt2, true))
  }
}