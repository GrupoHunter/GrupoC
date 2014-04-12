package ar.edu.unq.desapp

import org.scalatest.FunSpec
import org.scalatest.matchers.ShouldMatchers
import org.scalatest.GivenWhenThen
import org.scalatest.mock.MockitoSugar
import java.awt.Image
import ar.edu.unq.desapp.builders.Builder

class NotificationSystemTest extends FunSpec with ShouldMatchers with GivenWhenThen with MockitoSugar with Builder {

  describe("Notification System") {
    ignore("should register notifications to the users") {
      val notificationSystem = new NotificationSystem

      given("the following users and two books")
      val userA = anUser.withName("Joseph").withEmail("joseph@email.com").build
      val userB = anUser.withName("Pepito").withEmail("pepito1@email.com").build
      val userC = aLibrarian.withName("Edurito").withEmail("edurito@email.com").build

      val aBorrowedBookA = aBook.build
      val aBorrowedBookB = aBook.build

      when("add to map")
      notificationSystem.addObserver(userA, aBorrowedBookA)
      notificationSystem.addObserver(userB, aBorrowedBookA)
      notificationSystem.addObserver(userC, aBorrowedBookB)

      then("should have added")
      val notifications = notificationSystem.users

      notifications should have size (2)
      notifications should (contain key (aBorrowedBookA) and contain value (List(userA, userB)))
      notifications should (contain key (aBorrowedBookB) and contain value (List(userC)))
    }

    ignore("must notify to all users that await your wished book") {
      val notificationSystem = new NotificationSystem
      val mailer = new EmailService
      val userA = anUser.build
      val userB = anUser.build
      val aBorrowedBook = aBook.build

      given("users added to be notified")
      notificationSystem.addObserver(userA, aBorrowedBook)
      notificationSystem.addObserver(userB, aBorrowedBook)

      when("notify users by borrowed book")
      notificationSystem.notifyAllUsers(aBorrowedBook)

      then("verify that the message was sent")
      //TODO: problem with mockito!!!
// verify(mailer).sendNotification(clientA, aBorrowedBook)
// verify(mailer).sendNotification(clientB, aBorrowedBook)

      and("delete notifications")
      notificationSystem.users should not contain key(aBorrowedBook)
    }

    ignore("dashboard") {
      val notificationSystem = new NotificationSystem

    }
  }
}
