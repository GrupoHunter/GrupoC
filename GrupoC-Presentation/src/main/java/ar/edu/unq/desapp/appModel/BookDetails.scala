package ar.edu.unq.desapp.appModel

import ar.edu.unq.desapp.model.bean.Book

import scala.collection.JavaConversions._

class BookDetails(var book: Book) extends Serializable {
  var book_comment: String = _

  def getAuthors = {
    var authorsName: String = ""
    for (author <- book.authors.toList) {
      authorsName = author.getName ++ ", " ++ authorsName
    }
    authorsName
  }
}
