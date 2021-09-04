package lectures.part2oop

object Generics extends App {

  class MyList[B]{
    // use the type B later
  }

  val listInt = new MyList[Int]
  val listString = new MyList[String]

  class MyMap[key,value]

  // generic methods

  object MyList{
    def empty[A]: MyList[A] = ???
  }

  val emptyListOfIntegers = MyList.empty[Int]

  // variance problem

  class Animal
  class Cat extends Animal
  class Dog extends Animal

  // If Cats extends Animal. Does List[Cat] extends List[Animal]??
  // 3 possible answers to this
  /*
    1) yes. List[Cat] extends List[Animal] -- Covariance
    class CovariantList[+A]

   */

  

}
