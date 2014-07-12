package ar.edu.unq.desapp.appModel

import ar.edu.unq.desapp.model.bean.Book
import ar.edu.unq.desapp.services.GeneralService
import ar.edu.unq.desapp.services.bean.{BookService, LoanBookService}
import ar.edu.unq.desapp.utils.builder.Builder
import ar.edu.unq.desapp.view.security.ScalaBaseProjectSession

import scala.collection.JavaConverters._

class BookListAppModel(var generalService: GeneralService) extends Serializable with Builder {
  
  val bookService: BookService = generalService.bookService
  val loanService: LoanBookService = generalService.loanBookService

  var books: java.util.List[Book] = bookService.retriveAll.asJava
  var search: String = ""
  val isNew: String = "New"

  def isAvailable(aBook: Book): String = {
    if(aBook.isAvailable) "Available" else "Not available"
  }

  def getBorrowedAmount(aBook: Book): Int = {
    aBook.borrowedAmount
  }

  def reserveBook(aBook: Book) {
    //TODO
  }

  def deleteBook(aBook: Book) {
    bookService.delete(aBook)
  }

  def loanBook(aBook: Book) {
    loanService.recordLoan(ScalaBaseProjectSession.getCurrentUser.getUsername ,aBook)
  }
}
