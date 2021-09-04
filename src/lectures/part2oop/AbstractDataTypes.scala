package lectures.part2oop

object AbstractDataTypes extends App {
  /*
    fields / methods blank
    classes having abstract field or methods are called Abstract class
    -- cannot be instantiated as they need values
   */

  abstract class Animal {
    val creatureType : String
    def eat : Unit
  }

  class Dog extends Animal {
    // no need of override keyword
    override val creatureType: String = "Canine"
    def eat: Unit = println("crunch-crunch")
  }

  // traits - ultimate abstract classes

  trait Carnivore {
    def eat(animal : Animal): Unit
  }

  class Crocodile extends Animal with Carnivore {
    override val creatureType: String = "Croc"
    def eat = println("nom-nom-nom")
    def eat(animal: Animal)= println(s"I am a croc and I am eating a ${animal.creatureType}")
  }

  val dog = new Dog
  val croc = new Crocodile
  croc.eat(dog)

  // traits vs abstract classes
  // -- both abstract and traits can have abstract and non-abstract variables/definitions
  // -- traits cannot have constructor parameters
  // -- only extend one class but extend multiple traits
  // -- traits are behaviour, abstract class == "thing"

}
