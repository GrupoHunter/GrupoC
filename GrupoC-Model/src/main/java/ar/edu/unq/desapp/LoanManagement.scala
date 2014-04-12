package ar.edu.unq.desapp

class LoanManagement {
  var reservedBooks: Map[String, List[Book]] = Map()
  var borrowedBooks: List[(User, Book)] = Nil

  def reserveBook(anUser: User, aBook: Book) {
    val someBook: List[Book] = reservedBooks.get(anUser.email) match {
      case Some(books) => aBook :: books
      case None => aBook :: Nil
    }

    reservedBooks += anUser.email -> someBook
  }

  def recordLoan(anUser: User, aBook: Book) {
    borrowedBooks = (anUser, aBook) :: borrowedBooks
  }

  def signUpNotification(anUser: User, aBook: Book) {
    //TODO
  }
}
