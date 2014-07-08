package ar.edu.unq.desapp.view.model

import org.apache.wicket.markup.html.form._
import org.apache.wicket.markup.html.panel.FeedbackPanel

class SignInPage extends HeadBlankPage {

//  private val signinAppModel = new SignIn

  override def onInitialize() {
    super.onInitialize()

//    val signinForm = new Form[SignIn]("signinForm", new CompoundPropertyModel(this.signinAppModel))

    addFields()
    addActions()
    add()
  }

  private def addFields(){
    add(new FeedbackPanel("feedback"))
    add(new RequiredTextField("username"))
    add(new PasswordTextField("password"))
    add(new EmailTextField("email"))
  }

  private def addActions(){
    add(new Button("signin") { def onClick() { signIn() }})
  }

  private def signIn() {
    //TODO
  }
}
