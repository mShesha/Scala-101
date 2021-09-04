package lectures.part1basics

object Functions extends App {

  def aFunction(a: String,b: Int) : String = {
    a + " " + b
  }

  println(aFunction("hello",3))

  def aRepeatedFunction(aString: String, b:Int): String = {
    if(b==1) aString
    else aString + " " +  aRepeatedFunction(aString,b-1)
  }

  println(aRepeatedFunction("Hello",4))

  def aGreeting(name: String, age: Int) = {
    println(s"Hi, my name is $name and I am $age years old.")
  }

  aGreeting("Shourya",6)

  def aFactorial(n: Int) : Int = {
    if (n==1) 1
    else n * aFactorial(n-1)
  }

  println(aFactorial(5))

  def aFibonacci(n: Int) : Int = {
    if (n==1) 1
    else aFibonacci(n-1) + aFibonacci(n-2)
  }
  println(aFibonacci(5))

}
