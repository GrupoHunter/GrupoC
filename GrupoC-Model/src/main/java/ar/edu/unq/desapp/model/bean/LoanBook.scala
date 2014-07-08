package ar.edu.unq.desapp.model.bean

import javax.persistence.{Entity, GeneratedValue, Id, OneToOne, Table}

import org.joda.time.{DateTime, Days}

import scala.beans.BeanProperty

@Entity 
@Table(name = "loan_books")
class LoanBook(
  @BeanProperty
  var dateOfLoan: DateTime,
  @BeanProperty
  var refundDate: DateTime) {

  @Id @GeneratedValue
  var id: Int = _

  @OneToOne @BeanProperty
  var book: Book = _
  
  @OneToOne @BeanProperty
  var user: User = _
  
  def this() = this(null, null)
  
  def isOutOfDate: Boolean = {
    dateOfLoan.isAfter(refundDate)
  }

  def amountDaysBetweenLoanAndRefund: Int = {
    Days.daysBetween(dateOfLoan.withTimeAtStartOfDay(), refundDate.withTimeAtStartOfDay()).getDays
  }
}
