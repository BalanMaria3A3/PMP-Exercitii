import com.cra.figaro.algorithm.factored.VariableElimination
import com.cra.figaro.algorithm.factored.beliefpropagation.BeliefPropagation
import com.cra.figaro.algorithm.sampling.Importance
import com.cra.figaro.language.Flip
import com.cra.figaro.library.compound.If


//subpunctul a)
val presedinte=Flip(1.0/40000000.0) // 1 din 40 mil din cetatenii americani devine presedinte
val preseinteStangaci=Flip(0.5) //50% dintre presedintii americii sunt stangaci
val populatie=Flip(39999999.0/40000000.0)
val populatieStangace = Flip(0.1) //10% din populatia generala este stangace
val esteStangaci=If(presedinte, preseinteStangaci, populatieStangace)
esteStangaci.observe(true)
val presStg=VariableElimination.probability(presedinte, true)
print(presStg)
print(BeliefPropagation.probability(presedinte, true))
print(Importance.probability(presedinte, true))
print("\n")

//subpunctul b)
val presedinteHarvard=Flip(0.15) //15% dintre presedintiau mers la Harvard
val populatieHarvard=Flip(1.0/2000.0) //doar 1 din 2000 din populatia generala a mers la Harvard
val deLaHarvard=If(presedinte, presedinteHarvard, populatieHarvard)
deLaHarvard.observe(true)
val presedinteDeLaHarvard= VariableElimination.probability(presedinte, true)
print(presedinteDeLaHarvard)
print(BeliefPropagation.probability(presedinte,true))
print(Importance.probability(presedinte, true))
print("\n")

//subpunctul c)
esteStangaci.observe(true)
deLaHarvard.observe(true)
val ambelePresedinte=VariableElimination.probability(presedinte, true)
print(ambelePresedinte)
print(BeliefPropagation.probability(presedinte, true))
print(Importance.probability(presedinte, true))

/*
Variable Elimination: e cel mai exact si corect. Prezicerea sa se bazeaza pe grupuri de variabile si la fiecare iteratie elimina cate una
Belief Propagation: se aseamana cu Variable Elimination, dar cand vine vorba de rezultate nu este atat de precis, mai exact le aproximeaza
Importance: dureaza cel mai mult, pentru ca in spate el creeaza cate o variabila
*/