package curs07

import com.cra.figaro.util.memo 
import com.cra.figaro.language.Flip
import com.cra.figaro.library.atomic.discrete.Uniform
import com.cra.figaro.algorithm.factored.VariableElimination
import com.cra.figaro.language.ElementCollection
import com.cra.figaro.library.compound.{^^, If}

object Ex7 {
    class Topic() extends ElementCollection {
        val hot = Flip(0.1)("hot", this)
    }

    val sports = new Topic()
    val politics = new Topic()

    class Person() {
        val interest = Uniform(sports, politics)
    }

    class Post(val poster: Person) extends ElementCollection {
        val topic = If(Flip(0.9), poster.interest, Uniform(sports, politics))("topic", this)
    }

    val john = new Person()
    val peter = new Person()

    val post1 = new Post(john)
    val post2 = new Post(peter)

    def main(args: Array[String]) {
        val isHot1 = post1.get[Boolean]("topic.hot")
        val isHot2 = post2.get[Boolean]("topic.hot")
        println("john post hot: " + VariableElimination.probability(isHot1, true))
        println("peter post hot: " + VariableElimination.probability(isHot2, true))
        
        post1.topic.observe(politics)
        post2.topic.observe(sports)
        peter.interest.observe(politics) // it does not change
        println("john post hot after: " + VariableElimination.probability(isHot1, true))
        println("peter post hot after: " + VariableElimination.probability(isHot2, true))
        

        val eq = post1.topic === post1.get[Topic]("topic")
        println("eq: " + VariableElimination.probability(eq, true))
    }
    
}