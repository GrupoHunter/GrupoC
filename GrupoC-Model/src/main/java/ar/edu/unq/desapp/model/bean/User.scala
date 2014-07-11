package ar.edu.unq.desapp.model.bean

import javax.persistence._

import org.hibernate.annotations.IndexColumn

import scala.beans.BeanProperty
import scala.collection.JavaConversions._

@Entity
@Table(name = "users")
@Inheritance(strategy=InheritanceType.JOINED)
class User (
  @BeanProperty
  var username: String,
  @BeanProperty
  var email: String,
  @BeanProperty
  var password: String) {
 
  @Id @GeneratedValue
  @Column(name = "id")
  var id: Int = _

  @BeanProperty
  @ElementCollection(fetch = FetchType.EAGER)
  @IndexColumn(name = "id")
  var rols: java.util.List[String] = new java.util.ArrayList[String]
  
  @BeanProperty
  @OneToMany(fetch = FetchType.EAGER)
  @IndexColumn(name = "id")
  var borrowedBooks: java.util.List[Book] = _

  def this() = this(null, null, null)
  
  def borrowBook(aBook: Book) {
    borrowedBooks = aBook :: borrowedBooks.toList
  }

  def returnBook(aBook: Book) {
    borrowedBooks = borrowedBooks filter (book => !book.equals(aBook))
    aBook.amount += 1
  }

  def commentBook(aBook: Book, aComment: String) {
    aBook.addComment(this, aComment)
  }

  def addRole(rols: String*) {
    for (rol <- rols) {
      this.rols.add(rol)
    }
  }
}
