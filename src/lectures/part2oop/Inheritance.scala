package lectures.part2oop

object Inheritance extends App {

  // Single class inheritance

  sealed class Animal{
    val creatureType = "wild"
    def eat = println("nom-nom-nom")
  }

  class Cat extends Animal {
    def crunch = {
      eat
      println("crunch-crunch-crunch")
    }
  }

  val cat = new Cat
  cat crunch

  // Constructors
  // auxiliary constructor

  class Person(name: String, age: Int){
    def this(name: String) = this(name,0)
  }
  class Adult(name: String, age: Int, idCard: String) extends Person(name)

  // overriding

  class Dog(override val creatureType : String) extends Animal {
    // override val creatureType= "domestic" // can be overridden in the parameter field
    override def eat: Unit = {
      super.eat
      println("crunch-nom-crunch-nom")
    }
  }

  val dog = new Dog("k9")
  dog.eat
  println(dog.creatureType)

  // type substitution
  // broadly called as polymorphism

  val unknownAnimal : Animal = new Dog("K9")
  unknownAnimal.eat // won't print "nom-nom-nom" instead prints "crunch-nom-crunch-nom". coz, it is overridden in class Dog

  // super : is used with we want to access the elements from the parent class

  // prevent overrides
  // 1) use final on the methods to prevent overriding
  // 2) use final on the class itself to prevent inheritance
  // 3) seal the class -- softer extension -- extend in this file but restricted in other files

}
