package ar.edu.unq.desapp

class LoanManagement {
  var reservedBooks: Map[String, List[Book]] = Map()
  var borrowedBooks: List[(User, Book)] = List()

  def reserveBook(anUser: User, aBook: Book): Unit = {
    val someBook: List[Book] = reservedBooks get anUser.email match {
      case Some(books) => books
      case None => List(aBook)
    }

    reservedBooks.updated(anUser.email, someBook)
  }

  def recordLoan(anUser: User, aBook: Book) {
    //TODO
  }

  def signUpNotification(anUser: User, aBook: Book) {
    //TODO
  }
}
