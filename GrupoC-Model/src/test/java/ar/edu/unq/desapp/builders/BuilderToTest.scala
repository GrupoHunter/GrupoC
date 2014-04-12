package ar.edu.unq.desapp.builders

import ar.edu.unq.desapp.LibrarySystem

abstract class BuilderToTest[T] {

  def build: T //generic build
}

//Here define the builders to share with others test
trait Builder {
  val anUser = new UserIdentity
  val aLibrarian = new LibrarianBuilder(new LibrarySystem)
  val aBook = new BookBuilder
}
