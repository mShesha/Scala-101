package lectures.part2oop

object OOBasics extends App {

  val author = new writer("Priyanka", "Chopra", 1984)
  val novel = new novel("unfinished",2021, author)

  println(novel.authorAge)
  println(novel.isWrittenBy(author))

}

class writer(firstName: String, surName: String, val yearOfBirth: Int) {
  def fullName(): String = {
    firstName + " " + surName
  }
}

class novel(name: String, yearOfRelease: Int, author: writer) {
  def authorAge = yearOfRelease - author.yearOfBirth

  def isWrittenBy(author: writer) = author == this.author

  def copy(newYear: Int) = new novel(name, newYear, author)
}