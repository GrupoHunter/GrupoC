package ar.edu.unq.desapp

class NotificationSystem {
  var users: Map[Book, List[User]] = Map()
  var mailer: EmailService = new EmailService

  def notifyAllUsersOfAvailableBook() {

  }

  def notifyDebtorUser(aUser: User) {

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
    users = users-(aBook)
  }
}
