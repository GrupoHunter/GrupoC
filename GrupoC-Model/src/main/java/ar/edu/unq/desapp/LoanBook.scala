package ar.edu.unq.desapp

import org.joda.time.DateTime
import org.joda.time.Days

class LoanBook( val anUser: User, val aBook: Book, val dateOfLoan: DateTime, val refundDate: DateTime) {
  
	def isOutOfDate: Boolean = {
		dateOfLoan.isAfter(refundDate)
	}
	
	def amountDaysBetweenLoanAndRefund : Int = {
	  Days.daysBetween(dateOfLoan.withTimeAtStartOfDay(), refundDate.withTimeAtStartOfDay()).getDays()
	}
}