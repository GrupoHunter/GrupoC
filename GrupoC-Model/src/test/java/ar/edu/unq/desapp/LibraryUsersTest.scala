package ar.edu.unq.desapp

import org.scalatest.FunSpec
import org.scalatest.GivenWhenThen
import org.scalatest.matchers.ShouldMatchers
import org.scalatest.mock.MockitoSugar
import java.awt.Image

class LibraryUsersTest extends FunSpec with ShouldMatchers with GivenWhenThen with MockitoSugar {

  def fixture = new {
    val librarySystem = new LibrarySystem()
    val librarian = new Librarian("librarian", "libr@rian.com", "1234", librarySystem)
    val client = new User("userName", "client@librarian.com", "4651321")
    val loanManagement = new LoanManagement()

    //BookA
    val titleA = "Title"
    val isbnA = "123-749"
    val editorialA = "Something"
    val imageA = mock[Image]
    val descriptionA = "Just to test it"
    val authorsA = mock[Author] :: List()
    val amountA = 3
    val bookA = new Book(titleA, isbnA, editorialA, imageA, descriptionA, authorsA, amountA)
    //BookB
    val titleB = "The True"
    val isbnB = "987-789"
    val editorialB = "What's up?"
    val imageB = mock[Image]
    val descriptionB = "Yes, i haven't more imagination"
    val authorsB = mock[Author] :: authorsA
    val bookB = new Book(titleB, isbnB, editorialB, imageB, descriptionB, authorsB)
    //BookC
    val titleC = "FullMoon"
    val isbnC = "156-5746"
    val editorialC = "Sonata Artica"
    val imageC = mock[Image]
    val descriptionC = "i was listened Sonata Artica"
    val authorsC = mock[Author] :: List()
    val bookC = new Book(titleC, isbnC, editorialC, imageC, descriptionC, authorsC)
  }

  describe("Librarian") {
    it("should add books to the system") {
      val librarian = fixture.librarian
      val system = fixture.librarySystem

      given("following two books")
      val bookA = fixture.bookA
      val bookB = fixture.bookB

      when("the librarian add the books to system")
      librarian addBookToSystem (bookA)
      librarian addBookToSystem (bookB)

      then("the books should be inside to system")
      val booksToSystem: List[Book] = system.books

      booksToSystem should have size (2)
      booksToSystem(0) should have('title(bookA.title))
      booksToSystem(0) should have('isbn(bookA.isbn))
      booksToSystem(0) should have('editorial(bookA.editorial))
      booksToSystem(0) should have('description(bookA.description))
      booksToSystem(0) should have('authors(bookA.authors))
      booksToSystem(0).amount should be(3)

      booksToSystem(1) should have('title(bookB.title))
      booksToSystem(1) should have('isbn(bookB.isbn))
      booksToSystem(1) should have('editorial(bookB.editorial))
      booksToSystem(1) should have('description(bookB.description))
      booksToSystem(1) should have('authors(bookB.authors))
      booksToSystem(1).amount should be(1)

    }

    it("should modify a book to the system") {
      val librarian = fixture.librarian
      val system = fixture.librarySystem

      given("that the system has added one book")
      val bookA = fixture.bookA
      librarian addBookToSystem (bookA)

      when("will go to modify a book")
      val bookToModify = fixture.librarySystem.books(0)
      val bookModified = fixture.bookC

      librarian.modifyBookFromTheSystem(bookToModify, bookModified)

      then("must have the modified book")
      val books = system.books(0)

      books should have('title(bookModified.title))
      books should have('isbn(bookModified.isbn))
      books should have('editorial(bookModified.editorial))
      books should have('description(bookModified.description))

    }

    it("should delete a book") {
      val librarian = fixture.librarian
      val bookA = fixture.bookA
      val bookB = fixture.bookB
      val bookC = fixture.bookC

      given("following books")
      librarian addBookToSystem (bookA)
      librarian addBookToSystem (bookB)
      librarian addBookToSystem (bookC)

      when("you want delete a book")
      val bookToRemove = fixture.librarySystem.books(0)
      librarian deleteBookFromTheSystem (bookToRemove)

      then("should have the books")
      val books = fixture.librarySystem.books

      books should have size (2)
      books(0) should have('title(bookB.title))
      books(1) should have('title(bookC.title))
    }

    ignore("configure amount to allow loan") {
      //TODO: configure amount to allow loan
    }

    ignore("configure max date to loan") {
      //TODO: configure max date to loan
    }

    ignore("register users") {
      //TODO: register users
    }
  }

  describe("Client") {
    it("should borrow two available book") {
      val client = fixture.client

      given("an available book")
      val bookA = fixture.bookA
      val bookB = fixture.bookB

      when("client wanna borrow a book")
      client.borrowBook(bookA)
      client.borrowBook(bookB)

      then("should have the books")

      client.borrowedBooks should have size (2)
      client borrowedBooks (0) should have('title(bookA.title))
      client borrowedBooks (1) should have('title(bookB.title))
    }

    it("should return a book") {
      val client = fixture.client

      given("following books that client have loaned")
      val bookA = fixture.bookA
      val bookB = fixture.bookB
      val bookC = fixture.bookC
      val currentBorrowedBooks = bookA :: bookB :: bookC :: List()

      client.borrowedBooks = currentBorrowedBooks

      when("client wanna return a book")
      client.returnBook(bookB)

      then("must have the books")
      client.borrowedBooks should have size (2)
      client.borrowedBooks should contain((bookA :: bookC :: List()))
    }

    ignore("comment a book") {
      //TODO: implement comment a book
    }
  }
}
