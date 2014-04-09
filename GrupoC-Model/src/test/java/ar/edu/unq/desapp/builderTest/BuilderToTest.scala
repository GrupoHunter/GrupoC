package ar.edu.unq.desapp.builderTest

import ar.edu.unq.desapp._

abstract class BuilderToTest[T] {

  def build: T //generic build
}

//Here define the builders to share with others test
trait Builder {
  val anUser = new UserIdentity()
  val aLibrarian = new LibraryBuilder(new LibrarySystem)
  val aBook = new BookBuilder()
}
