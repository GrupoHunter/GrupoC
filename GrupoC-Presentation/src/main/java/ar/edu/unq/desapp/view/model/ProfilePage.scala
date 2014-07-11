package ar.edu.unq.desapp.view.model

import ar.edu.unq.desapp.appModel.UserProfile
import ar.edu.unq.desapp.model.bean.{Book, User}
import ar.edu.unq.desapp.view.security.ScalaBaseProjectSession
import org.apache.wicket.markup.html.WebPage
import org.apache.wicket.markup.html.basic.Label
import org.apache.wicket.markup.html.form._
import org.apache.wicket.markup.html.list.{ListView, ListItem}
import org.apache.wicket.model.CompoundPropertyModel

class ProfilePage() extends BasePage {

  val mainPage: WebPage = new HomePage

  val user: User = ScalaBaseProjectSession.getCurrentUser
  val profileAppModel: UserProfile = new UserProfile(user, generalService.userService)
  profileAppModel.showUserData()

  override def onInitialize() {
    super.onInitialize()

    val profileForm = new StatelessForm[UserProfile]("profileForm", new CompoundPropertyModel(profileAppModel))

    addLabels(profileForm)
    addMyBorrowedBooksTable(profileForm)
    addActions(profileForm)
    add(profileForm)
  }

  private def addLabels(parent: StatelessForm[UserProfile]){
    parent.add(new Label("user_username"))
    parent.add(new EmailTextField("user_email"))
    parent.add(new PasswordTextField("user_password"))
  }

  private def addActions(parent: StatelessForm[UserProfile]){
    parent.add(new Button("update") { override def onSubmit() { updateProfile() }})
//    parent.add(new Button("back") { override def onSubmit() { backPage() }})
  }

  private def updateProfile() {
    profileAppModel.updateProfile()
    setResponsePage(new LoginPage)
  }

  private def backPage() {
    setResponsePage(this.mainPage)
  }

  private def addMyBorrowedBooksTable(form: StatelessForm[UserProfile]) {
    val borrowedBooks =
    new ListView[Book]("borrowedBooks", form.getModelObject.getMyBorrowedBooks) {
      override def populateItem(book: ListItem[Book]) =
        book.add(
          //Data - items for each book on the list
          new Label("title", book.getModelObject.title),
          new Button("returnBook") { override def onSubmit() { /*TODO*/ }}
        )
      }
    // Add table
    form.add(borrowedBooks)
  }


}
