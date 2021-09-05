package lectures.part2oop

object CaseClasses extends App {
  /*
  light weight DS, i.e, for class Person, we need to implement all sort of boiler plate code.
  i.e, companion objects, std methods for serializing, pretty printing, equals, hash code, toString etc.

  case classes are an ideal solution for this.
  it is a short hand for defining class companion objects and a lot of sensible defaults in one go!
   */

  case class Person(name: String, age: Int)

  // 1. class params are promoted to fields

  val jim = new Person("Jim", 30)
  println(jim.name) // allowed

  // 2. a sensible toString

  println(jim.toString) // no cryptic output

  // 3. equals and hash code are implemented out of the box. used mainly in collections

  val jim1 = new Person("Jim", 30)
  println(jim == jim1)

  // 4. CCs have handy copy method

  val jim2 = jim.copy()
  val jim3 = jim.copy(age = 35)

  println(jim2)
  println(jim3)

  // 5. CCs have companion objects.
  // 6. CCs also have factory methods in their apply()

  val thePerson = Person
  val mary = Person("Mary", 25)

  // 7. CCs are serializable. Help when sent as a msg over the network.
  // 8. CCs have extractors patterns == can be used in patter matching.
  // 9. CCs also have case objects. Same as case classes except that they don't get companion object. Coz, they are the their own companion objects

  case object UnitedKingdom {
    def name: String = "The UK of the GB and NI"
  }

}
