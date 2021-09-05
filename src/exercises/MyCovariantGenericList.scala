package exercises

import scala.runtime.Nothing$

abstract class MyCovariantGenericList[+A] {

  def head: A

  def tail: MyCovariantGenericList[A]

  def isEmpty: Boolean

  def add[B >: A](element: B): MyCovariantGenericList[B]

  def printElements: String

  // polymorphic call
  override def toString: String = "[" + printElements + "]"

  def map[B](transformer: MyTransformer[A, B]): MyCovariantGenericList[B]

  def flatMap[B](transformer: MyTransformer[A, MyCovariantGenericList[B]]): MyCovariantGenericList[B]

  def filter(predicate: MyPredicate[A]): MyCovariantGenericList[A]

  def ++[B >: A](list: MyCovariantGenericList[B]): MyCovariantGenericList[B]
}

object CovariantGenericEmpty extends MyCovariantGenericList[Nothing] {

  def head: Nothing = throw new NoSuchElementException

  def tail: MyCovariantGenericList[Nothing] = throw new NoSuchElementException

  def isEmpty: Boolean = true

  def add[B >: Nothing](element: B): MyCovariantGenericList[B] = new CovariantGenericCons(element, CovariantGenericEmpty)

  def printElements: String = ""

  override def map[B](transformer: MyTransformer[Nothing, B]): MyCovariantGenericList[B] = CovariantGenericEmpty

  override def flatMap[B](transformer: MyTransformer[Nothing, MyCovariantGenericList[B]]): MyCovariantGenericList[B] = CovariantGenericEmpty

  override def filter(predicate: MyPredicate[Nothing]): MyCovariantGenericList[Nothing] = CovariantGenericEmpty

  override def ++[B >: Nothing](list: MyCovariantGenericList[B]): MyCovariantGenericList[B] = list
}

class CovariantGenericCons[+A](h: A, t: MyCovariantGenericList[A]) extends MyCovariantGenericList[A] {

  def head: A = h

  def tail: MyCovariantGenericList[A] = t

  def isEmpty: Boolean = false

  def add[B >: A](element: B): MyCovariantGenericList[B] = new CovariantGenericCons(element, this)

  def printElements: String =
    if (t.isEmpty) "" + h
    else h + " " + t.printElements

  override def map[B](transformer: MyTransformer[A, B]): MyCovariantGenericList[B] =
    new CovariantGenericCons(transformer.transform(h), t.map(transformer))

  override def flatMap[B](transformer: MyTransformer[A, MyCovariantGenericList[B]]): MyCovariantGenericList[B] =
    transformer.transform(h) ++ t.flatMap(transformer)

  override def filter(predicate: MyPredicate[A]): MyCovariantGenericList[A] =
    if (predicate.test(h)) new CovariantGenericCons(h, t.filter(predicate))
    else t.filter(predicate)

  override def ++[B >: A](list: MyCovariantGenericList[B]): MyCovariantGenericList[B] = new CovariantGenericCons(h, t ++ list)
}

trait MyPredicate[-T] {
  def test(elem: T): Boolean
}

trait MyTransformer[-A, B] {
  def transform(elem: A): B
}

object ExpandedListTest extends App {
  val listOfIntegers: MyCovariantGenericList[Int] = new CovariantGenericCons[Int](1, new CovariantGenericCons[Int](2, new CovariantGenericCons[Int](3, CovariantGenericEmpty)))
  val anotherListOfIntegers: MyCovariantGenericList[Int] = new CovariantGenericCons[Int](4, new CovariantGenericCons[Int](5, new CovariantGenericCons[Int](6, CovariantGenericEmpty)))
  val listOfString: MyCovariantGenericList[String] = new CovariantGenericCons[String]("Hello,", new CovariantGenericCons[String]("Learning", new CovariantGenericCons[String]("Scala", CovariantGenericEmpty)))

  println(listOfIntegers.toString)
  println(listOfString.toString)

  println(listOfIntegers.map(new MyTransformer[Int, Int] {
    override def transform(elem: Int): Int = elem * 2
  }).toString)

  println(listOfIntegers.filter(new MyPredicate[Int] {
    override def test(elem: Int): Boolean = elem % 2 == 0
  }).toString)

  println(listOfIntegers ++ anotherListOfIntegers)

  println(listOfIntegers.flatMap(new MyTransformer[Int, MyCovariantGenericList[Int]] {
    override def transform(elem: Int): MyCovariantGenericList[Int] = new CovariantGenericCons(elem, new CovariantGenericCons(elem + 1, CovariantGenericEmpty))
  }).toString)

}
