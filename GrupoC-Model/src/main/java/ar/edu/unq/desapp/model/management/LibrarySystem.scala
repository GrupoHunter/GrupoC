package ar.edu.unq.desapp.model.management

import ar.edu.unq.desapp.model.bean.{Book, LoanConfiguration, User}

class LibrarySystem (val configLoan: LoanConfiguration){
  var books: List[Book] = Nil
  var users: List[User] = Nil

  def manualBookLoad(aBook: Book, amount: Int = 0) {
    books = aBook :: books
    if (amount > 1) {
      this.changeAmount(aBook.isbn, amount)
    }
  }

  def automaticBookLoadByIsbn(isbn: String) {
    //TODO - implement using googleAPI
  }

  def automaticBookLoadByTitle(title: String) {
    //TODO - implement using googleAPI
  }

  def removeBook(anIsbn: String) {
    books = books.flatMap(book => if (book.isbn == anIsbn) None else Some(book))
  }

  def changeAmount(anIsbn: String, amount: Int) {
    getBookByIsbn(anIsbn).get.amount = amount
  }

  def containsBook(anIsbn: String): Boolean = {
    getBookByIsbn(anIsbn) match {
      case Some(book) => books.contains(book)
      case None => false
    }
  }

  def getBookByIsbn(anIsbn: String): Option[Book] = {
    books find (b => b.isbn.contentEquals(anIsbn))
  }
}
