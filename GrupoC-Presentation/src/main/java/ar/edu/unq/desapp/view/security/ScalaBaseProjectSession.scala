package ar.edu.unq.desapp.view.security

import java.io.Serializable

import ar.edu.unq.desapp.model.bean.User
import org.apache.wicket.Session
import org.apache.wicket.authroles.authentication.AuthenticatedWebSession
import org.apache.wicket.authroles.authorization.strategies.role.Roles
import org.apache.wicket.injection.Injector
import org.apache.wicket.request.Request
import org.apache.wicket.spring.injection.annot.SpringBean
import org.springframework.security.authentication.{AuthenticationManager, UsernamePasswordAuthenticationToken}
import org.springframework.security.core.{Authentication, AuthenticationException}
import org.springframework.security.core.context.SecurityContextHolder

import scala.beans.BeanProperty
import scala.collection.JavaConversions._

object ScalaBaseProjectSession {

  var mySession: ScalaBaseProjectSession = _

  def getSession(): ScalaBaseProjectSession = {
    this.mySession = Session.get.asInstanceOf[ScalaBaseProjectSession]
    mySession
  }

  def getCurrentUser: User = {
    mySession.userSession
  }
}

class ScalaBaseProjectSession(request: Request) extends AuthenticatedWebSession(request) {

  @SpringBean(name = "authenticationManager")
  private var authenticationManager: AuthenticationManager = _

  @BeanProperty
  var userSession: User = _

  this.injectDependencies()

  def add(key: String, value: Serializable) {
    setAttribute(key, value)
  }

  def remove(key: String) {
    removeAttribute(key)
  }

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
