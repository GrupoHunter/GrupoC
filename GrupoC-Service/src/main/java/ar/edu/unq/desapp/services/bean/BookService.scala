package ar.edu.unq.desapp.services.bean

import ar.edu.unq.desapp.services.GenericService
import ar.edu.unq.desapp.model.bean.{LoanBook, Author, Book}
import javax.annotation.Resource
import ar.edu.unq.desapp.repository.bean.{LoanBookRepository, BookRepository}
import org.apache.commons.io.IOUtils
import org.apache.http.client.methods.HttpGet
import org.apache.http.impl.client.DefaultHttpClient
import scala.beans.BeanProperty
import scala.util.parsing.json.JSON

import scala.collection.JavaConversions._

class BookService extends GenericService[Book] {

  @BeanProperty @Resource
  var bookRepository: BookRepository = _

  @BeanProperty @Resource
  var loanBookRepository: LoanBookRepository = _

  def retriveAllMostBorrowed: java.util.List[Book] = {
    val loanBooks: java.util.List[LoanBook] = loanBookRepository.findAll
    var allMostBorrowedBooks: Map[Book, Int] = Map()
    for(loan <- loanBooks) { //I know, it's horrible - TODO Refactor
      if(allMostBorrowedBooks.contains(loan.book)) {
        allMostBorrowedBooks.updated(loan.book, allMostBorrowedBooks.get(loan.book))
      }
      else {
        allMostBorrowedBooks += (loan.book -> 1)
      }
    }
    allMostBorrowedBooks.keySet.toList
  }

  def alreadyExists(aBook: Book): Boolean = { //I know, it's horrible - TODO Refactor
    val books: java.util.List[Book] = bookRepository.findAll
    var result: Boolean = false

    for( book <- books ) {
      if(book.isbn == aBook.isbn) result = true
    }
    result
  }

////////////////// Google Books Api Service
  class CC[T] { def unapply(a:Any):Option[T] = Some(a.asInstanceOf[T]) }

  object M extends CC[Map[String, Any]]
  object L extends CC[List[Any]]
  object S extends CC[String]

  def getExternalBook(isbn: String) = {
    getBook(isbn).head
  }

  private def getBook(isbn: String) = {
    for {
      Some(M(map)) <- List(getJson(isbn))
      L(items) = map("items")
      //M(item) = map("items")
      M(item) <- items
      M(volumeInfo) = item("volumeInfo")
      S(title) = volumeInfo("title")
      L(authors) = volumeInfo("authors")
      S(editorial) = volumeInfo("publisher")
      M(imageLinks) = volumeInfo("imageLinks")
      S(imageUrl) = imageLinks("thumbnail")
    } yield {
      var authorsList: List[Author] = Nil
        for (author <- authors) {
          authorsList = new Author(author.toString) :: authorsList
        }
      //TODO - Add description - Get more than one author (for <- authors)
      val book: Book = new Book(title, isbn, editorial, imageUrl, null)
      book.setAuthors(authorsList.toList)

      book
    }
  }

  private def getJson(isbn: String) = {
    val apiUrl = "https://www.googleapis.com/books/v1/volumes/"
    val apiKey = "&key=AIzaSyBjabeSyJz1GuxqRLBI9S434upZLfkC35I"
    val filterParams = "&fields=items(volumeInfo)"

    val request = new HttpGet(apiUrl + "?q=isbn:" + isbn + filterParams + apiKey)
    val client = new DefaultHttpClient()
    val response = client.execute(request)

    println(response.getStatusLine.getStatusCode)
    val json = IOUtils.toString(response.getEntity.getContent)
    //    response.getEntity.getContent.toString
    //    "Getting json" + json
    JSON.parseFull(json)
  }
}
