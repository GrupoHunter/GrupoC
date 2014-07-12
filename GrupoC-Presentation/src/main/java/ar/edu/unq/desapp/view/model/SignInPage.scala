package ar.edu.unq.desapp.view.model

import org.apache.wicket.markup.html.form._
import org.apache.wicket.markup.html.panel.FeedbackPanel
import scala.beans.BeanProperty
import org.apache.wicket.spring.injection.annot.SpringBean
import ar.edu.unq.desapp.services.bean.UserService
import ar.edu.unq.desapp.model.bean.User
import org.apache.wicket.model.CompoundPropertyModel

class SignInPage extends HeadBlankPage {

  @BeanProperty @SpringBean(name = "services.user")
  var userService: UserService = _

  private val user: User = new User()

  override def onInitialize() {
    super.onInitialize()
    user.addRole("ROLE_USER")
    val form = new StatelessForm[User]("signinForm", new CompoundPropertyModel(this.user))
    addFields(form)
    addActions(form)
    add(form)
  }

  private def addFields(form: StatelessForm[User]) {
    form.add(new RequiredTextField("username"))
    form.add(new PasswordTextField("password"))
    form.add(new EmailTextField("email"))
    form.add(new FeedbackPanel("feedback"))
  }

  private def addActions(form: StatelessForm[User]) {
    form.add(new Button("signin") {
      override def onSubmit() {
        signIn(form.getModelObject)
        SignInPage.this.setResponsePage(classOf[LoginPage])
      }
    })
  }

  private def signIn(user: User) {
    userService.save(user)
  }
}
