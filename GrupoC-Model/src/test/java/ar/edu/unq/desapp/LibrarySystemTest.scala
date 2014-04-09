package ar.edu.unq.desapp

import org.scalatest.FunSpec
import org.scalatest.mock.MockitoSugar
import org.scalatest.matchers.ShouldMatchers
import org.scalatest.GivenWhenThen
import java.awt.Image

class LibrarySystemTest extends FunSpec with ShouldMatchers with GivenWhenThen with MockitoSugar {

  def fixture = new {
    val system = new LibrarySystem()
    val searcher = new SearchBookSystem()

    val author1 = new Author("author1")
    val author2 = new Author("author2")
    val author3 = new Author("author3")
    val authors = author1 :: author2 :: author3 :: List()

    //Book A
    val isbnA = "ISBN-1234"
    val titleA = "Title A book"
    val editorialA = "EditorialSarasa"
    val mockImageA = mock[Image]
    val descriptionA = "This is a description"
    val bookA = new Book(titleA, isbnA, editorialA, mockImageA, descriptionA, authors)
    //Book B
    val isbnB = "ISBN-5678"
    val titleB = "Title B book"
    val editorialB = "EditorialSarasaB"
    val mockImageB = mock[Image]
    val descriptionB = "Description of B book"
    val bookB = new Book(titleB, isbnB, editorialB, mockImageB, descriptionB, authors)
    //Book C
    val isbnC = "ISBN-9012"
    val titleC = "Title C book"
    val editorialC = "EditorialSaraC"
    val mockImageC = mock[Image]
    val descriptionC = "Description of C book"
    val bookC = new Book(titleC, isbnC, editorialC, mockImageC, descriptionC, authors)
  }
  
  trait Builder {
    val builder = Nil
  }

  describe("Library System") { 
    it("should load manually the books to the system") {
      val librarySystem = fixture.system
      given("the following books")
      val bookA = fixture.bookA
      val bookB = fixture.bookB
      val bookC = fixture.bookC

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
      val librarySystem = fixture.system

      given("the following data")
      val isbn = fixture.isbnA
      val title = fixture.titleA

      when("searching the books by isbn and title automatically")
      librarySystem automaticBookLoadByIsbn (isbn)
      librarySystem automaticBookLoadByTitle (title)

      then("the books should be found and loaded")
      val bookLoadedByIsbn: List[Book] = fixture.searcher searchByIsbn (isbn)
      val bookLoadedByTitle: List[Book] = fixture.searcher searchBook (title)

      bookLoadedByIsbn should have size (1)
      bookLoadedByIsbn should contain(isbn)

      bookLoadedByTitle should have size (1)
      bookLoadedByTitle should contain(title)
    }
  }
}
