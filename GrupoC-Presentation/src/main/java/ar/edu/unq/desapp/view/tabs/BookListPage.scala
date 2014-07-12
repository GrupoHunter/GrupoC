package ar.edu.unq.desapp.view.tabs

import ar.edu.unq.desapp.appModel.BookListAppModel
import ar.edu.unq.desapp.model.bean.Book
import ar.edu.unq.desapp.view.model.{BasePage, BookDetailsPage}
import ar.edu.unq.desapp.view.security.ScalaBaseProjectSession
import de.agilecoders.wicket.core.markup.html.bootstrap.navigation.BootstrapPagingNavigator
import org.apache.wicket.markup.html.basic.Label
import org.apache.wicket.markup.html.form.{Button, StatelessForm}
import org.apache.wicket.markup.html.list.{ListItem, PageableListView}
import org.apache.wicket.model.CompoundPropertyModel

class BookListPage extends BasePage {

	override def onInitialize() {
		super.onInitialize()
		val bookListAppModel = new BookListAppModel(generalService)
		val form = new StatelessForm[BookListAppModel]("bookListForm",
		    new CompoundPropertyModel[BookListAppModel](bookListAppModel)
		)
		addBookTable(form)
		add(form)
	}

	// Adds table book items
	private def addBookTable(form: StatelessForm[BookListAppModel]) {
    val books =
			new PageableListView[Book]("books", form.getModelObject.books, 5) {
			  	override def populateItem(book: ListItem[Book]) =
			    	book.add(
            //Data - items for each book on the list
                new Label("title", book.getModelObject.title),
                new Label("isNew").setVisible(book.getModelObject.isNew),
                new Label("isbn", book.getModelObject.isbn),
                new Label("registrationDate", book.getModelObject.registrationDate.toDate),
                new Label("amount", book.getModelObject.getAmount),
                new Label("borrowedAmount", form.getModelObject.getBorrowedAmount(book.getModelObject)),
                new Label("state", form.getModelObject.isAvailable(book.getModelObject)),
            //Actions
                new Button("details") { override def onSubmit() { detailsBook(book.getModelObject)} },
                new Button("reserve") { override def onSubmit() { form.getModelObject.reserveBook(book.getModelObject)} },
                new Button("loan") { override def onSubmit() { form.getModelObject.loanBook(book.getModelObject)}	}
                    .setVisible(currentUserIsAdmin()),
                new Button("delete") {
                      override def onSubmit() {
                        form.getModelObject.deleteBook(book.getModelObject)
                        setResponsePage(classOf[BookListPage])
                      }
                }.setVisible(currentUserIsAdmin())
        )
			}
    // Pagination nav
    form.add(new BootstrapPagingNavigator("navigator", books))
    // Add table
		form.add(books)
	}

  private def detailsBook(aBook: Book) {
      this.setResponsePage(new BookDetailsPage(aBook))
  }

  private def currentUserIsAdmin(): Boolean = {
    ScalaBaseProjectSession.getCurrentUser.rols.contains("ROLE_ADMIN")
  }
}
