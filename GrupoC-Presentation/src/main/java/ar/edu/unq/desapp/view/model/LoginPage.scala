package ar.edu.unq.desapp.view.model

import ar.edu.unq.desapp.services.bean.UserService
import ar.edu.unq.desapp.view.security.ScalaBaseProjectSession
import org.apache.wicket.markup.html.form.{Form, PasswordTextField, RequiredTextField}
import org.apache.wicket.markup.html.link.BookmarkablePageLink
import org.apache.wicket.markup.html.panel.FeedbackPanel
import org.apache.wicket.model.CompoundPropertyModel
import org.apache.wicket.spring.injection.annot.SpringBean

import scala.beans.BeanProperty

class LoginPage extends HeadBlankPage {

  @BeanProperty @SpringBean(name = "services.user")
  var userService: UserService = _

  add(new LoginForm("loginform"))

  private class LoginForm(id: String) extends Form[LoginObject](id) {

    val loginObject = new LoginObject

    setModel(new CompoundPropertyModel(loginObject))

    add(new RequiredTextField("username"))

    add(new PasswordTextField("password"))

    add(new FeedbackPanel("feedback"))

    add(new BookmarkablePageLink[SignInPage]("register", classOf[SignInPage]))

    override def onSubmit() {
      val session = ScalaBaseProjectSession.getSession()
      if (session.signIn(loginObject.username, loginObject.password)) {
        val userSession = userService.findByUsername(loginObject.username)
        session.setUserSession(userSession)
        setResponsePage(classOf[HomePage])
      } else {
        error(getString("login_failed"))
      }
    }
  }
}

class LoginObject {
  var username: String = _
  var password: String = _
}
