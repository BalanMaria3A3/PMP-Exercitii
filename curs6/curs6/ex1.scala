package curs6

object Ex1 {
  def main(args: Array[String]) {
    
    if (args.length == 0) {
        println("No arguments!")
        return
    } 
    
    val arg0 = args(0)
    val len0 = arg0.length
    for {
      i <- 0 until len0
    } {
      println(i + ": " + arg0(i))
    }
    
    if (args.length > 1) {
        val arg1 = args(1)
        val len1 = arg1.length
        println(arg1)
        for {
            i <- 0 until len1
        } {
            println(i + ": " + arg1(i))
        }
    }
    
  }
}