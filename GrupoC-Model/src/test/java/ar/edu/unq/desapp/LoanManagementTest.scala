package ar.edu.unq.desapp

import org.scalatest.FunSpec
import org.scalatest.GivenWhenThen
import org.scalatest.matchers.ShouldMatchers
import org.scalatest.mock.MockitoSugar
import ar.edu.unq.desapp.builders.Builder

class LoanManagementTest extends FunSpec with ShouldMatchers with GivenWhenThen with MockitoSugar with Builder{

  describe("Loan Management") {
    it("should record borrows in case that the books is available") {
      val loanManagement = new LoanManagement

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
      loanManagement.borrowedBooks should have size (3)
      loanManagement.borrowedBooks should contain((userA, bookA))
      loanManagement.borrowedBooks should contain((userB, bookB))
      loanManagement.borrowedBooks should contain((userB, bookC))

      //TODO: See you have to also save the time of the loan and repayment
    }

    it("should reserve book in case that it is busy") {
      val loanManagement = new LoanManagement

      given("following 2 users, 3 busy book and maximum allowable reserve")
      val userA = anUser.withEmail("userA@library.com").build
      val userB = anUser.withEmail("userB@library.com").build
      val busyBookA = aBook.build
      val busyBookB = aBook.build
      val busyBookC = aBook.build

      when("user reserve the book")
      loanManagement.reserveBook(userA, busyBookA)
      loanManagement.reserveBook(userB, busyBookA)
      loanManagement.reserveBook(userA, busyBookB)
      loanManagement.reserveBook(userA, busyBookC)
      loanManagement.reserveBook(userB, busyBookC)

      then("Loan Management should save reserve of users")
      loanManagement.reservedBooks should have size (2)
      loanManagement.reservedBooks should (contain key (userA.email) and contain value (List(busyBookA, busyBookB)))
      loanManagement.reservedBooks should (contain key (userB.email) and contain value (List(busyBookC)))
    }

    ignore("should sign up the users to notification list") {
      val loanManagement = new LoanManagement

      given("following users and a busy book")
      val userA = anUser.withName("Pepe").withEmail("pepe@email.com").build
      val userB = anUser.withName("Jose").withEmail("jose@emai.com").build

      val busyBook = aBook.build

      when("users want to sign up to notification list")
      loanManagement.signUpNotification(userA, busyBook)
      loanManagement.signUpNotification(userB, busyBook)

      then("you get loaded the users")
      // loanManagement.usersToNotification should have size (1)
      // loanManagement.usersToNotification should (key(busyBook) and contain value (userA :: userB :: List()))
    }

    ignore("handle the notifications of available books") {
      val loanManagement = new LoanManagement

    }
  }
}
