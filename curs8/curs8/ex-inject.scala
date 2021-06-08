package curs8

import com.cra.figaro.language._
import com.cra.figaro.algorithm.factored.VariableElimination

object ExInject {
    val l = List(0, 1, 2)
    val list1: List[Element[Int]] =
        for { i <- l }
            yield Select(1.0/(i+1) -> 5, 
                         1.0/(i+3) -> 10, 
                         1.0/(i+7) -> 15)
    val list2: Element[List[Int]] = Inject(list1:_*)
    def main(args: Array[String]) {
        println("list1")
        for { i <- 0 until l.length }
        {
            println("i: " + i)
            println(VariableElimination.probability(list1(i), 5))
            println(VariableElimination.probability(list1(i), 10))
            println(VariableElimination.probability(list1(i), 15))
        }
        println("list2")
        println(VariableElimination.probability(list2, List(5,5,5)))
    }
}
