package ar.edu.unq.desapp.view.model

import ar.edu.unq.desapp.appModel.AddEditBook
import org.apache.wicket.markup.html.WebPage
import org.apache.wicket.markup.html.basic.Label
import org.apache.wicket.markup.html.form.{Button, StatelessForm, TextField}
import org.apache.wicket.markup.html.panel.FeedbackPanel
import org.apache.wicket.model.CompoundPropertyModel

class AddApiBookPage extends BasePage {

  val mainPage: WebPage = new HomePage
  var addEditBook: AddEditBook = new AddEditBook(generalService)

  override def onInitialize() {
    super.onInitialize()
    val bookForm = new StatelessForm[AddEditBook]("bookForm", new CompoundPropertyModel(this.addEditBook))

    add(new Label("title", "Get book data"))

    addInputs(bookForm)
    addActions(bookForm)
    add(bookForm)
  }

  private def addInputs(parent: StatelessForm[AddEditBook]) {
    parent.add(new TextField[String]("book_isbn"))
    parent.add(new FeedbackPanel("feedbackPanel"))
  }

  private def addActions(parent: StatelessForm[AddEditBook]){
    parent.add(new Button("cancel") {
      override def onSubmit() { returnPage() }}
    )
    parent.add(new Button("getData") {
      override def onSubmit() { getData() }}
    )
  }

  private def getData() {
    val book = this.addEditBook.getExternalBook
    setResponsePage(new AddEditBookPage(book))
  }

  private def returnPage() {
    setResponsePage(mainPage)
  }
}
