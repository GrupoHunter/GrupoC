package ar.edu.unq.desapp.model

import ar.edu.unq.desapp.utils.builder.Builder
import org.scalatest.{FunSpec, GivenWhenThen, Matchers}
import org.specs2.mock.Mockito

class LibraryUsersTest extends FunSpec with Matchers with GivenWhenThen with Mockito with Builder

//{

//  describe("Librarian") {
//    it("should add books to the system") {
//      val librarian = aLibrarian.build
//
//      Given("following two books")
//      val bookA =
//        aBook
//          .withTitle("Title")
//          .withIsbn("123-749")
//          .withEditorial("Something")
//          .withDescription("Just to test it")
//          .withAuthors(mock[Author] :: Nil).build
//
//      val bookB =
//        aBook
//          .withTitle("The True")
//          .withIsbn("987-789")
//          .withEditorial("What's up?")
//          .withDescription("Yes, i haven't more imagination")
//          .withAuthors(mock[Author] :: Nil)
//          .withAmount(3)
//          .build
//
//      When("the librarian add the books to system")
//      librarian addBookToSystem bookA
//      librarian addBookToSystem bookB
//
//      Then("the books should be inside to system")
//      val booksToSystem: List[Book] = librarian.librarySystem.books
//
//      booksToSystem should have size 2
//      booksToSystem(0) should have('title(bookB.title))
//      booksToSystem(0) should have('isbn(bookB.isbn))
//      booksToSystem(0) should have('editorial(bookB.editorial))
//      booksToSystem(0) should have('description(bookB.description))
//      booksToSystem(0) should have('authors(bookB.authors))
//      booksToSystem(0).amount should be(3)
//
//      booksToSystem(1) should have('title(bookA.title))
//      booksToSystem(1) should have('isbn(bookA.isbn))
//      booksToSystem(1) should have('editorial(bookA.editorial))
//      booksToSystem(1) should have('description(bookA.description))
//      booksToSystem(1) should have('authors(bookA.authors))
//      booksToSystem(1).amount should be(1)
//    }
//
//    ignore("should modify a book to the system") {
//      val librarian = aLibrarian.build
//
//      Given("that the system has added one book")
//      val bookA = aBook.build
//      librarian addBookToSystem bookA
//
//      When("will go to modify a book")
//      val bookToModify = librarian.librarySystem.books(0)
//      val bookModified =
//        aBook
//          .withTitle("FullMoon")
//          .withIsbn("156-5746")
//          .withEditorial("Sonata Arctica")
//          .withDescription("I was listening Sonata Arctica")
//          .build
//
//      librarian.modifyBookFromTheSystem(bookToModify, bookModified)
//
//      Then("must have the modified book")
//      val books = librarian.librarySystem.books(0)
//
//      books should have('title(bookModified.title))
//      books should have('isbn(bookModified.isbn))
//      books should have('editorial(bookModified.editorial))
//      books should have('description(bookModified.description))
//    }
//
//    ignore("should delete a book") {
//      val librarySystem = new LibrarySystem(mock[LoanConfiguration])
//      val librarian =
//        aLibrarian
//          .withLibrarySystem(librarySystem).build
//
//      Given("following books")
//      val bookA =
//        aBook
//          .withIsbn("1234-isbn")
//          .build
//      val bookB =
//        aBook
//          .withTitle("Some title")
//          .withIsbn("bookB")
//          .build
//      val bookC =
//        aBook
//          .withTitle("FullMoon")
//          .withIsbn("bookC")
//          .build
//
//      librarySystem.books = bookA :: bookB :: bookC :: Nil
//
//      When("you want delete a book")
//      val bookToRemove = librarySystem.books(0)
//      librarian deleteBookFromTheSystem (bookA)
//
//      Then("should have the books")
//      val books = librarySystem.books
//
//      books should have size 2
//      books(0) should have('title(bookB.title))
//      books(1) should have('title(bookC.title))
//    }
//
//    it("configure amount to allow loan") {
//      val loanConf = new LoanConfiguration
//      val loanManagement = new LoanManagement(mock[NotificationSystem], loanConf)
//      val librarySystem = new LibrarySystem(loanConf)
//      val librarian = aLibrarian.withLibrarySystem(librarySystem).build
//      val amountReserver = 5
//
//      Given("some user and books inside the system")
//      val user = anUser.build
//      librarySystem.users = user :: Nil
//      librarySystem.books =
//        aBook.build :: aBook.build :: aBook.build :: aBook.build :: aBook.build :: Nil
//
//      When("set max reserves amount to 5 and two users reserve 5 books each")
////      librarian.configureMaxReservesAmount(amountReserver)
//      librarySystem.books foreach (book => loanManagement.reserveBook(user, book))
//
//      Then("all users should have 5 books")
//      loanConf.amountAllowLoan should be(5)
//      loanManagement.reservedBooks(user) should have size 5
//    }
//
//    it("configure max date to loan") {
//      val loanConf = new LoanConfiguration
//      val loanManagement = new LoanManagement(mock[NotificationSystem], loanConf)
//      val librarySystem = new LibrarySystem(loanConf)
//      val librarian = aLibrarian.withLibrarySystem(librarySystem).build
//
//      val user = anUser.build
//      val book = aBook.build
//
//      Given("an amount days to allow loan")
//      val amountDays = 6
//      val dateToday = new DateTime().plusDays(amountDays)
//
//      When("librarian configure max loan date")
////      librarian.configureMaxDaysOfLoan(amountDays)
//
//      Then("if an user will reserve some book")
//      loanManagement.recordLoan(user, book)
//      val borrowedBook = loanManagement.borrowedBooks(0)
//
//      loanConf.maxDaysOfLoan should be(amountDays)
//      borrowedBook.refundDate.getDayOfYear should be(dateToday.getDayOfYear)
//    }
//
//    it("register users") {
//      val librarySystem = new LibrarySystem(mock[LoanConfiguration])
//      val librarian = aLibrarian.withLibrarySystem(librarySystem).build
//
//      When("librarian is going to register two users")
////      librarian.registerUser("Pep", "pep@gmail.com", "10iifoiwe", "User")
////      librarian.registerUser("Erudito", "erudito@gmail.com", "9130u019", "Librarian")
//
//      Then("the library will register the users")
//      librarySystem.users should have size 2
//      librarySystem.users(1) should have('username("Pep"))
//      librarySystem.users(0) should have('username("Erudito"))
//    }
//  }
//
//  describe("User") {
//    it("should borrow two available book") {
//      val user = anUser.build
//
//      Given("an available book")
//      val bookA =
//        aBook
//          .withTitle("Title")
//          .build
//      val bookB =
//        aBook
//          .withTitle("Lalala")
//          .build
//
//      When("client wanna borrow a book")
//      user.borrowBook(bookA)
//      user.borrowBook(bookB)
//
//      Then("should have the books")
//
//      user.borrowedBooks should have size 2
//      user borrowedBooks 0 should have('title(bookB.title))
//      user borrowedBooks 1 should have('title(bookA.title))
//    }
//
//    it("should be able to return a book") {
//      val user = anUser.build
//
//      Given("the following books that user have loaned")
//      val bookA = aBook.build
//      val bookB = aBook.build
//      val bookC = aBook.build
//      val currentBorrowedBooks = bookA :: bookB :: bookC :: Nil
//
//      user.borrowedBooks = currentBorrowedBooks
//
//      When("user want return a book")
//      user.returnBook(bookB)
//
//      Then("must have only the books")
//      user.borrowedBooks should have size 2
//      user.borrowedBooks should contain(bookA)
//      user.borrowedBooks should contain(bookC)
//    }
//
//    it("comment a book") {
//      val user = anUser.build
//      val book = aBook.build
//
//      Given("the following comment")
//      val commentA = "This is a comment, poor, but it is"
//
//      When("user comments the book")
//      user.commentBook(book, commentA)
//
//      Then("the comment must be loaded in the book")
//      book.comment(0) should have('anUser(user), 'comment("This is a comment, poor, but it is"))
//    }
//  }
//}
