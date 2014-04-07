package ar.edu.unq.desapp

import org.scalatest.FunSpec
import org.scalatest.GivenWhenThen
import org.scalatest.matchers.ShouldMatchers
import org.scalatest.mock.MockitoSugar

class LibraryUsersTest extends FunSpec with ShouldMatchers with GivenWhenThen with MockitoSugar {

  def fixture = new {
    val librarySystem = new LibrarySystem()
    val librarian = new Librarian("librarian", "libr@rian.com", "1234", librarySystem)
    val client = new Client("userName", "client@librarian.com", "4651321")
    val loanManagement = new LoanManagement()
  }

  describe("Librarian") {
    it("should add books to the system") {
      val librarian = fixture.librarian

      given("following two books")

      val titleA = "Title"
      val isbnA = "123-749"
      val editorialA = "Something"
      val imageA = mock[Image]
      val descriptionA = "Just to test it"
      val authorsA = mock[Author] :: List()

      val titleB = "The True"
      val isbnB = "987-789"
      val editorialB = "What's up?"
      val imageB = mock[Image]
      val descriptionB = "Yes, i haven't more imagination"
      val authorsB = mock[Author] ::List() ::: authorsA

      when("the librarian add the books to system")
      librarian addBookToSystem(titleA, isbnA, editorialA, imageA, descriptionA, authorsA)
      librarian addBookToSystem(titleA, isbnA, editorialA, imageA, descriptionA, authorsA)
      librarian addBookToSystem(titleB, isbnB, editorialB, imageB, descriptionB, authorsB)

      then("the books should be inside to system")
      val booksToSystem : List[(Book, Int)] = fixture.librarySystem.books

      booksToSystem should have size (2)
      booksToSystem(0)._1 should have('title("Title"))
      booksToSystem(0)._1 should have('isbn("123-749"))
      booksToSystem(0)._1 should have('editorial("Something"))
      booksToSystem(0)._1 should have('description("Just to test it"))
      booksToSystem(0)._1 should have('authors(authorsA))
      booksToSystem(0)._2 should be (2)

      booksToSystem(1)._1 should have('title("The True"))
      booksToSystem(1)._1 should have('isbn("987-789"))
      booksToSystem(1)._1 should have('editorial("What's up?"))
      booksToSystem(1)._1 should have('description("Yes, i haven't more imagination"))
      booksToSystem(1)._1 should have('authors(authorsB))
      booksToSystem(1)._2 should be (1)

    }

    it("should modify a book to the system") {
      val librarian = fixture.librarian

      given("that the system has added one book")
      librarian addBookToSystem("FullMoon", "156-5746", "Sonata Artica", mock[Image], "i was listened Sonata Artica", mock[Author]::List())

      when("will go to modify a book")
      val bookToModify = fixture.librarySystem.books(0)._1
      val bookModified = new Book("Rhythmbox", "156-5746", "Sonata Artica", mock[Image], "yes, i was bored", mock[Author]::List())

      librarian.modifyBookFromTheSystem(bookToModify, bookModified)

      then("must have the modified book")
      val book = fixture.librarySystem.books(0)._1

      book should have('title("Rhythmbox"))
      book should have('isbn("156-5746"))
      book should have('editorial("Sonata Artica"))
      book should have('description("yes, i was bored"))
      
    }

    it("should delete a book") {
      val librarian = fixture.librarian
      
      val authorMobyDick = new Author("Herman Melville", mock[Book]::List())
      val authorAAPA = new Author("Julio Cortazar", mock[Book]::List())

      given("following books")
      librarian addBookToSystem("FullMoon", "156-5746", "Sonata Artica", mock[Image], "i know that it's not book", mock[Author]::List())
      librarian addBookToSystem ("Alguien anda por ahi", "1819-7846", "Argentina", mock[Image], "what?, it's a real book", authorAAPA :: List())
      librarian addBookToSystem ("Moby Dick", "156-5746", "Some Editorial", mock[Image], "cuento", authorMobyDick :: List())

      when("you want delete a book")
      val bookToRemove = fixture.librarySystem.books(0)._1
      librarian deleteBookFromTheSystem(bookToRemove)

      then("should have the books")
      val books = fixture.librarySystem.books

      books should have size (2)
      books(0)._1 should have('title("Alguien anda por ahi"))
      books(1)._1 should have('title("Moby Dick"))
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
      val aBookA = new Book("FullMoon", "156-5746", "Sonata Artica", mock[Image], "i know that it's not book", mock[Author] :: List())
      val aBookB = new Book("Alguien anda por ahi", "1819-7846", "Argentina", mock[Image], "what?, it's a real book", mock[Author] :: List())

      when("client wanna borrow a book")
      client.borrowBook(aBookA)
      client.borrowBook(aBookB)

      then("should have the books")

      client.borrowedBooks should have size(2)
      client borrowedBooks (0) should have('title("FullMoon"))
      client borrowedBooks (1) should have('title("Alguien anda por ahi"))
    }
    
    it("should return a book"){
      val client = fixture.client
      
      given("following books that client have loaned")
      val bookA = new Book("FullMoon", "156-5746", "Sonata Artica", mock[Image], "i know that it's not book", mock[Author] :: List())
      val bookB = new Book("Alguien anda por ahi", "1819-7846", "Argentina", mock[Image], "what?, it's a real book", mock[Author] :: List())
      val bookC = new Book("Rhythmbox", "156-5746", "Sonata Artica", mock[Image], "yes, i was bored", mock[Author]::List())
      val currentBorrowedBooks = bookA :: bookB :: bookC :: List()
      
      client.borrowedBooks = currentBorrowedBooks
      
      when("client wanna return a book")
      client.returnBook(bookB)
      
      then("must have the books")
      client.borrowedBooks should have size (2)
      client.borrowedBooks should contain ((bookA :: bookC :: List())) 
    }

    ignore("comment a book"){
      //TODO: implement comment a book
    }
  }
}