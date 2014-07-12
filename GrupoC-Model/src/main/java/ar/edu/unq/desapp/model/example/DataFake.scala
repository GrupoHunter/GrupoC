package ar.edu.unq.desapp.model.example

import ar.edu.unq.desapp.model.bean.{Book, LoanBook, User}
import ar.edu.unq.desapp.utils.builder.Builder
import org.joda.time.DateTime

object DataFake extends Builder {
  /* Generate users */
  val userA: User =
    anUser.withName("Jona").withEmail("jona@mail.com").withPassword("jona").build
  val userB: User =
    anUser.withName("beto").withEmail("beto@email.com").withPassword("beto").build
  val userC: User =
    anUser.withName("Ursu").withEmail("ursu@email.com").withPassword("ursu").build
  val userD: User =
    anUser.withName("carlo").withEmail("carlo@email.com").withPassword("carlo").build
  val userE: User =
    anUser.withName("mati").withEmail("mati@email.com").withPassword("mati").build

  /* Generate books */
  val bookA: Book = aBook
    .withTitle("Alguien que anda por ahi")
    .withIsbn("isbn-1231")
    .withEditorial("Editorial Argentina")
    .withDescription("Libro por Julio Cortazar")
    .withRegistrationDate(new DateTime().minusWeeks(3))
    .withAmount(18).build

  val bookB: Book = aBook
    .withTitle("El alquimista")
    .withIsbn("isbn-234")
    .withEditorial("Editorial-A")
    .withDescription("Libro escrito por Pablo Cohelo")
    .withRegistrationDate(new DateTime().minusWeeks(1))
    .withAmount(13).build

  val bookC: Book = aBook
    .withTitle("Cien años de soledad")
    .withIsbn("isbn-879")
    .withEditorial("Editorial-Colombia")
    .withDescription("Libro escrito por Gabriel Garcia Marquez")
    .withAmount(14).build

  val bookD: Book = aBook
    .withTitle("Moby Dick")
    .withIsbn("isbn-876")
    .withEditorial("Editorial-AAA")
    .withDescription("Libro que trata de ballenas :P")
    .withAmount(14).build

  val bookE: Book = aBook
    .withTitle("No tengo idea")
    .withImage("isbn-1987")
    .build

  val bookF: Book = aBook.build

  /* Generate some loan */
  def generateLoan(): List[LoanBook] = {
    val loanManagement = aLoanManagement
    loanManagement.recordLoan(userA, bookA)
    loanManagement.recordLoan(userA, bookC)
    loanManagement.recordLoan(userA, bookD)

    loanManagement.recordLoan(userB, bookA)
    loanManagement.recordLoan(userB, bookB)
    loanManagement.recordLoan(userB, bookD)

    loanManagement.recordLoan(userC, bookA)
    loanManagement.recordLoan(userC, bookB)
    loanManagement.recordLoan(userC, bookD)

    loanManagement.recordLoan(userD, bookA)
    loanManagement.recordLoan(userD, bookB)
    loanManagement.recordLoan(userD, bookC)
    loanManagement.recordLoan(userD, bookD)

    loanManagement.recordLoan(userE, bookA)

    loanManagement.borrowedBooks
  }
}
