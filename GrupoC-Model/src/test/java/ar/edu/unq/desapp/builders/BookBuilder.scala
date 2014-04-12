package ar.edu.unq.desapp.builders

import java.awt.Image
import java.awt.image.BufferedImage
import ar.edu.unq.desapp.Author
import ar.edu.unq.desapp.Book

class BookBuilder extends BuilderToTest[Book] {

  var title: String = "no title"
  var isbn: String = "no isbn"
  var editorial: String = "no editorial"
  var image: Image = new BufferedImage(1, 1, 1)
  var description: String = "no description"
  var authors: List[Author] = Nil
  var amount: Int = 1

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

  def withImage(anImage: Image): BookBuilder = {
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

  override def build: Book = {
    new Book(
      this.title,
      this.isbn,
      this.editorial,
      this.image,
      this.description,
      this.authors,
      this.amount)
  }
}
