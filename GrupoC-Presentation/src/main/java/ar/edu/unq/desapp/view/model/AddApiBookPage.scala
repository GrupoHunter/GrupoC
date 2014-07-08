package ar.edu.unq.desapp.view.model

import ar.edu.unq.desapp.appModel.AddEditBook
import ar.edu.unq.desapp.services.GeneralService
import org.apache.wicket.markup.html.WebPage
import org.apache.wicket.markup.html.basic.Label
import org.apache.wicket.markup.html.form.{Button, Form, TextField}
import org.apache.wicket.markup.html.panel.FeedbackPanel
import org.apache.wicket.model.CompoundPropertyModel
import org.apache.wicket.spring.injection.annot.SpringBean

import scala.beans.BeanProperty

class AddApiBookPage extends BasePage {

  @BeanProperty @SpringBean(name = "services.general")
  var generalService: GeneralService = _

  val mainPage: WebPage = new HomePage
  var addEditBook: AddEditBook = new AddEditBook(generalService)

  override def onInitialize() {
    super.onInitialize()
    val bookForm = new Form[AddEditBook]("bookForm", new CompoundPropertyModel(this.addEditBook))

    add(new Label("title", "Get book data"))

    addInputs(bookForm)
    addActions(bookForm)
    add(bookForm)
  }

  private def addInputs(parent: Form[AddEditBook]) {
    parent.add(new TextField[String]("book_isbn"))
    parent.add(new FeedbackPanel("feedbackPanel"))
  }

  private def addActions(parent: Form[AddEditBook]){
    parent.add(new Button("cancel") {
      override def onSubmit() { returnPage() }}
    )
    parent.add(new Button("getData") {
      override def onSubmit() { getData() }}
    )
  }

  private def getData() {
    val book = this.addEditBook.getExternalBook()
    setResponsePage(new AddEditBookPage(book))
  }

  private def returnPage() {
    setResponsePage(mainPage)
  }
}
