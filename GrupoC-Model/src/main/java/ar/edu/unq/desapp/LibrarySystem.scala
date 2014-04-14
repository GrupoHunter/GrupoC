package ar.edu.unq.desapp

import java.util.ArrayList

class LibrarySystem {
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
    this.getBookByIsbn(anIsbn) match {
      case Some(book) => books filter (b => b.equals(book))
      case None => Nil
    }
  }

  def changeAmount(anIsbn: String, amount: Int) {
    getBookByIsbn(anIsbn).get.amount += amount
  }

  def containsBook(anIsbn: String): Boolean = {
    val book = getBookByIsbn(anIsbn).get
    books.contains(book)
  }

  def getBookByIsbn(anIsbn: String): Option[Book] = {
    books find (b => (b.isbn == anIsbn))
  }
}
