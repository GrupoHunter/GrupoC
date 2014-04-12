package ar.edu.unq.desapp.builders

import ar.edu.unq.desapp.Book
import ar.edu.unq.desapp.User
import ar.edu.unq.desapp.LibrarySystem
import ar.edu.unq.desapp.Librarian

abstract class UserBuilder extends BuilderToTest[User] {

  var name: String = "no name"
  var password: String = "no password"
  var email: String = "no email"
  var borrowedBooks: List[Book] = List()

  def withName(aName: String): UserBuilder

  def withPassword(aPassword: String): UserBuilder

  def withEmail(anEmail: String): UserBuilder

  def withBorrowedBooks(books: List[Book]): UserBuilder

}

class UserIdentity extends UserBuilder {

  override def withName(aName: String): UserBuilder = {
    this.name = name
    this
  }

  override def withPassword(aPassword: String): UserBuilder = {
    this.password = aPassword
    this
  }

  override def withEmail(anEmail: String): UserBuilder = {
    this.email = anEmail
    this
  }

  override def withBorrowedBooks(books: List[Book]): UserBuilder = {
    this.borrowedBooks = books
    this
  }

  override def build: User = {
    val user = new User(this.name, this.email, this.password)
    user.borrowedBooks = this.borrowedBooks
    user
  }
}

class LibrarianBuilder(var librarySystem: LibrarySystem) extends UserIdentity {
  var system = librarySystem

  def withLibrarySystem(libSystem: LibrarySystem): UserBuilder = {
    this.librarySystem = libSystem
    this
  }

  override def build: Librarian = {
    val librarian = new Librarian(this.name, this.email, this.password, this.librarySystem)
    librarian.borrowedBooks = this.borrowedBooks
    librarian
  }
}
