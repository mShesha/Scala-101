package lectures.part2oop

import scala.language.postfixOps

object Objects extends App {

  // Scala doesn't have cass level functionality

  object Person { // you are defining this type and it's only instance
    // class level functionality
    val n_eyes = 2
    def canFly() : Boolean = false

    // factory method : whole purpose is to build new Person.
    // This is also called apply method
    def apply(mother: Person, father: Person, cName: String) : Person = new Person(cName)
  }

  class Person (val name : String) {
    // instance level functionality
  }

  // companion objects

  println(Person.n_eyes)
  println(Person.canFly())
  println(Person canFly) // postfix notation. Works on objects as well

  // Scala objects are a singleton instance
  val mary = new Person("Mary")
  val john = new Person("John")
  println(mary == john)

  val person1 = Person
  val person2 = Person
  println(person1 == person2)

  val bobbie = Person(mary,john,"Bobbie")

  // Scala applications = Scala object with
  // def main(args: Array[String]) : Unit



}
