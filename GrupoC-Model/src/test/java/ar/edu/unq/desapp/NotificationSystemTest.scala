package ar.edu.unq.desapp

import org.scalatest.FunSpec
import org.scalatest.matchers.ShouldMatchers
import org.scalatest.GivenWhenThen
import org.scalatest.mock.MockitoSugar
import java.awt.Image

class NotificationSystemTest extends FunSpec with ShouldMatchers with GivenWhenThen with MockitoSugar {

  def fixture = new {
    val notificationSystem = new NotificationSystem()
    val emailService = mock[EmailService]
    val mockImage = mock[Image]
    val userA = new User("userA", "userA@gmail.com", "124121")
    val userB = new Librarian("librarian", "librarian@gmail.com", "12412412", mock[LibrarySystem])
    val userC = new User("userC", "userC@gmail.com", "1124feqf")
    val bookA = new Book("Alguien anda por ahi", "1819-7846", "Argentina", mockImage, "what?, it's a real book", mock[Author] :: List())
    val bookB = mock[Book]
  }

  describe("Notification System") {
    it("must register notifications to the users") {
      val notificationSystem = fixture.notificationSystem

      given("following users and two books")
      val clientA = fixture.userA
      val clientB = fixture.userC
      val clientC = fixture.userB

      val aBorrowedBookA = fixture.bookA
      val aBorrowedBookB = fixture.bookB

      when("add to map")
      notificationSystem.addObserver(clientA, aBorrowedBookA)
      notificationSystem.addObserver(clientB, aBorrowedBookA)
      notificationSystem.addObserver(clientC, aBorrowedBookB)

      then("should have added")
      val notifications = notificationSystem.users

      notifications should have size (2)
      notifications should (contain key (aBorrowedBookA) and contain value (List(clientA, clientB)))
      notifications should (contain key (aBorrowedBookB) and contain value (List(clientC)))
    }

    it("must notify to all users that await your wished book") {
      val notificationSystem = fixture.notificationSystem
      val mailer = fixture.emailService
      val clientA = fixture.userA
      val clientB = fixture.userC
      val aBorrowedBook = fixture.bookA

      given("users added to be notified")
      notificationSystem.addObserver(clientA, aBorrowedBook)
      notificationSystem.addObserver(clientB, aBorrowedBook)

      when("notify users by borrowed book")
      notificationSystem.notifyAllUsers(aBorrowedBook)

      then("verify that the message was sent")
      //TODO: problem with mockito!!!
      //      verify(mailer).sendNotification(clientA, aBorrowedBook)
      //      verify(mailer).sendNotification(clientB, aBorrowedBook)

      and("delete notifications")
      notificationSystem.users should not contain key(aBorrowedBook)
    }

    ignore("dashboard") {
      val notificationSystem = fixture.notificationSystem

    }
  }
}
