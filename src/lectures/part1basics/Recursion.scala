package lectures.part1basics

object Recursion extends App {

  def aRepeatedTailRecursionFunction(aString: String, n: Int): String = {
    def repeatedTailRecursionHelper(aString: String, n: Int, accumulator: String): String = {
      if (n <= 1) accumulator
      else repeatedTailRecursionHelper(aString, n - 1, aString + " " + accumulator)
    }

    repeatedTailRecursionHelper(aString, n, "")
  }

  println(aRepeatedTailRecursionFunction("Hello", 10))

  def isPrimeTailRecursiveFunction(aNumber: Int): Boolean = {
    def isPrimeTailRecursiveHelper(aNum: Int, isStillPrime: Boolean): Boolean = {
      if (!isStillPrime) false
      else if (aNum <= 1) true
      else isPrimeTailRecursiveHelper(aNum - 1, aNumber % aNum != 0 && isStillPrime)
    }

    isPrimeTailRecursiveHelper(aNumber / 2, true)
  }

  println(isPrimeTailRecursiveFunction(2004))

  def aFibonacciTailRecursion(n: Int): Int = {
    def aFibonacciTailRecursionHelper(i: Int, last: Int, nextToLast: Int): Int = {
      if (i >= n) last
      else aFibonacciTailRecursionHelper(i + 1, last + nextToLast, last)
    }

    if (n <= 2) 1
    else aFibonacciTailRecursionHelper(2, 1, 1)

  }

  println(aFibonacciTailRecursion(8))


}
