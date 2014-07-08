package ar.edu.unq.desapp.model

import org.scalatest.FunSpec
import org.scalatest.matchers.ShouldMatchers
import org.scalatest.GivenWhenThen
import java.awt.Image
import org.specs2.mock.Mockito
import ar.edu.unq.desapp.model.mailer.NotificationSystem
import ar.edu.unq.desapp.utils.builder.Builder
import ar.edu.unq.desapp.model.mailer.EmailService

class NotificationSystemTest extends FunSpec with ShouldMatchers with GivenWhenThen with Mockito with Builder {

  describe("Notification System") {
    it("should register notifications to the users") {
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
      notifications should (contain key (aBorrowedBookA) and contain value (List(userB, userA)))
      notifications should (contain key (aBorrowedBookB) and contain value (List(userC)))
    }

    it("must notify to all users that await your wished book") {
      val notificationSystem = new NotificationSystem
      notificationSystem.mailer = mock[EmailService]

      val userA = anUser.build
      val userB = anUser.build
      val aBorrowedBook = aBook.build

      given("users added to be notified")
      notificationSystem.addObserver(userA, aBorrowedBook)
      notificationSystem.addObserver(userB, aBorrowedBook)

      when("notify users by borrowed book")
      notificationSystem.notifyAllUsers(aBorrowedBook)

      then("verify that the message was sent")
      there was one(notificationSystem.mailer).sendNotification(userA, aBorrowedBook)
      there was one(notificationSystem.mailer).sendNotification(userB, aBorrowedBook)

      and("delete notifications")
      notificationSystem.users should not contain key(aBorrowedBook)
    }

    ignore("dashboard") {
      val notificationSystem = new NotificationSystem

    }
  }
}
