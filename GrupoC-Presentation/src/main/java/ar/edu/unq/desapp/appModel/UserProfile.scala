package ar.edu.unq.desapp.appModel

import java.util

import ar.edu.unq.desapp.model.bean.{Book, User}
import ar.edu.unq.desapp.services.bean.UserService

class UserProfile(user: User, userService: UserService) extends Serializable {

  var user_username: String = _
  var user_email: String = _
  var user_password: String = _

  def showUserData() {
    user_username = user.getUsername
    user_email = user.getEmail
    user_password = ""
  }

  def updateProfile() {
    user.setUsername(user_username)
    user.setEmail(user_email)
    user.setPassword(user_password)

    userService.update(user)
  }

  def getMyBorrowedBooks: java.util.List[Book] = {
    val myUser = userService.findByUsername(user.username)
    myUser.borrowedBooks
  }

  def kindsBook: util.ArrayList[String] = {
    new util.ArrayList[String]()
  }
}
