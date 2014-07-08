package ar.edu.unq.desapp.utils.builder

import ar.edu.unq.desapp.model.bean.{Book, Librarian, User}
import ar.edu.unq.desapp.model.management.LibrarySystem

import scala.collection.JavaConversions._

abstract class LibraryUserBuilder[T <: LibraryUserBuilder[T, U], U] extends BuilderToTest[U] {

  var name: String = "no name"
  var password: String = "no password"
  var email: String = "no email"
  var borrowedBooks: List[Book] = List()

  def withName(aName: String): LibraryUserBuilder[T, U] = {
    this.name = aName
    this
  }

  def withPassword(aPassword: String): LibraryUserBuilder[T, U] = {
    this.password = aPassword
    this
  }

  def withEmail(anEmail: String): LibraryUserBuilder[T, U] = {
    this.email = anEmail
    this
  }

  def withBorrowedBooks(books: List[Book]): LibraryUserBuilder[T, U] = {
    this.borrowedBooks = books
    this
  }

}

class UserBuilder extends LibraryUserBuilder[UserBuilder, User] {

  def build: User = {
    val user = new User(this.name, this.email, this.password)
    user.borrowedBooks = this.borrowedBooks
    user
  }
}

class LibrarianBuilder(var librarySystem: LibrarySystem) extends LibraryUserBuilder[LibrarianBuilder, Librarian] {

  var system = librarySystem

  def withLibrarySystem(libSystem: LibrarySystem): LibrarianBuilder = {
    this.librarySystem = libSystem
    this
  }

  def build: Librarian = {
    val librarian = new Librarian(this.name, this.email, this.password)
    librarian.borrowedBooks = this.borrowedBooks
    librarian
  }
}
