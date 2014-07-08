package ar.edu.unq.desapp.view.model

import ar.edu.unq.desapp.view.security.ScalaBaseProjectSession
import org.apache.wicket.markup.html.form.{Form, PasswordTextField, RequiredTextField}
import org.apache.wicket.markup.html.link.BookmarkablePageLink
import org.apache.wicket.markup.html.panel.FeedbackPanel
import org.apache.wicket.request.mapper.parameter.PageParameters

class LoginPage(parameters: PageParameters) extends HeadBlankPage {

  add(new LoginForm("loginform"))

  //TODO - Refactor
  def this() {
    this(null)
  }

  private class LoginForm(id: String) extends Form(id) {

    private var username: String = _

    private var password: String = _

    val signInPage: SignInPage = new SignInPage

//    setModel(new CompoundPropertyModel(this))

    add(new RequiredTextField("username"))

    add(new PasswordTextField("password"))

    add(new FeedbackPanel("feedback"))

//    add(new Button("register") { override def onSubmit() { setResponsePage(signInPage) }})
    add(new BookmarkablePageLink[SignInPage]("register", classOf[SignInPage]))

    protected override def onSubmit() {
      val session = ScalaBaseProjectSession.getSession()
      if (session.signIn(username, password)) {
        LoginForm.this.setResponsePage(classOf[HomePage])
      } else {
        error(getString("login.failed"))
      }
    }
  }
}
