package ar.edu.unq.desapp.services

import ar.edu.unq.desapp.services.bean.{AuthorService, BookService, LoanBookService, UserService}

import scala.beans.BeanProperty

class GeneralService {
  @BeanProperty
  var userService: UserService = _
  @BeanProperty
  var bookService: BookService = _
  @BeanProperty
  var authorService: AuthorService = _
  @BeanProperty
  var loanBookService: LoanBookService = _
}
