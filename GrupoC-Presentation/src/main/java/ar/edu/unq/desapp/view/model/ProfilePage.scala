package ar.edu.unq.desapp.view.model

import ar.edu.unq.desapp.appModel.UserProfile
import ar.edu.unq.desapp.model.bean.User
import org.apache.wicket.markup.html.WebPage
import org.apache.wicket.markup.html.basic.Label
import org.apache.wicket.markup.html.form.{Button, Form}
import org.apache.wicket.model.CompoundPropertyModel

class ProfilePage() extends HeadBlankPage {

  val user: User = new User("user", "bla@nah", "sarasa")
  val mainPage: WebPage = new BasePage
  val appModel: UserProfile = new UserProfile(user)

  override def onInitialize() {
    super.onInitialize()

    val profileForm = new Form[UserProfile]("profileForm", new CompoundPropertyModel(this.appModel))

    addLabels(profileForm)
    addActions(profileForm)
    add(profileForm)
  }

  private def addLabels(parent: Form[UserProfile]){
    parent.add(new Label("getUsername"))
    parent.add(new Label("getEmail"))
    parent.add(new Label("kindsBook"))
  }

  private def addActions(parent: Form[UserProfile]){
    parent.add(new Button("update") { def onClick() { updateProfile() }})
    parent.add(new Button("back") { def onClick() { backPage() }})
  }

  private def updateProfile() {
    this.appModel.updateProfile()
  }

  private def backPage() {
    this.setResponsePage(mainPage)
  }
}
