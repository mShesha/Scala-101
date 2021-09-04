package lectures.part1basics

object GCD extends App{

  def gcd(num1: Int, num2: Int): Int ={
    if(num2==0) return num1
    else return gcd(num2.abs, num1.abs%num2.abs)
  }

  println(gcd(-40,4))

}
