package ar.edu.unq.desapp

class User(username: String, email: String, password: String) {

  var amountAllowLoan: Int = 3
  var borrowedBooks: List[Book] = List()

  def borrowBook(aBook: Book): Unit = borrowedBooks.::(aBook)

  def returnBook(aBook: Book): Unit = borrowedBooks filter (book => book.equals(aBook))

  def commentBook(aBook: Book, aComment: String) {}
}
