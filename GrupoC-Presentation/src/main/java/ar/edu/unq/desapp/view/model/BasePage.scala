package ar.edu.unq.desapp.view.model

import ar.edu.unq.desapp.view.tabs.{BookListPage, RankingBorrowedBookPage}
import org.apache.wicket.markup.html.link.BookmarkablePageLink

class BasePage extends HeadBlankPage {

  override def onInitialize() {
    super.onInitialize()
    this.addLinkToHome()
    this.addProfileLink()
    this.addMainOptions()
  }

  private def addLinkToHome() {
    add(new BookmarkablePageLink[HomePage]("linkToHomePage", classOf[HomePage]))
  }

  private def addProfileLink() {
    add(new BookmarkablePageLink("profile", classOf[ProfilePage]))
  }
  
  private def addMainOptions() {
    add(new BookmarkablePageLink("BookList", classOf[BookListPage]))
	  add(new BookmarkablePageLink("RankingBorrowedBook", classOf[RankingBorrowedBookPage]))
    add(new BookmarkablePageLink("AddBook", classOf[AddEditBookPage]))
    add(new BookmarkablePageLink("AddApiBook", classOf[AddApiBookPage]))
  }
}
