package ar.edu.unq.desapp

import org.scalatest.FunSpec
import org.scalatest.GivenWhenThen
import org.scalatest.matchers.ShouldMatchers
import org.scalatest.mock.MockitoSugar

class LoanManagementTest extends FunSpec with ShouldMatchers with GivenWhenThen with MockitoSugar {

  def fixture = new {
    val loanManagement = new LoanManagement()
    val userA = new User("userA", "userA@library.com", "568321")
    val userB = new User("userB", "userB@library.com", "as9uj")
    val mockBook = mock[Book]
  }

  describe("Loan Management") {
    ignore("should record borrows in case that the books is available") {
      val loanManagement = fixture.loanManagement

      val userA = fixture.userA
      val userB = fixture.userB

      given("following users and books")

      val bookA = fixture.mockBook
      val bookB = fixture.mockBook
      val bookC = fixture.mockBook

      when("each user requests a book")
      loanManagement.recordLoan(userA, bookA)
      loanManagement.recordLoan(userB, bookB)
      loanManagement.recordLoan(userB, bookC)

      then("you get loaded the users with your loans books")
      loanManagement.borrowedBooks should have size (3)
      loanManagement.borrowedBooks should contain((userA, bookA))
      loanManagement.borrowedBooks should contain((userB, bookB))
      loanManagement.borrowedBooks should contain((userB, bookC))

      //TODO: See you have to also save the time of the loan and repayment
    }

    ignore("should reserve book in case that it is busy") {
      val loanManagement = fixture.loanManagement

      given("following 2 users, 3 busy book and maximum allowable reserve")
      val userA = fixture.userA
      val userB = fixture.userB
      val busyBookA = fixture.mockBook
      val busyBookB = fixture.mockBook
      val busyBookC = fixture.mockBook

      when("user reserve the book")
      loanManagement.reserveBook(userA, busyBookA)
      loanManagement.reserveBook(userA, busyBookB)
      loanManagement.reserveBook(userA, busyBookC)
      loanManagement.reserveBook(userB, busyBookA)
      loanManagement.reserveBook(userB, busyBookC)

      then("Loan Management should save reserve of users")
      loanManagement.reservedBooks should have size (2)
      loanManagement.reservedBooks should (contain key (userA.email) and contain value (List(busyBookA, busyBookB)))
      loanManagement.reservedBooks should (contain key (userB.email) and contain value (List(busyBookC)))
    }

    ignore("should sign up the users to notification list") {
      val loanManagement = fixture.loanManagement

      given("following users and a busy book")
      val userA = fixture.userA
      val userB = fixture.userB

      val busyBook = fixture.mockBook

      when("users want to sign up to notification list")
      loanManagement.signUpNotification(userA, busyBook)
      loanManagement.signUpNotification(userB, busyBook)

      then("you get loaded the users")
      //      loanManagement.usersToNotification should have size (1)
      //      loanManagement.usersToNotification should (key(busyBook) and contain value (userA :: userB :: List()))
    }

    ignore("handle the notifications of available books") {
      val loanManagement = fixture.loanManagement

    }
  }
}
