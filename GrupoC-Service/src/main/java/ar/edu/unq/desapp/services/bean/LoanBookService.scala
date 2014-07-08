package ar.edu.unq.desapp.services.bean

import ar.edu.unq.desapp.model.bean.LoanBook
import ar.edu.unq.desapp.repository.bean.LoanBookRepository
import ar.edu.unq.desapp.services.GenericService
import javax.annotation.Resource

class LoanBookService extends GenericService[LoanBook] {
	
  private val serialVersionUID: Long = 1L
  
  @Resource
  var loanBookDAO: LoanBookRepository= _
  
  def retriveAllMostBorrowed: java.util.List[LoanBook] = {
	loanBookDAO.findTheTwentyMostBorrowedBook
  }
}
