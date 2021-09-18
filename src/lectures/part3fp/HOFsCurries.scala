package lectures.part3fp

object HOFsCurries extends App {
  /*
  HOF : functions which takes other functions as parameters and can return functions as return types.
   */

  def nTimes(f: Int => Int, n: Int, ele: Int): Int = {
    if (n <= 0) ele
    else nTimes(f, n - 1, f(ele))
  }

  def plusOne = (ele: Int) => ele + 1

  println(nTimes(plusOne, 10, 1))

  /*
  better way to do the same is to return a function instead of an Int. and then use that function as any other function call
   */

  def nTimesBetter(f: Int => Int, n: Int): (Int => Int) = {
    if (n <= 0) (x: Int) => x
    else (x: Int) => nTimesBetter(f, n - 1)(f(x)) // this is a curried function
  }

  val plusTen = nTimesBetter(plusOne, 10)
  println(plusTen(2))

  /*
  curried functions : by-product of the need to dealing with higher order functions.
  functions with multiple parameter lists
   */

  def curriedFormatter(f: Double)(c: String): String = c.format(f)

  // def standardFormatter: Double => String = curriedFormatter("%4.2f")

  // def preciseFormatter: Double => String = curriedFormatter(("%10.2f"))

  /*
  OR
   */

  def piFormat: String => String = curriedFormatter(Math.PI)

  def eFormat: String => String = curriedFormatter(Math.E)

  println(piFormat("%10.8f"))
  println((eFormat("%10.8f")))

  // println("%4.2f".format(Math.PI))
}
