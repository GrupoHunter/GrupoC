 package ar.edu.unq.desapp.view.model

 import ar.edu.unq.desapp.appModel.AddEditBook
 import ar.edu.unq.desapp.model.bean.Book
 import ar.edu.unq.desapp.view.tabs.BookListPage
 import org.apache.wicket.markup.html.WebPage
 import org.apache.wicket.markup.html.basic.Label
 import org.apache.wicket.markup.html.form._
 import org.apache.wicket.markup.html.panel.FeedbackPanel
 import org.apache.wicket.model.CompoundPropertyModel

 class AddEditBookPage(book: Book) extends BasePage {

  // Parameter-less constructor
  def this() = this(null)
	
  var addEditBook: AddEditBook = new AddEditBook(generalService)

  val mainPage: WebPage = new HomePage
  val bookListPage: BookListPage = new BookListPage

 	override def onInitialize() {
 	  super.onInitialize()

    editMode // If a book is received, set title and get book info.

 		val bookForm = new StatelessForm[AddEditBook]("bookForm", new CompoundPropertyModel(this.addEditBook))
    addInputs(bookForm)
 		addActions(bookForm)
 		add(bookForm)
 	}

  // Call when a book is received, changes title and get book's info.
  private def editMode = {
    var title: String = null
    if (this.book == null) title = "New Book" else {
      title = "Edit Book Data"
      addEditBook.editBook(book)
    }
    add(new Label("title", title))
  }

 	private def addInputs(parent: StatelessForm[AddEditBook]) {
 		parent.add(new TextField[String]("book_title"))
 		parent.add(new TextField[String]("book_isbn"))
 		parent.add(new TextField[String]("book_editorial"))
 		parent.add(new TextField[String]("book_imageUrl"))
 		parent.add(new TextArea[String]("book_description"))
		parent.add(new TextField[String]("book_authors"))
 		parent.add(new FeedbackPanel("feedbackPanel"))
 	}

 	private def addActions(parent: StatelessForm[AddEditBook]){
 		parent.add(new Button("accept") {
 			override def onSubmit() { saveChanges() }}
    )
 		parent.add(new Button("cancel") {
 			override def onSubmit() { returnPage() }}
    )
 	}

  private def saveChanges() {
    addEditBook.saveChanges()
    setResponsePage(bookListPage)
  }

 	private def returnPage() {
 	  setResponsePage(mainPage)
 	}
 }
