package ar.edu.unq.desapp.services.bean

import javax.annotation.Resource

import ar.edu.unq.desapp.model.bean.{Book, LoanBook}
import ar.edu.unq.desapp.services.GenericService
import org.joda.time.DateTime
import org.springframework.transaction.annotation.Transactional

import scala.beans.BeanProperty

class LoanBookService extends GenericService[LoanBook] {

  @BeanProperty @Resource
  var userService: UserService = _

  @Transactional
  def recordLoan(username: String, aBook: Book) {
    val user = userService.findByUsername(username)
    if (user != null) {
      aBook.borrowedAmount += 1
      user.borrowBook(aBook)
      val refundDate = new DateTime().plusDays(3)//TODO define loan to day
      val loanBook = new LoanBook(new DateTime, refundDate)
      loanBook.user = user
      loanBook.book = aBook
      save(loanBook)
    }
  }
}