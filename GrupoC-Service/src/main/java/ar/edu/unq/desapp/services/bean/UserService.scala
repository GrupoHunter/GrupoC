package ar.edu.unq.desapp.services.bean

import ar.edu.unq.desapp.model.bean.User
import ar.edu.unq.desapp.services.GenericService
import ar.edu.unq.desapp.services.login.UserLogin
import org.apache.commons.collections15.{CollectionUtils, Predicate}
import org.springframework.security.core.userdetails.{UserDetails, UserDetailsService, UsernameNotFoundException}
import org.springframework.transaction.annotation.Transactional

import scala.collection.JavaConversions._

class UserService extends GenericService[User] with UserDetailsService {

  override def loadUserByUsername(username: String): UserDetails = {
    val user: User = this.findByUsername(username)
    if (user != null)
      new UserLogin(user)
    else
      throw new UsernameNotFoundException("No user found for username: " + username)
  }

  @Transactional
  def findByUsername(username: String): User = {
    val users: java.util.List[User] = this.repository.findAll
    CollectionUtils.find(users, this.equalsUsername(username))
  }

  private def equalsUsername(username: String): Predicate[User] = {
    new Predicate[User]() {
      override def evaluate(user: User): Boolean = {
        user.getUsername.equals(username)
      }
    }
  }

  @Transactional
  def loadDefaultUsers() {
    val user: User = new User("user", "user@email.com", "user")
    user.setRols(List("ROLE_USER"))
    val admin: User = new User("admin", "admin@email.com", "admin")
    admin.setRols(List("ROLE_USER", "ROLE_ADMIN"))
    this.save(user)
    this.save(admin)
  }
}
