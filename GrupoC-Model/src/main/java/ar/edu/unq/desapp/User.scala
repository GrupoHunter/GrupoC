package ar.edu.unq.desapp

class User(val username: String, val email: String, val password: String) {

  var amountAllowLoan: Int = 3
  var borrowedBooks: List[Book] = List()

  def borrowBook(aBook: Book): Unit = borrowedBooks = aBook :: borrowedBooks

  def returnBook(aBook: Book): Unit = borrowedBooks = borrowedBooks filter (book => book.equals(aBook))

  def commentBook(aBook: Book, aComment: String) {
    //TODO - implement
  }
}
