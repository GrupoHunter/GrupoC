package ar.edu.unq.desapp.builders

import org.scalatest.mock.MockitoSugar
import java.awt.Image
import ar.edu.unq.desapp.LoanManagement
import ar.edu.unq.desapp.Book
import ar.edu.unq.desapp.LibrarySystem
import ar.edu.unq.desapp.User
import ar.edu.unq.desapp.Author
import ar.edu.unq.desapp.Librarian
import ar.edu.unq.desapp.SearchBookSystem
import ar.edu.unq.desapp.NotificationSystem
import ar.edu.unq.desapp.EmailService

class Fixture extends MockitoSugar {
  val librarySystem = new LibrarySystem()
  val librarian = new Librarian("librarian", "libr@rian.com", "1234", librarySystem)
  val client = new User("userName", "client@librarian.com", "4651321")
  val notificationSystem = new NotificationSystem()
  val loanManagement = new LoanManagement(notificationSystem)
  val searcher = new SearchBookSystem()
  val emailService = mock[EmailService]

  //Users
  val userA = new User("userA", "userA@gmail.com", "124121")
  val userB = new Librarian("librarian", "librarian@gmail.com", "12412412", mock[LibrarySystem])
  val userC = new User("userC", "userC@gmail.com", "1124feqf")

  //BookA
  val titleA = "Title"
  val isbnA = "123-749"
  val editorialA = "Something"
  val imageA = mock[Image]
  val descriptionA = "Just to test it"
  val authorsA = mock[Author] :: Nil
  val amountA = 3
  val bookA = new Book(titleA, isbnA, editorialA, imageA, descriptionA, authorsA, amountA)
  //BookB
  val titleB = "The True"
  val isbnB = "987-789"
  val editorialB = "What's up?"
  val imageB = mock[Image]
  val descriptionB = "Yes, i haven't more imagination"
  val authorsB = mock[Author] :: authorsA
  val bookB = new Book(titleB, isbnB, editorialB, imageB, descriptionB, authorsB)
  //BookC
  val titleC = "FullMoon"
  val isbnC = "156-5746"
  val editorialC = "Sonata Artica"
  val imageC = mock[Image]
  val descriptionC = "i was listened Sonata Artica"
  val authorsC = mock[Author] :: Nil
  val bookC = new Book(titleC, isbnC, editorialC, imageC, descriptionC, authorsC)
}
