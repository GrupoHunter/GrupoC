package ar.edu.unq.desapp.model.search

import ar.edu.unq.desapp.model.management.LibrarySystem
import ar.edu.unq.desapp.model.bean.User
import ar.edu.unq.desapp.model.bean.Book

class SearchBookSystem(val system: LibrarySystem) {
  var users: List[User] = Nil

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
