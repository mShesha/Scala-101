package lectures.part1basics

object DecimalToBinary extends App {

  def decimalToBinary(decimal: Int, binary: String = "") : String = {
    if(decimal==0) return binary.toString
    else return decimalToBinary(decimal/2,(decimal%2).toString+binary.toString)

  }

  println(decimalToBinary(13))

}
