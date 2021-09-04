package lectures.part1basics

object sumDigitsTailRecursion extends App {

  def sumDigitsTailRecursion(n:Int, sumDigits:Int = 0): Int = {
   
    if(n == 0) return sumDigits
    else return (sumDigitsTailRecursion((n/10).toInt,sumDigits+(n%10).toInt))
  }

  println(sumDigitsTailRecursion(1357))

}

