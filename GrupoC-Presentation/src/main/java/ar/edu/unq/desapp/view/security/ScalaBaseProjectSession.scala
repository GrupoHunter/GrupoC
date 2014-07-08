package ar.edu.unq.desapp.view.security

import java.io.Serializable
import org.apache.wicket.Session
import org.apache.wicket.authroles.authentication.AuthenticatedWebSession
import org.apache.wicket.authroles.authorization.strategies.role.Roles
import org.apache.wicket.injection.Injector
import org.apache.wicket.request.Request
import org.apache.wicket.spring.injection.annot.SpringBean
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.security.core.context.SecurityContextHolder
import scala.collection.JavaConversions._

object ScalaBaseProjectSession {

  def getSession(): ScalaBaseProjectSession = {
    Session.get.asInstanceOf[ScalaBaseProjectSession]
  }
}

@SerialVersionUID(1L)
class ScalaBaseProjectSession(request: Request) extends AuthenticatedWebSession(request) {

  @SpringBean(name = "authenticationManager")
  private var authenticationManager: AuthenticationManager = _

  injectDependencies()

  def add(key: String, value: Serializable) {
    setAttribute(key, value)
  }

  def remove(key: String) {
    removeAttribute(key)
  }

//  def get(key: String): AnyRef = getAttribute(key)

  private def injectDependencies() {
    Injector.get.inject(this)
  }

  override def authenticate(username: String, password: String): Boolean = {
    var authenticated = false
    try {
      val authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password))
      SecurityContextHolder.getContext.setAuthentication(authentication)
      authenticated = authentication.isAuthenticated
    } catch {
      case e: AuthenticationException => authenticated = false
    }
    authenticated
  }

  override def getRoles(): Roles = {
    val roles = new Roles()
    getRolesIfSignedIn(roles)
    roles
  }

  private def getRolesIfSignedIn(roles: Roles) {
    if (isSignedIn) {
      val authentication = SecurityContextHolder.getContext.getAuthentication
      addRolesFromAuthentication(roles, authentication)
    }
  }

  private def addRolesFromAuthentication(roles: Roles, authentication: Authentication) {
    for (authority <- authentication.getAuthorities) {
      roles.add(authority.getAuthority)
    }
  }
}
