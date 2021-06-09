import com.cra.figaro.algorithm.factored.VariableElimination
import com.cra.figaro.algorithm.sampling.{DisjointScheme, Importance, MetropolisHastings, ProposalScheme}
import com.cra.figaro.language.{Apply, Flip, Universe}
import com.cra.figaro.library.atomic.continuous.Normal
import com.cra.figaro.library.compound.If

import scala.collection.mutable.ListBuffer


val x0 = Apply(Normal(0.75, 0.3), (d: Double) => d.max(0).min(1))

val y0 = Apply(Normal(0.4, 0.2), (d: Double) => d.max(0).min(1))
val x = Flip(x0)
val y = Flip(y0)
val z1 = Flip(0.8)
val z2 = Flip(0.2)
val z = If(x === y, z1, z2)
z.observe(false)

val vAnswer = VariableElimination.probability(y, true)
println(vAnswer)

//ex1
val length = 101
var allErrors = new ListBuffer[Int]()
val allErrors = Vector()
val data = for {a<- 1000 until 2100 by 1000} yield //pune until 11000
{
  println(a)
  var totalSquaredError = 0.0
  for { index <- 1 until length } {
    println(index)
    val algorithm = Importance(a, y)
    algorithm.start()
    val importanceAnswer = algorithm.probability(y,true)
    val error = vAnswer - importanceAnswer
    println(error)
    val sqrtError = math.sqrt(error) // iese mereu 0
    totalSquaredError += error * error


  }
  val rmse = math.sqrt(totalSquaredError / 100)
  println(a + " samples: RMSE = " + rmse)
  (a,rmse)
}
//val chart = XYLineChart(data)
//chart.saveAsPNG("F:\\Faculty\\FII\\Anul 3\\Sem 1\\PMP\\Labs\\Lab12\\myplot.png")

//ex2

val veAnswer = VariableElimination.probability(y, true)

for { i <- 10000 to 21000 by 10000 } { // pana la 110000
  var totalSquaredError = 0.0
  for { j <- 1 to 100 } {
    Universe.createNew()
    val x0 = Apply(Normal(0.75, 0.3), (d: Double) => d.max(0).min(1))
    val y0 = Apply(Normal(0.4, 0.2), (d: Double) => d.max(0).min(1))
    val x = Flip(x0)
    val y = Flip(y0)
    val z1 = Flip(0.8)
    val z2 = Flip(0.2)

    val z = If(x === y, z1, z2)

    z.observe(false)
    val mh = MetropolisHastings(i, ProposalScheme.default, y)
    mh.start()
    val mhAnswer = mh.probability(y, true)
    val diff = veAnswer - mhAnswer
    totalSquaredError += diff * diff
  }
  val rmse = math.sqrt(totalSquaredError / 100)
  println(i + " samples: RMSE = " + rmse)
}

//ex3


val veAnswer3 = VariableElimination.probability(y, true)
println(veAnswer3)
val algorithm3 = Importance(1000000, y)
algorithm3.start()
val importanceAnswer3 = algorithm3.probability(y,true)
println("importance ptr 1 000 000 samples: ")
println(importanceAnswer3)

//ex4
println("metropolitam hash de 5 ori: ")
for { i <- 1 until 6 } {

  val mh1 = MetropolisHastings(10000000, ProposalScheme.default, y)
  mh1.start()
  val mhAnswer1 = mh1.probability(y, true)
  println(mhAnswer1)
}

//ex5

val scheme = DisjointScheme(
  0.1 -> (() => ProposalScheme(z1)),
  0.1 -> (() => ProposalScheme(z2)),
  0.8 -> (() => ProposalScheme(x, y))
)

val mh2 = MetropolisHastings(10000, scheme,y)
mh2.start()
println(mh2.probability(y,true))