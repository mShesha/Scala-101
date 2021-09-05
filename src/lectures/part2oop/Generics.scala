package lectures.part2oop

object Generics extends App {

  class MyList[B] {
    // use the type B later
  }

  val listInt = new MyList[Int]
  val listString = new MyList[String]

  class MyMap[key, value]

  // generic methods

  object MyList {
    def empty[A]: MyList[A] = ???
  }

  val emptyListOfIntegers = MyList.empty[Int]

  // variance problem

  class Animal

  class Cat extends Animal

  class Dog extends Animal

  /*


  If Cats extends Animal. Does List[Cat] extends List[Animal]??
  3 possible answers to this

  1) yes. List[Cat] extends List[Animal] -- Covariance
  class CovariantList[+A]
  the way it is declared/used is the same way we use Class animal and Cat

  eg:

  val animal : Animal = new Cat
  val animalList : CovariantList[Animal] = new CovariantList[Cat]
   Q) hard question: animalList.add(new Dog) is allowed??? ==> yes, it is allowed. But we return a CovariantList[Animal] instead.

   eg:

    class MyList[A] {
      def add(element: A) : MyList[A] = ??? -- throws error
      def add[B >: A](element: B) : MyList[B] = ??? -- accepted. coz, it returns the superclass of A. which is more generic.

      -- A = Cat
      -- B = Dog ==> Animal
    }


  2) no, they are 2 separate things -- Invariance
  class InvariantList[A]

  eg:

  val invariantList: InvariantList[Animal] = new InvariantList[Cat] -- would throw an error. It has to be,
  val invariantList: InvariantList[Animal] = new InvariantList[Animal]

  3) hell no. -- Contravariance

  class ContravariantList[-A]

  val contravariantList: ContravariantList[Cat] = new ContravariantList[Animal] -- this doesn't make much sense. expected a list of cats, but getting a list of any animal (dog etc)

  eg 2:

  class Trainer[-A]
  val trainer: Trainer[Cat] = new Trainer[Animal] -- makes sense now.

  BOUNDED TYPES: Allows us to use generic classes only for certain type that are either a sub class or a super class of a different type.

  eg:

  class Cage[A <: Animal](animal : A) -- read as, class Cage accepts any type that are subtype of Animal. Also called upper bounder class.
  class Cage[A >: Animal] --Lower bounded class is denoted by >:. It accepts anything the is a super class of Animal.

  val cage = new Cage(new Dog)
  class Car
  val cageCar = new Cage(new Car) -- not allowed coz car is not a sub type of Animal

 */


}
