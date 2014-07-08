package ar.edu.unq.desapp.model

import org.scalatest.FunSpec
import org.specs2.mock.Mockito
import org.scalatest.matchers.ShouldMatchers
import org.scalatest.GivenWhenThen
import java.awt.Image
import ar.edu.unq.desapp.utils.builder.Builder
import ar.edu.unq.desapp.model.management.LibrarySystem
import ar.edu.unq.desapp.model.search.SearchBookSystem
import ar.edu.unq.desapp.model.bean.Book
import ar.edu.unq.desapp.model.bean.LoanConfiguration

class SearchBookSystemTest extends FunSpec with ShouldMatchers with GivenWhenThen with Mockito with Builder {

  describe("Search Book System") {
    ignore("should give a top 20 book rank") {
      val librarySystem = new LibrarySystem(mock[LoanConfiguration])
      val searcher = new SearchBookSystem(librarySystem)

      given("the following books")
      val bookA = aBook.build
      val bookB = aBook.build
      val bookC = aBook.build

      librarySystem manualBookLoad (bookA)
      librarySystem manualBookLoad (bookB)
      librarySystem manualBookLoad (bookC)

      when("borrow books")
      //This use users - TODO
      // user borrowBook(bookA) x3 bookB x2 bookC x1...

      then("Sarasa")
      val result: List[Book] = searcher top20borrowed

      result should contain(bookA)
      result should contain(bookB)
      result should contain(bookC)
    }

    ignore("should give a rank with the last books added") {
      val librarySystem = new LibrarySystem(mock[LoanConfiguration])
      val searcher = new SearchBookSystem(librarySystem)

      given("the following books")
      val bookA = aBook.build
      val bookB = aBook.build
      val bookC = aBook.build

      val isbn = "123-749"
      val title = "Title"

      when("added to the library system")
      librarySystem manualBookLoad (bookA)
      librarySystem manualBookLoad (bookB)
      librarySystem manualBookLoad (bookC)

      librarySystem automaticBookLoadByIsbn (isbn)
      librarySystem automaticBookLoadByTitle (title)

      val bookD = searcher searchByIsbn (isbn)
      val bookE = searcher searchBook (title)

      then("rank of last added books")
      val rank = searcher lastBooksAdded

      rank should have size (5)
      rank should contain(bookA)
      rank should contain(bookB)
      rank should contain(bookC)
      rank should contain(bookD)
      rank should contain(bookE)
    }

    ignore("should be able to find with no precise data") {
      val librarySystem = new LibrarySystem(mock[LoanConfiguration])
      val searcher = new SearchBookSystem(librarySystem)

      given("the following data")
      val bookA = aBook.build
      val bookB = aBook.build
      val bookC = aBook.build

      //Search request data
      val someTitle1 = "title"
      val someTitle2 = "A book"
      val someAuthor1 = "name"
      val someAuthor2 = "author"

      when("searching with no precise data")
      val result1 = searcher searchBook (someTitle1)
      val result2 = searcher searchBook (someTitle2)
      val result3 = searcher searchBook (someAuthor1)
      val result4 = searcher searchBook (someAuthor2)

      then("the books should be found")
      result1 should contain('title("some"))
    }
  }
}
