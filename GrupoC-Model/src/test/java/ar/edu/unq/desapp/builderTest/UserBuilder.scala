package ar.edu.unq.desapp.builderTest

import ar.edu.unq.desapp._

class UserBuilder {

  var name: String = "no name"
  var password: String = "no pass"
  var email: String = "no email"
  var borrowedBooks: List[Book] = List()

  def anUser: UserBuilder = {
    new UserBuilder
  }

  def withName(aName: String): UserBuilder = {
    name = aName
    this
  }

  def withPassword(aPassword: String): UserBuilder = {
    password = aPassword
    this
  }

  def withEmail(anEmail: String): UserBuilder = {
    email = anEmail
    this
  }

  def withBorrowedBooks(books: List[Book]): UserBuilder = {
    borrowedBooks = books
    this
  }
  
  def build: User = {
    val user = new User(name, password, email)
    user.borrowedBooks = borrowedBooks
    user
  }

}
