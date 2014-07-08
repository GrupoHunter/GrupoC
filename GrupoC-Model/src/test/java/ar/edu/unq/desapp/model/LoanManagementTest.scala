package ar.edu.unq.desapp.model

import org.joda.time.DateTime
import org.scalatest.FunSpec
import org.scalatest.GivenWhenThen
import org.scalatest.matchers.ShouldMatchers
import org.specs2.mock.Mockito
import ar.edu.unq.desapp.utils.builder.Builder
import ar.edu.unq.desapp.model.management.LoanManagement
import ar.edu.unq.desapp.model.bean.LoanConfiguration
import ar.edu.unq.desapp.model.mailer.NotificationSystem


class LoanManagementTest extends FunSpec with ShouldMatchers with GivenWhenThen with Mockito with Builder {

  describe("Loan Management") {
    it("should record borrows in case that the books is available") {
      val loanManagement = aLoanManagement
      val date = new DateTime

      val userA = anUser.build
      val userB = anUser.build

      given("following users and books")

      val bookA = aBook.build
      val bookB = aBook.build
      val bookC = aBook.build

      when("each user requests a book")
      loanManagement.recordLoan(userA, bookA)
      loanManagement.recordLoan(userB, bookB)
      loanManagement.recordLoan(userB, bookC)

      then("you get loaded the users with your loans books")
      val borrowedBooks = loanManagement.borrowedBooks

      borrowedBooks should have size (3)
      borrowedBooks(2) should have('anUser(userA))
      borrowedBooks(2) should have('aBook(bookA))

      borrowedBooks(1) should have('anUser(userB))
      borrowedBooks(1) should have('aBook(bookB))

      borrowedBooks(0) should have('anUser(userB))
      borrowedBooks(0) should have('aBook(bookC))

    }

    it("should be loaded date of loan") {
      val loanManagement = new LoanManagement(mock[NotificationSystem], new LoanConfiguration)
      val date = new DateTime
      val endDate = date.plusDays(4)

      given("an user wanna load one book")
      val user = anUser.build
      val book = aBook.build

      when("user request the book")
      loanManagement.recordLoan(user, book)

      then("you get loaded date today and 4 day after")
      val dayOfLoan = loanManagement.borrowedBooks(0).dateOfLoan
      val refundDate = loanManagement.borrowedBooks(0).refundDate

      dayOfLoan.getDayOfYear() should be(date.getDayOfYear())
      refundDate.getDayOfYear() should be(endDate.getDayOfYear())
    }

    it("shouldn't record borrow since there was not book") {
      val loanManagement = new LoanManagement(mock[NotificationSystem], new LoanConfiguration)

      given("two users and one book")
      val userA = anUser.build
      val userB = anUser.build
      val book = aBook.withAmount(1).build

      when("both users want to record same book")
      loanManagement.recordLoan(userA, book)
      loanManagement.recordLoan(userB, book)

      then("you get loaded the userA but no userB")
      val borrowedBooks = loanManagement.borrowedBooks

      borrowedBooks(0) should have('anUser(userA))
      borrowedBooks(0) should have('aBook(book))
    }

    it("should reserve book in case that it is busy") {
      val loanManagement = aLoanManagement

      given("following 2 users, 3 busy book and maximum allowable reserve 3")
      val userA = anUser.withEmail("userA@library.com").build
      val userB = anUser.withEmail("userB@library.com").build
      val busyBookA = aBook.build
      val busyBookB = aBook.build
      val busyBookC = aBook.build

      when("user reserve the book")
      loanManagement.reserveBook(userA, busyBookA)
      loanManagement.reserveBook(userB, busyBookA)
      loanManagement.reserveBook(userA, busyBookB)
      loanManagement.reserveBook(userB, busyBookC)
      loanManagement.reserveBook(userA, busyBookC)

      then("Loan Management should save reserve of users")
      loanManagement.reservedBooks should have size (2)
      loanManagement.reservedBooks should (contain key (userA) and contain value (List(busyBookB, busyBookA)))
      loanManagement.reservedBooks should (contain key (userB) and contain value (List(busyBookC)))
    }

    it("shouldn't allow an user reserve more than allowed") {
      val loanConf = new LoanConfiguration
      loanConf.amountAllowLoan = 2
      val loanManagement = new LoanManagement(mock[NotificationSystem], loanConf)

      val user = anUser.build
      val busyBookA = aBook.build
      val busyBookB = aBook.build
      val busyBookC = aBook.build

      when("user reserve 3 books")
      loanManagement.reserveBook(user, busyBookA)
      loanManagement.reserveBook(user, busyBookC)
      loanManagement.reserveBook(user, busyBookB)

      then("the books busyBookA and busyBookC is reserved")
      loanManagement.reservedBooks should contain key (user)
      loanManagement.reservedBooks should not contain value(busyBookB)
    }

    it("should sign up the users to notification list") {
      val loanManagement = new LoanManagement(mock[NotificationSystem], mock[LoanConfiguration])

      given("following users and a busy book")
      val userA = anUser.build
      val userB = anUser.build

      val busyBook = aBook.build

      when("users want to sign up to notification list")
      loanManagement.signUpNotification(userA, busyBook)
      loanManagement.signUpNotification(userB, busyBook)

      then("you get loaded the users")
      there was one(loanManagement.notificationSystem).addObserver(userA, busyBook)
      there was one(loanManagement.notificationSystem).addObserver(userB, busyBook)
    }

    it("handle the notifications of available books") {
      val loanManagement = new LoanManagement(mock[NotificationSystem], mock[LoanConfiguration])

      given("following user who borrow a book")
      val userWithBook = anUser.build
      val borrowedBook = aBook.build
      loanManagement.recordLoan(userWithBook, borrowedBook)

      when("other user who demand reserve this book and returned")
      val userAToNotify = anUser.build
      loanManagement.reserveBook(userAToNotify, borrowedBook)

      loanManagement.deleteLoan(userWithBook, borrowedBook)

      then("it must send notice who have reserved")
      there was one(loanManagement.notificationSystem).notifyUserOfAvailableBook(userAToNotify, borrowedBook)
    }

    it("handle the notifications of available books when nobody have reserved the book"){
      val loanManagement = new LoanManagement(mock[NotificationSystem], mock[LoanConfiguration])

      given("following user who borrow a book")
      val userWithBook = anUser.build
      val borrowedBook = aBook.build
      loanManagement.recordLoan(userWithBook, borrowedBook)

      when("the user return the book")
      loanManagement.deleteLoan(userWithBook, borrowedBook)

      then("it must not send somebody notification")
      there was no(loanManagement.notificationSystem).notifyUserOfAvailableBook(anUser.build, aBook.build)
    }
  }
}
