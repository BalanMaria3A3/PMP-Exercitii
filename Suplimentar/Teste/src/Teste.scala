import com.cra.figaro.algorithm.sampling.Importance
import com.cra.figaro.experimental.normalproposals.Uniform
import com.cra.figaro.language.{Flip, Select}
import com.cra.figaro.library.atomic.continuous.Normal
import com.cra.figaro.library.atomic.discrete.Binomial

object Teste {
  def main(args: Array[String]): Unit ={
    //avem 7 zile dintr-o saptamana si fiecare zi are un element sunny cu Flip(0.2)
    val numSunnyDaysInWeek = Binomial(7, 0.2) //numarul de zile insorite (sunny) intr-o saptamana
    //println(VariableElimination.probability(numSunnyDaysInWeek,3))

    //sunnyTodayProbability reprezinta probabilitatea incerta ca azi sa fie sunny; orice valoarea intre 0 si 0.5 este egala posibila
    val sunnyTodayProbability = Uniform(0.0, 0.5)
    //sunnyToday este adevarata cu o probabilitate egala cu sunnyTodayProbability
    val sunnyToday=Flip(sunnyTodayProbability)
    //afiseaza probabilitatea ca sunnyToday sa fie true
    println(Importance.probability(sunnyToday, true))

    //Normal- ofera mai multe posibilitati
    //Normal are o anumită varianță cunoscută, reprezentând în același timp incertitudine asupra mediei.
    val tempMean = Normal(40, 9) //media temepraturei este undeva in jur de 40
    val temperature = Normal(tempMean, 100) //temperatura are o variatie de 100
    println(Importance.probability(temperature, (d: Double) => d > 50))

    val tempMean1 = Normal(40, 9)
    val tempVariance1 = Select(0.5 -> 80.0, 0.5 -> 105.0) //variatia este fie 80, fie 105
    val temperature1 = Normal(tempMean1, tempVariance1)
    println(Importance.probability(temperature1, (d: Double) => d > 50))
  }
}
