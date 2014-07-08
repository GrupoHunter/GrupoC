package ar.edu.unq.desapp.model

import ar.edu.unq.desapp.model.bean.{Book, LoanConfiguration}
import ar.edu.unq.desapp.model.management.LibrarySystem
import ar.edu.unq.desapp.model.search.SearchBookSystem
import ar.edu.unq.desapp.utils.builder.Builder
import org.scalatest.{FunSpec, GivenWhenThen}
import org.scalatest.matchers.ShouldMatchers
import org.specs2.mock.Mockito

class LibrarySystemTest extends FunSpec with ShouldMatchers with GivenWhenThen with Mockito with Builder {

  describe("Library System") {
    it("should load manually the books to the system") {
      val librarySystem = new LibrarySystem(mock[LoanConfiguration])

      given("the following books")
      val bookA = aBook.build
      val bookB = aBook.build
      val bookC = aBook.build

      when("loading the books")
      librarySystem manualBookLoad bookA
      librarySystem manualBookLoad bookB
      librarySystem manualBookLoad bookC

      then("the books should be loaded")
      val booksLoaded = librarySystem.books

      booksLoaded should have size 3
      booksLoaded should contain(bookA)
      booksLoaded should contain(bookB)
      booksLoaded should contain(bookC)
    }

    ignore("should load automatically the books to the system") {
      val librarySystem = new LibrarySystem(mock[LoanConfiguration])
      val searcher = new SearchBookSystem(librarySystem)

      given("the following data")
      val isbn = "123-749"
      val title = "Title"

      when("searching the books by isbn and title automatically")
      librarySystem automaticBookLoadByIsbn isbn
      librarySystem automaticBookLoadByTitle title

      then("the books should be found and loaded")
      val bookLoadedByIsbn: List[Book] = searcher searchByIsbn isbn
      val bookLoadedByTitle: List[Book] = searcher searchBook title

      bookLoadedByIsbn should have size 1
      bookLoadedByIsbn should contain(isbn)

      bookLoadedByTitle should have size 1
      bookLoadedByTitle should contain(title)
    }

    it("should remove a book from the system") {
      val librarySystem = new LibrarySystem(mock[LoanConfiguration])

      given("the following added books")
      val bookA = aBook.withIsbn("1234-isbn").build
      val bookB = aBook.withIsbn("4363-isbn").build
      val bookC = aBook.withIsbn("3241-isbn").build
      librarySystem.manualBookLoad(bookA)
      librarySystem.manualBookLoad(bookB)
      librarySystem.manualBookLoad(bookC)

      when("removing the same book")
      librarySystem.removeBook("1234-isbn")

      then("the book should not be on the system")
      val books = librarySystem.books

      books should have size 2
      books should not contain bookA
      books should contain(bookB)
      books should contain(bookC)
    }

    it("should be able to change an amount of a book") {
      val librarySystem = new LibrarySystem(mock[LoanConfiguration])

      given("the following added book")
      val bookA = aBook.withIsbn("Isbn-A").build
      librarySystem.manualBookLoad(bookA, 2)

      when("changing amount of a book")
      val newAmount = 3
      librarySystem.changeAmount("Isbn-A", newAmount)

      then("the amount of the book should have changed")
      librarySystem.books should contain(bookA)
      librarySystem.books should have size 1
      librarySystem.books(0) should have('amount(newAmount))
    }

    it("should be able to know if the list of books on the system contain a book") {
      val librarySystem = new LibrarySystem(mock[LoanConfiguration])

      given("the following book added to the system")
      val bookA = aBook.withIsbn("Isbn-lala").build
      librarySystem.manualBookLoad(bookA)

      then("asking if the books on the system contain a book")
      assert(librarySystem.containsBook("Isbn-lala"))
      assert(!librarySystem.containsBook("Isbn-false"))
    }

    it("should be able to get a book by its isbn") {
      val librarySystem = new LibrarySystem(mock[LoanConfiguration])

      given("the following added book on the system")
      val bookA = aBook.withIsbn("Lala-A").build
      librarySystem.manualBookLoad(bookA)

      when("when searching the books isbn")
      val obtainedBook = librarySystem.getBookByIsbn("Lala-A")
      val notObtainedBook = librarySystem.getBookByIsbn("False-isbn")

      then("the obtained book should be correct")
      obtainedBook should be(Some(bookA))
      notObtainedBook should be(None)
    }
  }
}
