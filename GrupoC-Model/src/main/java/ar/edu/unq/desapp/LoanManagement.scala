package ar.edu.unq.desapp

import org.joda.time.DateTime

class LoanManagement(val notificationSystem: NotificationSystem) {
  var reservedBooks: Map[String, List[Book]] = Map()
  var borrowedBooks: List[LoanBook] = Nil
  var maxDaysOfLoan: Int = 4 //Default

  def reserveBook(anUser: User, aBook: Book) {
    val isReserved: Boolean = reservedBooks.values.exists(b => b.contains(aBook))
    val reserveBooksToUser: List[Book] = reservedBooks.get(anUser.email) match {
      case Some(books) =>
        if (books.length < anUser.amountAllowLoan && !isReserved)
          aBook :: books
        else
          books
      case None => if (!isReserved) List(aBook) else Nil
    }
    reservedBooks += (anUser.email -> reserveBooksToUser)
  }

  def recordLoan(anUser: User, aBook: Book) {
    if (aBook.amount - 1 >= 0) { //validate
      aBook.amount -= 1

      val refundDate = new DateTime().plusDays(4)
      val loanBook = new LoanBook(anUser, aBook, new DateTime, refundDate)
      borrowedBooks = (loanBook) :: borrowedBooks

      anUser.borrowBook(aBook)
    }
  }

  def deleteLoan(anUser: User, aBook: Book) {
	  borrowedBooks = borrowedBooks.filter(b => b.equals(aBook))
	  anUser.returnBook(aBook)
	  notificationSystem.notifyAllUsers(aBook)
  }

  def signUpNotification(anUser: User, aBook: Book) {
    notificationSystem.addObserver(anUser, aBook)
  }
}
