/*
Expr ::= Number(Int) | Sum(Expr, Expr) 
Number(12)
Sum(Number(23), Number(67))
Sum(Sum((Number(23), 67), Number(12))
*/
abstract class Expr
case class Number(n: Int) extends Expr
case class Sum(e1: Expr, e2: Expr) extends Expr

def eval(e: Expr): Int = e match {
    case Number(n) => n
    case Sum(l, r) => eval(l) + eval(r)
}