package ar.edu.unq.desapp.appModel

import ar.edu.unq.desapp.model.bean.Book
import ar.edu.unq.desapp.services.bean.BookService
import ar.edu.unq.desapp.utils.builder.Builder

import scala.collection.JavaConversions._

class BookListAppModel(var bookService: BookService) extends Serializable with Builder {
  
  var books: java.util.List[Book] = bookService.retriveAll//new util.ArrayList

  def isAvailable(aBook: Book): String = {
    if(aBook.amount > getReservationsAmount(aBook)) "Available" else "Not available"
  }

  def getReservationsAmount(aBook: Book): Int = {
    1//TODO
  }

  def reserveBook(aBook: Book) {
    //TODO
  }

  def deleteBook(aBook: Book) {
    bookService.delete(aBook)
  }
}
