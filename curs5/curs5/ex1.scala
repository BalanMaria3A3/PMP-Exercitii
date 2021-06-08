package curs5

import com.cra.figaro.language.{Select, Constant, Flip, Chain}	
import com.cra.figaro.library.compound.{If, CPD, RichCPD, OneOf, *}
import com.cra.figaro.algorithm.factored.VariableElimination

object Ex1 {
    val printerPowerButtonOn = Flip(0.95)
    val printerState1 =
        Chain(printerPowerButtonOn,
            (on: Boolean) =>
            if (on) Select(0.2 -> 'down, 0.8 -> 'up)
            else Constant('down))
    val printerState2 =
        If(printerPowerButtonOn,
            Select(0.2 -> 'down, 0.8 -> 'up),
            Constant('down))
    val printerState3 =
        CPD(printerPowerButtonOn,
            false -> Constant('down),
            true -> Select(0.2 -> 'down, 0.8 -> 'up))
    val printerState4 =
        RichCPD(printerPowerButtonOn,
            OneOf(false) -> Constant('down),
            * -> Select(0.2 -> 'down, 0.8 -> 'up))
    def main(args: Array[String]) {	
      // Chain
      println("Probab. for printer state up " + 
      VariableElimination.probability(printerState1, 'up))
      // If
      println("Probab. for printer state up " + 
      VariableElimination.probability(printerState2, 'up))
      // CPD
      println("Probab. for printer state up " + 
      VariableElimination.probability(printerState3, 'up))
      // RichCPD
      println("Probab. for printer state up " + 
      VariableElimination.probability(printerState4, 'up))
    }
}
