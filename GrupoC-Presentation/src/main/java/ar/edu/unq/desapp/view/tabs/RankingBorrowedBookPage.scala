package ar.edu.unq.desapp.view.tabs

import ar.edu.unq.desapp.appModel.RankingBorrowedBookAppModel
import ar.edu.unq.desapp.model.bean.Book
import ar.edu.unq.desapp.services.GeneralService
import ar.edu.unq.desapp.view.model.BasePage
import org.apache.wicket.markup.html.basic.Label
import org.apache.wicket.markup.html.form.Form
import org.apache.wicket.markup.html.list.{ListItem, ListView}
import org.apache.wicket.model.CompoundPropertyModel
import org.apache.wicket.spring.injection.annot.SpringBean

import scala.beans.BeanProperty

class RankingBorrowedBookPage extends BasePage {

  @BeanProperty @SpringBean(name = "services.general")
  var generalService: GeneralService = _

  override def onInitialize() {
    super.onInitialize()
    val bookListAppModel = new RankingBorrowedBookAppModel(this.generalService.bookService)
    val form = new Form[RankingBorrowedBookAppModel]("bookListForm",
      new CompoundPropertyModel[RankingBorrowedBookAppModel](bookListAppModel)
    )

//// TODO - Uncomment this when mostBorrowed on BookService is implemented
//    createTableRanking(form)
///  /TODO

    add(form)
  }

  private def createTableRanking(form: Form[RankingBorrowedBookAppModel]) {
    val books =
      new ListView[Book]("books", form.getModelObject.mostBorrowed) {
        override def populateItem(book: ListItem[Book]) =
          book.add(
            //Data - items for each book on the list
            new Label("title", book.getModelObject.title),
            new Label("isbn", book.getModelObject.isbn)
          )
      }
    // Add table
    form.add(books)
  }
  
}
