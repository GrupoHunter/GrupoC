package ar.edu.unq.desapp.model.mailer

import ar.edu.unq.desapp.model.bean.User
import ar.edu.unq.desapp.model.bean.Book

class NotificationSystem {
  var users: Map[Book, List[User]] = Map()
  var mailer: EmailService = new EmailService

  def notifyAllUsersOfAvailableBook() {
    //TODO implement
  }

  def notifyDebtorUser(aUser: User) {
    //TODO implement
  }

  def notifyUserOfAvailableBook(aUser: User, aBook: Book) {
    //TODO we need one email service
    //mailer.sendNotification(user, aBorrowedBook)
  }

  def addObserver(anUser: User, aBook: Book) {
    val notifyUsers: List[User] = users.get(aBook) match {
      case Some(users) => anUser :: users
      case None => anUser :: Nil
    }
    users += (aBook -> notifyUsers)
  }

  def notifyAllUsers(aBook: Book) {
    users.get(aBook) match {
      case Some(users) => users.foreach(user => mailer.sendNotification(user, aBook))
      case None => Nil
    }
    users = users - aBook
  }
}
