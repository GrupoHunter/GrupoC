package tets

import org.scalatest.mock.MockitoSugar
import org.scalatest.FunSpec
import org.scalatest.matchers.ShouldMatchers
import java.util.Date
import java.sql.Time
import org.scalatest.GivenWhenThen

class LoanManagementTest extends FunSpec with ShouldMatchers with GivenWhenThen with MockitoSugar {

  def fixture = new {
    val loanManagement = new LoanManagement()
  }

  describe("Loan Management") {
    it("should record loans in case that the books is available") {
      val loanManagement = fixture.loanManagement

      given("following users and books")
      val userA = mock[User]
      val userB = mock[User]

      val bookA = mock[Book]
      val bookB = mock[Book]
      val bookC = mock[Book]

      when("each user requests a book")
      loanManagement.recordLoan(userA, bookA)
      loanManagement.recordLoan(userB, bookB)
      loanManagement.recordLoan(UserB, bookC)

      then("you get loaded the users with your loans books")
      loanManagement.borrowedBooks should have size (3)
      loanManagement.borrowedBooks should contain((userA, bookA))
      loanManagement.borrowedBooks should contain((userB, bookB))
      loanManagement.borrowedBooks should contain((userB, bookC))

      //TODO: See you have to also save the time of the loan and repayment
    }

    it("should reserve book in case that it is busy") {
      val loanManagement = fixture.loanManagement

      given("following 2 users, 3 busy book and maximum allowable reserve")
      loanManagement.setAllowableReserve(2)
      val userA = mock[User] //TODO: replace mock to object User
      val userB = mock[User]
      val busyBookA = mock[Book]
      val busyBookB = mock[Book]
      val busyBookC = mock[Book]

      when("user reserve the book")
      loanManagement.reserveBook(userA, busyBookA)
      loanManagement.reserveBook(userA, busyBookB)
      loanManagement.reserveBook(userA, busyBookC)
      loanManagement.reserveBook(userB, busyBookA)
      loanManagement.reserveBook(userB, busyBookC)

      then("Loan Management should save reserve of users")
      loanManagement.reservedBooks should have size (2)
      loanManagement.reservedBooks should (key(userA.username) and contain value (busyBookA :: busyBookB :: List()))
      loanManagement.reservedBooks should (key(userB.username) and contain value (busyBookC :: List()))
    }

    it("should sign up the users to notification list") {
      val loanManagement = fixture.loanManagement

      given("following users and a busy book")
      val userA = mock[User]
      val userB = mock[User]

      val busyBook = mock[Book]

      when("users want to sign up to notification list")
      loanManagement.signUpNotification(userA, busyBook)
      loanManagement.signUpNotification(userB, busyBook)

      then("you get loaded the users")
      loanManagement.usersToNotification should have size (1)
      loanManagement.usersToNotification should (key(busyBook) and contain value (userA :: userB :: List()))
    }
    
    ignore("handle the notifications of available books"){
      val loanManagement = fixture.loanManagement
      
    }
  }
}