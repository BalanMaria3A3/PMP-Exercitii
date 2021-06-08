/*
List[Elt] ::= Nil | Elt :: List[Elt]
*/


def scaleList(xs: List[Int], factor: Int): List[Int] = xs match {
    case Nil => xs
    case x :: xs1 => x * factor :: scaleList(xs1, factor)
}

def scaleList2(xs: List[Int], factor: Int) =
    xs map (x => x * factor)

