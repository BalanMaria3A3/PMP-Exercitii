import com.cra.figaro.algorithm.sampling.Importance
import com.cra.figaro.language.{Chain, Select}
import com.cra.figaro.library.atomic.continuous
import com.cra.figaro.library.collection.Container
//Carte - Ex 3 - Cap 6 - 227/198

object Ex1 {
  def main(args: Array[String]): Unit = {
    val par = List(3,4,3,5,4,3,5,4,5,3,4,5,4,5,3,4,3,4)
    val skill=continuous.Uniform(0.0, 8.0/13.0)
    val shots = List.tabulate(18)((hole: Int) => Chain(skill, (s: Double) =>
      Select(s/8.0 -> (par(hole)-2),
        s/2.0 -> (par(hole) - 1),
        s -> par(hole),
        4.0 / 5.0 * (1.0 - 13.0 *s /8.0) -> (par(hole) +1),
        1.0/5.0 * (1.0 -13.0 * s /8.0) -> (par(hole) +2))))

    val suma = Container(shots:_*).foldLeft(0)(_ +_)
    def greaterThan80(s: Int) = s >= 80
    def greaterThan03 (s: Double) = s>= 0.3

    var alg = Importance(1000, suma)
    alg.start()
    alg.stop()
    println(alg.probability(suma, (s: Int) => s >=80))

    skill.setCondition((s: Double) => s>= 0.3)
    var alg1 = Importance(1000, suma)
    alg1.start()
    alg1.stop()
    println(alg1.probability(suma, greaterThan80 _))
    skill.removeConditions()

    suma.observe(80)
    var alg2= Importance(1000, skill)
    alg2.start()
    alg2.stop()
    println(alg2.probability(skill, greaterThan03 _))
  }
}
