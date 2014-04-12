package ar.edu.unq.desapp

import org.scalatest.FunSpec
import org.scalatest.GivenWhenThen
import org.scalatest.matchers.ShouldMatchers
import org.scalatest.mock.MockitoSugar
import java.awt.Image
import ar.edu.unq.desapp.builders.UserIdentity
import ar.edu.unq.desapp.builders.Builder

class LibraryUsersTest extends FunSpec with ShouldMatchers with GivenWhenThen with MockitoSugar with Builder {

  describe("Librarian") {
    it("should add books to the system") {
      val librarian = aLibrarian.build

      given("following two books")
      //TODO - I think this books should be created
      // with default constructor instead of a builder
      val bookA =
        aBook
          .withTitle("Title")
          .withIsbn("123-749")
          .withEditorial("Something")
          .withDescription("Just to test it")
          .withAuthors(mock[Author] :: Nil).build

      val bookB =
        aBook
          .withTitle("The True")
          .withIsbn("987-789")
          .withEditorial("What's up?")
          .withDescription("Yes, i haven't more imagination")
          .withAuthors(mock[Author] :: Nil)
          .withAmount(3)
          .build

      when("the librarian add the books to system")
      librarian addBookToSystem (bookA)
      librarian addBookToSystem (bookB)

      then("the books should be inside to system")
      val booksToSystem: List[Book] = librarian.librarySystem.books

      booksToSystem should have size (2)
      booksToSystem(0) should have('title(bookB.title))
      booksToSystem(0) should have('isbn(bookB.isbn))
      booksToSystem(0) should have('editorial(bookB.editorial))
      booksToSystem(0) should have('description(bookB.description))
      booksToSystem(0) should have('authors(bookB.authors))
      booksToSystem(0).amount should be(3)

      booksToSystem(1) should have('title(bookA.title))
      booksToSystem(1) should have('isbn(bookA.isbn))
      booksToSystem(1) should have('editorial(bookA.editorial))
      booksToSystem(1) should have('description(bookA.description))
      booksToSystem(1) should have('authors(bookA.authors))
      booksToSystem(1).amount should be(1)

    }

    ignore("should modify a book to the system") {
      val librarian = aLibrarian.build

      given("that the system has added one book")
      val bookA = aBook.build
      librarian addBookToSystem (bookA)

      when("will go to modify a book")
      val bookToModify = librarian.librarySystem.books(0)
      val bookModified =
        aBook
          .withTitle("FullMoon")
          .withIsbn("156-5746")
          .withEditorial("Sonata Arctica")
          .withDescription("I was listening Sonata Arctica")
          .build

      librarian.modifyBookFromTheSystem(bookToModify, bookModified)

      then("must have the modified book")
      val books = librarian.librarySystem.books(0)

      books should have('title(bookModified.title))
      books should have('isbn(bookModified.isbn))
      books should have('editorial(bookModified.editorial))
      books should have('description(bookModified.description))

    }

    it("should delete a book") {
      val librarian = aLibrarian.build

      given("following books")
      val bookA = aBook.build
      val bookB =
        aBook
          .withTitle("Some title")
          .build
      val bookC =
        aBook
          .withTitle("FullMoon")
          .build

      librarian addBookToSystem (bookA)
      librarian addBookToSystem (bookB)
      librarian addBookToSystem (bookC)

      when("you want delete a book")
      val bookToRemove = librarian.librarySystem.books(0)
      librarian deleteBookFromTheSystem (bookToRemove)

      then("should have the books")
      val books = librarian.librarySystem.books

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

  describe("User") {
    it("should borrow two available book") {
      val user = anUser.build

      given("an available book")
      val bookA =
        aBook
        .withTitle("Title")
        .build
      val bookB =
        aBook
        .withTitle("Lalala")
        .build

      when("client wanna borrow a book")
      user.borrowBook(bookA)
      user.borrowBook(bookB)

      then("should have the books")

      user.borrowedBooks should have size (2)
      user borrowedBooks (0) should have('title(bookB.title))
      user borrowedBooks (1) should have('title(bookA.title))
    }

    it("should be able to return a book") {
      val user = anUser.build

      given(" the following books that user have loaned")
      val bookA = aBook.build
      val bookB = aBook.build
      val bookC = aBook.build
      val currentBorrowedBooks = bookA :: bookB :: bookC :: Nil

      user.borrowedBooks = currentBorrowedBooks

      when("user want return a book")
      user.returnBook(bookB)

      then("must have only the books")
      user.borrowedBooks should have size (2)
      user.borrowedBooks should contain((bookA :: bookC :: Nil))
    }

    ignore("comment a book") {
      //TODO: implement comment a book
    }
  }
}
