package lectures.part3fp

object AnonymousFunctions extends App {

  // this is object oriented way of defining a function as an instance of the class Function1.
  val doublerOOWay = new Function[Int, Int] {
    override def apply(x: Int): Int = x * 2
  }

  // Syntactic sugar way of doing the above is by using anonymous function or the Lambda

  val doublerSSWay = (x: Int) => x * 2

  // lose the data type

  val doublerDTDefined: Int => Int = x => x * 2

  // rules for functions with multiple parameters:

  val adder: (Int, Int) => Int = (a, b) => a + b

  // to work with Lambdas and no parameters

  val noParamLambda: () => Int = () => 3

  // with lambdas, we have to call it with ()
  println(noParamLambda) // prints the instance of the object
  println(noParamLambda()) // prints the output of the function

  // more syntactic sugar

  val niceDoubler: Int => Int = _ + 1 // equivalent to x => x + 1
  val niceAdded: (Int, Int) => Int = _ + _ // equivalent to (a,b) => a + b

  /*
  superAdder from previous file as anonymous function
   */

  val superAdder: Function1[Int, Function1[Int, Int]] = new Function1[Int, Function1[Int, Int]] {
    override def apply(x: Int): Int => Int = new Function1[Int, Int] {
      override def apply(y: Int): Int = x + y
    }
  }

  val supAdder = (x: Int) => (y: Int) => x + y

  val add = superAdder(3)
  println(add(4))
  println(superAdder(3)(4)) // curried function :

  println(supAdder(3)(5))

}
