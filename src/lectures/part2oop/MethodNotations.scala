package lectures.part2oop

import scala.language.postfixOps

object MethodNotations extends App {

  class Person(val name: String, favoriteMovie: String, val  age: Int = 0) {
    def likes(movieName: String): Boolean = movieName == favoriteMovie
    def +(person: Person): String = s"${this.name} is hanging out with ${person.name}"
    def +(nickName : String) : Person = new Person(s"${this.name} (${nickName})",this.favoriteMovie)
    def unary_! : String = s"$name, what the heck!?"
    def unary_+ : Person = new Person(this.name,this.favoriteMovie, this.age+1)
    def isAlive: Boolean = true
    def apply(): String = s"Hi, my name is $name and I like $favoriteMovie"
    def learns(lang: String) : String = s"${this.name} learns $lang"
    def learnsScala() : String = learns("Scala")
    def apply(num : Int) : String = s"${this.name} watched ${this.favoriteMovie} $num times"
  }

  val mary = new Person("Mary", "Wake up Sid!")

  /**
   * Four types of important concepts
   * 1) Infix notations : applicable only on functions with 1 argument
   * 2) prefix notations : applicable only with unary +/-/!/~
   * 3) postfix notations : applicable on functions with no parameters
   * 4) apply notation : just () is used to call this function. No need to specify apply
   */

  // Syntactic sugar also called as infix operators

  println(mary.likes("Wake up Sid!"))
  println(mary likes "Wake up Sid!" )

  val john = new Person("John", "Piku")
  println(mary.+(john))
  // infix operators can be applied only with functions with one parameter
  println(mary + john)

  // Prefix notation

  val x = +1 // equivalent to 1.unary_+
  val y = 1.unary_-

  println(x)
  println(y)

  println(!mary)
  println(mary.unary_!)

  // Postfix notation

  println(mary.isAlive)
  println(mary isAlive)

  // Apply
  println(mary.apply()) // or
  println(mary())

  /** exercise */

  println((mary + "The rock-star")())
  println((+mary).age)
  println(mary learnsScala)
  println(mary(5))

}
