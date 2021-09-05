package exercises

import scala.runtime.Nothing$

abstract class MyGenericList[+A] {

  def head: A

  def tail: MyGenericList[A]

  def isEmpty: Boolean

  def add[B >: A](element: B): MyGenericList[B]

  def printElements: String

  // polymorphic call
  override def toString: String = "[" + printElements + "]"

}

object GenericEmpty extends MyGenericList[Nothing] {

  def head: Nothing = throw new NoSuchElementException

  def tail: MyGenericList[Nothing] = throw new NoSuchElementException

  def isEmpty: Boolean = true

  def add[B >: Nothing](element: B): MyGenericList[B] = new GenericCons(element, GenericEmpty)

  def printElements: String = ""

}

class GenericCons[+A](h: A, t: MyGenericList[A]) extends MyGenericList[A] {

  def head: A = h

  def tail: MyGenericList[A] = t

  def isEmpty: Boolean = false

  def add[B >: A](element: B): MyGenericList[B] = new GenericCons(element, this)

  def printElements: String =
    if (t.isEmpty) "" + h
    else h + " " + t.printElements

}

object GenericListTest extends App {
  val listOfIntegers: MyGenericList[Int] = new GenericCons[Int](1,new GenericCons[Int](2, new GenericCons[Int](3, GenericEmpty)))
  val listOfString: MyGenericList[String] = new GenericCons[String]("Hello,",new GenericCons[String]("Learning", new GenericCons[String]("Scala", GenericEmpty)))

  println(listOfIntegers.toString)
  println(listOfString.toString)
}
