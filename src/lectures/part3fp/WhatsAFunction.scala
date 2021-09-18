package lectures.part3fp

object WhatsAFunction extends App {
  /*
  use and work with functions as first class elements.
  work with functions as plain values.

  problem : OO world. everything is an object. to simulate functional programming is to use classes and instances of those classes.
   */

  val doubler = new MyFunction[Int, Int] {
    override def apply(element: Int): Int = element * 2
  }

  println(doubler(2))

  // Function Types = Function1[A,B]

  val stringToIntConverter = new ((String) => Int) {
    override def apply(ele: String): Int = ele.toInt
  }
  println(stringToIntConverter("3") + 4) // this is how we convert objects to look like a function.
  // scala supports this functions up to 22 parameters

  // ALL SCALA FUNCTIONS ARE OBJECTS OR INSTANCES OF CLASSES DERIVING FROM CLASSES FUNCTION1, FUNCTION2 .. FUNCTION22 ETC.


  def stringConcat: (String, String) => String = (v1: String, v2: String) => v1 + v2

  println(stringConcat("hello, ", "Scala"))

  val superAdder: Function1[Int, Function1[Int, Int]] = new Function1[Int, Function1[Int, Int]] {
    override def apply(x: Int): Int => Int = new Function1[Int, Int] {
      override def apply(y: Int): Int = x + y
    }
  }

  val adder = superAdder(3)
  println(adder(4))
  println(superAdder(3)(4)) // curried function :

}

trait MyFunction[A, B] {
  def apply(element: A): B
}


