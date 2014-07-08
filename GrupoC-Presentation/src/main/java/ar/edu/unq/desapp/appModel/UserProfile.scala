package ar.edu.unq.desapp.appModel

import java.util

import ar.edu.unq.desapp.model.bean.User

class UserProfile(user: User) extends Serializable {

  def updateProfile() {
    //TODO
  }

  def getUsername: String = {
    this.user.username
  }

  def getEmail: String = {
    this.user.email
  }

  def kindsBook: util.ArrayList[String] = {
    new util.ArrayList[String]()
  }
}
