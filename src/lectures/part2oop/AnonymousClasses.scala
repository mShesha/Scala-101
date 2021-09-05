package lectures.part2oop

object AnonymousClasses extends App {

  trait Animal {
    def eat: Unit
  }

  val funnyAnimal = new Animal {
    override def eat: Unit = println("hahahahahaahah")
  }

  /*
  equivalent to :
  class annon$1 extends Animal {
    override def eat: Unit = println("hahahahahaahah")
  }

  val funnyAnimal = new annon$1
  funnyAnimal.eat()

  on the spot implementation
   */
}
