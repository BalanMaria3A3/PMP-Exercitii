package curs12

import com.cra.figaro.language._

object ProbChStmt {

  def main(args: Array[String]) {
    var x = 99;
    var myCoin = Flip(2.0/4.0);
    myCoin.generate();
    var b = myCoin.value
    if (b) x = x+1; else x = x+5;
    println("x = " + x);
  }
}