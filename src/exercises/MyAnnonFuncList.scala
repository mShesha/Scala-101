package exercises

import scala.runtime.Nothing$

abstract class MyAnnonFuncList[+A] {

  def head: A

  def tail: MyAnnonFuncList[A]

  def isEmpty: Boolean

  def add[B >: A](element: B): MyAnnonFuncList[B]

  def printElements: String

  // polymorphic call
  override def toString: String = "[" + printElements + "]"

  def map[B](transformer: A => B): MyAnnonFuncList[B]

  def flatMap[B](transformer: A => MyAnnonFuncList[B]): MyAnnonFuncList[B]

  def filter(predicate: A => Boolean): MyAnnonFuncList[A]

  def ++[B >: A](list: MyAnnonFuncList[B]): MyAnnonFuncList[B]
}

case object AnnonFuncEmpty extends MyAnnonFuncList[Nothing] {

  def head: Nothing = throw new NoSuchElementException

  def tail: MyAnnonFuncList[Nothing] = throw new NoSuchElementException

  def isEmpty: Boolean = true

  def add[B >: Nothing](element: B): MyAnnonFuncList[B] = new AnnonFuncCons(element, AnnonFuncEmpty)

  def printElements: String = ""

  override def map[B](transformer: Nothing => B): MyAnnonFuncList[B] = AnnonFuncEmpty

  override def flatMap[B](transformer: Nothing => MyAnnonFuncList[B]): MyAnnonFuncList[B] = AnnonFuncEmpty

  override def filter(predicate: Nothing => Boolean): MyAnnonFuncList[Nothing] = AnnonFuncEmpty

  override def ++[B >: Nothing](list: MyAnnonFuncList[B]): MyAnnonFuncList[B] = list
}

case class AnnonFuncCons[+A](h: A, t: MyAnnonFuncList[A]) extends MyAnnonFuncList[A] {

  def head: A = h

  def tail: MyAnnonFuncList[A] = t

  def isEmpty: Boolean = false

  def add[B >: A](element: B): MyAnnonFuncList[B] = new AnnonFuncCons(element, this)

  def printElements: String =
    if (t.isEmpty) "" + h
    else h + " " + t.printElements

  override def map[B](transformer: A => B): MyAnnonFuncList[B] =
    new AnnonFuncCons(transformer(h), t.map(transformer))

  override def flatMap[B](transformer: A => MyAnnonFuncList[B]): MyAnnonFuncList[B] =
    transformer(h) ++ t.flatMap(transformer)

  override def filter(predicate: A => Boolean): MyAnnonFuncList[A] =
    if (predicate(h)) new AnnonFuncCons(h, t.filter(predicate))
    else t.filter(predicate)

  override def ++[B >: A](list: MyAnnonFuncList[B]): MyAnnonFuncList[B] = new AnnonFuncCons(h, t ++ list)
}

object AnnonFuncListTest extends App {
  val listOfIntegers: MyAnnonFuncList[Int] = new AnnonFuncCons[Int](1, new AnnonFuncCons[Int](2, new AnnonFuncCons[Int](3, AnnonFuncEmpty)))
  val clonedListOfIntegers: MyAnnonFuncList[Int] = new AnnonFuncCons[Int](1, new AnnonFuncCons[Int](2, new AnnonFuncCons[Int](3, AnnonFuncEmpty)))
  val anotherListOfIntegers: MyAnnonFuncList[Int] = new AnnonFuncCons[Int](4, new AnnonFuncCons[Int](5, new AnnonFuncCons[Int](6, AnnonFuncEmpty)))
  val listOfString: MyAnnonFuncList[String] = new AnnonFuncCons[String]("Hello,", new AnnonFuncCons[String]("Learning", new AnnonFuncCons[String]("Scala", AnnonFuncEmpty)))

  println(listOfIntegers.toString)
  println(listOfString.toString)

  println(listOfIntegers.map((elem: Int) => elem * 2).toString)

  println(listOfIntegers.filter((elem: Int) => elem % 2 == 0).toString)

  println(listOfIntegers ++ anotherListOfIntegers)

  println(listOfIntegers.flatMap((elem: Int) => new AnnonFuncCons(elem, new AnnonFuncCons(elem + 1, AnnonFuncEmpty))).toString)

  /*
  checking case class equality: below statement returns true. coz, equals and hash code are already implemented with Case class.
   */

  println(listOfIntegers == clonedListOfIntegers)


}
