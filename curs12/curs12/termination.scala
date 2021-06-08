package curs12

import com.cra.figaro.language._

object Term {

  def main(args: Array[String]) {
    var myCoin = Flip(2.0/4.0);
    myCoin.generate();
    var b = myCoin.value;
    var i = 1;
    while (b) {
        myCoin.generate();
        b = myCoin.value;
        i = i+1;
    }
    println("Terminated after " + i + "steps.");
  }
}