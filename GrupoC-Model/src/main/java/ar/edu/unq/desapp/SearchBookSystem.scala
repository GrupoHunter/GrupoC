package ar.edu.unq.desapp

class SearchBookSystem {
  val system: LibrarySystem = new LibrarySystem()
  var users: List[User] = List()

  def top20borrowed: List[Book] = {
    Nil //TODO - implement
  }

  def lastBooksAdded: List[Book] = {
    Nil //TODO - implement
  }

  def searchBook(aRequest: String): List[Book] = {
    Nil //TODO - implement
  }

  def searchByIsbn(isbn: String): List[Book] = {
    Nil //TODO - implement
  }
}
