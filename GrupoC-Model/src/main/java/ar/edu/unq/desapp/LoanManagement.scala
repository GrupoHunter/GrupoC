package ar.edu.unq.desapp

class LoanManagement {
  var reservedBooks: Map[String, List[Book]] = Map()
  var borrowedBooks: List[(User, Book)] = Nil
  var amountAllowLoan: Int = 3

  def reserveBook(anUser: User, aBook: Book) {
    val isReserved: Boolean = reservedBooks.values.exists(b => b.contains(aBook))
    val reserveBooksToUser: List[Book] = reservedBooks.get(anUser.email) match {
      case Some(books) =>
        if (books.length < amountAllowLoan && !isReserved)
          aBook :: books
        else
          books
      case None => if (!isReserved) List(aBook) else Nil
    }
    reservedBooks += (anUser.email -> reserveBooksToUser)
  }

  def recordLoan(anUser: User, aBook: Book) {
    if (aBook.amount - 1 >= 0){
      aBook.amount -= 1
      borrowedBooks = (anUser, aBook) :: borrowedBooks
    }
  }
  
  def signUpNotification(anUser: User, aBook: Book) {
    //TODO
  }
}
