package ar.edu.unq.desapp

class Librarian(username: String, email: String, password: String, val librarySystem: LibrarySystem)
  extends User(username, email, password) {

  def addBookToSystem(aTitle: String, anIsbn: String, anEditorial: String, anImage: Image, aDescription: String, authors: List[Author]) = {
    if (librarySystem.containsBook(anIsbn))
      librarySystem changeAmount(anIsbn, 1)
    else {
      val loadingBook = new Book(aTitle, anIsbn, anEditorial, anImage, aDescription, authors)
      librarySystem manualBookLoad(loadingBook)
    }
  }

  def modifyBookFromTheSystem(oldBook: Book, newBook: Book) = {
    librarySystem.books.map(book => if(book._1.equals(oldBook)) (newBook,book._2) else book)
  }

  def deleteBookFromTheSystem(aBook: Book) = librarySystem removeBook (aBook.isbn)

  def configureMaxReservesAmount(amount: Int) {}//TODO implement

  def configureMaxDaysOfLoan(maxDate: Int) {}//TODO implement max date to loan

  def registerUser(username: String, email: String, password: String) {}//TODO: implement register user
}