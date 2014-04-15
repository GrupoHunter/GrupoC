package ar.edu.unq.desapp

class User(val username: String, val email: String, val password: String) {

  var borrowedBooks: List[Book] = Nil
  var amountAllowLoan = 3 //Default value

  def borrowBook(aBook: Book) {
    borrowedBooks = aBook :: borrowedBooks
  }

  def returnBook(aBook: Book) {
    borrowedBooks = borrowedBooks filter (book => book.equals(aBook))
    aBook.amount += 1
  }

  def commentBook(aBook: Book, aComment: String) {
    //TODO - implement
  }
}
