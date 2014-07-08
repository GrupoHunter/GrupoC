package ar.edu.unq.desapp.services.login

import ar.edu.unq.desapp.model.bean.User
import com.google.common.base.Function
import com.google.common.collect.Collections2
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.GrantedAuthorityImpl
import org.springframework.security.core.userdetails.UserDetails

class UserLogin(var user: User) extends UserDetails {
  override def getAuthorities: java.util.Collection[GrantedAuthority] = {
    Collections2.transform(this.user.rols, new Function[String, GrantedAuthority]() {
      override def apply(role: String): GrantedAuthority = {
        new GrantedAuthorityImpl(role) 
      }
    })
  }
  
  override def isAccountNonExpired: Boolean = {
    true
  }
  
  override def isAccountNonLocked: Boolean = {
    true
  }
  
  override def isCredentialsNonExpired: Boolean = {
    true
  }
  
  override def isEnabled: Boolean = {
    true
  }
  
  override def getPassword: String = {
    this.user.password
  }
  
  override def getUsername: String = {
    this.user.username
  }
}
