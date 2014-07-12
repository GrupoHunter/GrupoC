package ar.edu.unq.desapp.utils.builder

import ar.edu.unq.desapp.model.bean.{Author, Book, Comment}
import org.joda.time.DateTime

import scala.collection.JavaConversions._

class BookBuilder extends BuilderToTest[Book] {

  var title: String = "no title"
  var isbn: String = "no isbn"
  var editorial: String = "no editorial"
  var image: String = "../../empty.jpe"
  var description: String = "no description"
  var authors: List[Author] = Nil
  var comments: List[Comment] = Nil
  var amount: Int = 1 //DEFAULT!!!!
  var registrationDate = new DateTime

  def withTitle(aTitle: String): BookBuilder = {
    this.title = aTitle
    this
  }

  def withIsbn(anIsbn: String): BookBuilder = {
    this.isbn = anIsbn
    this
  }

  def withEditorial(anEditorial: String): BookBuilder = {
    this.editorial = anEditorial
    this
  }

  def withImage(anImage: String): BookBuilder = {
    this.image = anImage
    this
  }

  def withDescription(anDescription: String): BookBuilder = {
    this.description = anDescription
    this
  }

  def withAmount(n: Int): BookBuilder = {
    this.amount = n
    this
  }

  def withAuthors(myAuthors: List[Author]): BookBuilder = {
    this.authors = myAuthors
    this
  }
  
  def withComments(comments: List[Comment]): BookBuilder = {
    this.comments = comments
    this
  }

  def withRegistrationDate(date: DateTime): BookBuilder = {
    this.registrationDate = date
    this
  }

  def build: Book = {
    val book = new Book(
      this.title,
      this.isbn,
      this.editorial,
      this.image,
      this.description,
      this.amount,
      this.registrationDate)
    
    book.authors = this.authors.toList
    clean()
    book
  }

  def clean() {
    this.title = "no title"
    this.isbn = "no isbn"
    this.editorial = "no editorial"
    this.image = "../../empty.jpe"
    this.description = "no description"
    this.authors = Nil
    this.comments = Nil
    this.amount = 1
    this.registrationDate = new DateTime
  }
}
