package ar.edu.unq.desapp

import java.awt.Image

class Librarian(username: String, email: String, password: String, val librarySystem: LibrarySystem)
  extends User(username, email, password) {

  def addBookToSystem(aTitle: String, anIsbn: String, anEditorial: String, anImage: Image, aDescription: String, authors: List[Author], cant: Int = 1) = {
    val loadingBook = new Book(aTitle, anIsbn, anEditorial, anImage, aDescription, authors)
    librarySystem manualBookLoad (loadingBook, cant)
  }

  def modifyBookFromTheSystem(oldBook: Book, newBook: Book): Unit = {
    //TODO: Implement
  }

  def deleteBookFromTheSystem(aBook: Book) = librarySystem removeBook (aBook.isbn)

  def configureMaxReservesAmount(amount: Int) = {} //TODO implement

  def configureMaxDaysOfLoan(maxDate: Int) = {} //TODO implement max date to loan

  def registerUser(username: String, email: String, password: String) = {} //TODO: implement register user
}