package ar.edu.unq.desapp

import org.scalatest.FunSpec
import org.scalatest.mock.MockitoSugar
import org.scalatest.matchers.ShouldMatchers
import org.scalatest.GivenWhenThen
import java.awt.Image
import ar.edu.unq.desapp.builders.Builder

class LibrarySystemTest extends FunSpec with ShouldMatchers with GivenWhenThen with MockitoSugar with Builder{

  describe("Library System") {
    it("should load manually the books to the system") {
      val librarySystem = new LibrarySystem

      given("the following books")
      val bookA = aBook.build
      val bookB = aBook.build
      val bookC = aBook.build

      when("loading the books")
      librarySystem manualBookLoad (bookA)
      librarySystem manualBookLoad (bookB)
      librarySystem manualBookLoad (bookC)

      then("the books should be loaded")
      val booksLoaded = librarySystem books

      booksLoaded should have size (3)
      booksLoaded should contain(bookA)
      booksLoaded should contain(bookB)
      booksLoaded should contain(bookC)
    }

    ignore("should load automatically the books to the system") {
      val librarySystem = new LibrarySystem
      val searcher = new SearchBookSystem

      given("the following data")
      val isbn = "123-749"
      val title = "Title"

      when("searching the books by isbn and title automatically")
      librarySystem automaticBookLoadByIsbn (isbn)
      librarySystem automaticBookLoadByTitle (title)

      then("the books should be found and loaded")
      val bookLoadedByIsbn: List[Book] = searcher searchByIsbn (isbn)
      val bookLoadedByTitle: List[Book] = searcher searchBook (title)

      bookLoadedByIsbn should have size (1)
      bookLoadedByIsbn should contain(isbn)

      bookLoadedByTitle should have size (1)
      bookLoadedByTitle should contain(title)
    }
  }
}
