package ar.edu.unq.desapp.utils.builder

import ar.edu.unq.desapp.model.bean.LoanConfiguration
import ar.edu.unq.desapp.model.management.LibrarySystem
import ar.edu.unq.desapp.model.management.LoanManagement
import ar.edu.unq.desapp.model.mailer.NotificationSystem

abstract class BuilderToTest[T] {

  def build: T //generic build
}

//Here define the builders to share with others test
trait Builder {
  val aLoanConfiguration = new LoanConfiguration
  val anUser: UserBuilder = new UserBuilder
  val aLibrarian: LibrarianBuilder = new LibrarianBuilder(new LibrarySystem(aLoanConfiguration))
  val aBook: BookBuilder = new BookBuilder
  val aLoanManagement: LoanManagement = new LoanManagement(new NotificationSystem, aLoanConfiguration)
  val anAuthor: AuthorBuilder = new AuthorBuilder
}
