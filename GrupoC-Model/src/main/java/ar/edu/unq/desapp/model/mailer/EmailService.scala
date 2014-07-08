package ar.edu.unq.desapp.model.mailer

import ar.edu.unq.desapp.model.bean.User
import ar.edu.unq.desapp.model.bean.Book
import org.apache.commons.mail.SimpleEmail
import org.apache.commons.mail.DefaultAuthenticator

/**
* Just simulate service email
*/
class EmailService {

 def sendNotification(user: User, aBorrowedBook: Book) {
   val email = new SimpleEmail()
   email.setHostName("smtp.googlemail.com")
   email.setSmtpPort(465)
   email.setAuthenticator(new DefaultAuthenticator("librarySystem", "lalala"))
   email.setSSLOnConnect(true)
   email.setFrom("librarysystem@gmail.com")
   email.setSubject("Automatic Library System Mail")
   email.setMsg("We are send you an email for let you know that you need to return book" + aBorrowedBook.title)
   email.addTo(user.email)
   email.send()
 }
}
