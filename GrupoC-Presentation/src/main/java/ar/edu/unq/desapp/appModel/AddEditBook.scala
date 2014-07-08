package ar.edu.unq.desapp.appModel

import ar.edu.unq.desapp.model.bean.{Author, Book}
import ar.edu.unq.desapp.services.GeneralService
import ar.edu.unq.desapp.utils.builder.Builder

import scala.collection.JavaConversions._

class AddEditBook(var generalService: GeneralService) extends Serializable with Builder{

  val book: Book = new Book(null, null, null, null, null)
  var create: Boolean = true
  
  var book_title: String = _
  var book_isbn: String = _
  var book_editorial: String = _
  var book_imageUrl: String = _
  var book_description: String = _
  var book_authors: String = _

  def getAuthors: List[Author] = {
    var authors: List[Author] = Nil
    val names = book_authors.split(", ")
    for (authorName <- names ) {
      val author: Author = new Author(authorName)
      saveAuthor(author)
      authors = author :: authors
    }
    authors
  }

  def saveChanges() {
    book.title = book_title
    book.isbn = book_isbn
    book.editorial = book_editorial
    book.imageUrl = book_imageUrl
    book.description = book_description
    book.authors = getAuthors
    generalService.bookService.save(book)
  }

  def getExternalBook(): Book = {
    generalService.bookService.getExternalBook(book_isbn)
  }
  
  def editBook(aBook: Book) {
   book_title = aBook.title
   book_isbn = aBook.isbn
   book_editorial = aBook.editorial
   book_imageUrl = aBook.imageUrl
   book_description = aBook.description
//   book_authors = aBook.authors.toListNil
   book_authors = ""
    for (author <- aBook.authors) {
      book_authors = author.getName ++ ", " ++ book_authors
    }
  }

  def saveAuthor(author: Author){
    generalService.authorService.save(author)
  }
}
