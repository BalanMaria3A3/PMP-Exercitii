import com.cra.figaro.algorithm.OneTimeMPE
import com.cra.figaro.algorithm.factored.MPEVariableElimination
import com.cra.figaro.algorithm.factored.beliefpropagation.MPEBeliefPropagation
import com.cra.figaro.algorithm.sampling.{MetropolisHastingsAnnealer, Schedule, _}
import com.cra.figaro.language.Universe.universe
import com.cra.figaro.language.{Flip, _}
import com.cra.figaro.library.compound.If

abstract class State {
  val confident: Element[Boolean]
  def possession: Element[Boolean] =
    If(confident, Flip(0.7), Flip(0.3))
}

class InitialState() extends State {
  val confident = Flip(0.4)
}

class NextState(current: State) extends State {
  val confident = If(current.confident, Flip(0.6), Flip(0.3))
}
// produce a state sequence in reverse order of the given length

def stateSequence(n: Int): List[State] = {
  if (n == 0)
    List(new InitialState())
  else {
    val last :: rest = stateSequence(n - 1)//add la inceputul listei un element ::
    new NextState(last) :: last :: rest
  }
}
// unroll the hmm and measure the amount of time to infer the last hidden state

// def timing(obsSeq: List[Boolean]): Double = {
// Universe.createNew() // ensures that each experiment is separate
// val stateSeq = stateSequence(obsSeq.length)
// for { i <- 0 until obsSeq.length } {
// stateSeq(i).possession.observe(obsSeq(obsSeq.length - 1 - i))
// }
// val alg = VariableElimination(stateSeq(0).confident)
// val time0 = System.currentTimeMillis()
// alg.start()
// val time1 = System.currentTimeMillis()
// (time1 - time0) / 1000.0 // inference time in seconds
// }


def run(algorithm: OneTimeMPE) {
  val obsSeq: List[Boolean] = List.fill(10)(scala.util.Random.nextBoolean())
  val stateSeq = stateSequence(obsSeq.length)


  for {i <- obsSeq.indices} {
    //facem observatiile
    stateSeq(i).possession.observe(obsSeq(obsSeq.length - 1 - i))
    print("\t")
  }

  //pornim algoritmul , DUPA ce am facut observatiile
  algorithm.start()
  for {j <- obsSeq.indices} {
    println()
    //aplicam algortimul pentru fiecare stare din secventa
    //si afisam in paralel starea si observatia facuta
    print("Starea: ")
    print(algorithm.mostLikelyValue(stateSeq(j).confident))
    println()
    print("Observatia: ")
    print(obsSeq(j))
    // 0.7 prob sa fie mereu identice si 0.3 probabilitatea sa nu fie identice

    println()

  }

}

println("MPE variable elimination")
run(MPEVariableElimination())
println("MPE belief propagation")
run(MPEBeliefPropagation(10))
println("Simulated annealing")
run(MetropolisHastingsAnnealer(100000, ProposalScheme.default,Schedule.default(1.0)))