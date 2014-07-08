package ar.edu.unq.desapp.appModel

import ar.edu.unq.desapp.model.bean.Book
import ar.edu.unq.desapp.services.bean.BookService
import ar.edu.unq.desapp.utils.builder.Builder

class RankingBorrowedBookAppModel (var bookService: BookService) extends Serializable with Builder {
  var mostBorrowed: java.util.List[Book] = bookService.retriveAllMostBorrowed
}
