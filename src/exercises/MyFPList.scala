package exercises

import scala.runtime.Nothing$

abstract class MyFPList[+A] {

  def head: A

  def tail: MyFPList[A]

  def isEmpty: Boolean

  def add[B >: A](element: B): MyFPList[B]

  def printElements: String

  // polymorphic call
  override def toString: String = "[" + printElements + "]"

  def map[B](transformer: A => B): MyFPList[B]

  def flatMap[B](transformer: A => MyFPList[B]): MyFPList[B]

  def filter(predicate: A => Boolean): MyFPList[A]

  def ++[B >: A](list: MyFPList[B]): MyFPList[B]
}

case object FPEmpty extends MyFPList[Nothing] {

  def head: Nothing = throw new NoSuchElementException

  def tail: MyFPList[Nothing] = throw new NoSuchElementException

  def isEmpty: Boolean = true

  def add[B >: Nothing](element: B): MyFPList[B] = new FPCons(element, FPEmpty)

  def printElements: String = ""

  override def map[B](transformer: Nothing => B): MyFPList[B] = FPEmpty

  override def flatMap[B](transformer: Nothing => MyFPList[B]): MyFPList[B] = FPEmpty

  override def filter(predicate: Nothing => Boolean): MyFPList[Nothing] = FPEmpty

  override def ++[B >: Nothing](list: MyFPList[B]): MyFPList[B] = list
}

case class FPCons[+A](h: A, t: MyFPList[A]) extends MyFPList[A] {

  def head: A = h

  def tail: MyFPList[A] = t

  def isEmpty: Boolean = false

  def add[B >: A](element: B): MyFPList[B] = new FPCons(element, this)

  def printElements: String =
    if (t.isEmpty) "" + h
    else h + " " + t.printElements

  override def map[B](transformer: A => B): MyFPList[B] =
    new FPCons(transformer(h), t.map(transformer))

  override def flatMap[B](transformer: A => MyFPList[B]): MyFPList[B] =
    transformer(h) ++ t.flatMap(transformer)

  override def filter(predicate: A => Boolean): MyFPList[A] =
    if (predicate(h)) new FPCons(h, t.filter(predicate))
    else t.filter(predicate)

  override def ++[B >: A](list: MyFPList[B]): MyFPList[B] = new FPCons(h, t ++ list)
}

object FPListTest extends App {
  val listOfIntegers: MyFPList[Int] = new FPCons[Int](1, new FPCons[Int](2, new FPCons[Int](3, FPEmpty)))
  val clonedListOfIntegers: MyFPList[Int] = new FPCons[Int](1, new FPCons[Int](2, new FPCons[Int](3, FPEmpty)))
  val anotherListOfIntegers: MyFPList[Int] = new FPCons[Int](4, new FPCons[Int](5, new FPCons[Int](6, FPEmpty)))
  val listOfString: MyFPList[String] = new FPCons[String]("Hello,", new FPCons[String]("Learning", new FPCons[String]("Scala", FPEmpty)))

  println(listOfIntegers.toString)
  println(listOfString.toString)

  println(listOfIntegers.map(new Function1[Int, Int] {
    def apply(elem: Int): Int = elem * 2
  }).toString)

  println(listOfIntegers.filter(new Function1[Int, Boolean] {
    def apply(elem: Int): Boolean = elem % 2 == 0
  }).toString)

  println(listOfIntegers ++ anotherListOfIntegers)

  println(listOfIntegers.flatMap(new Function1[Int, MyFPList[Int]] {
    def apply(elem: Int): MyFPList[Int] = new FPCons(elem, new FPCons(elem + 1, FPEmpty))
  }).toString)

  /*
  checking case class equality: below statement returns true. coz, equals and hash code are already implemented with Case class.
   */

  println(listOfIntegers == clonedListOfIntegers)


}
