package ar.edu.unq.desapp.model.bean

import java.util
import javax.persistence._

import org.hibernate.annotations.IndexColumn
import org.joda.time.DateTime

import scala.beans.BeanProperty
import scala.collection.JavaConversions.seqAsJavaList
import scala.collection.JavaConverters.asScalaBufferConverter

@Entity @Table(name = "books")
class Book (
  @BeanProperty
  var title: String,
  @BeanProperty
  var isbn: String,
  @BeanProperty
  var editorial: String,
  @BeanProperty
  var imageUrl: String,
  @BeanProperty
  var description: String,
  @BeanProperty
  var amount: Int = 1,
  @BeanProperty
  var registrationDate: DateTime = new DateTime
){

  @Id @GeneratedValue
  @Column(name = "id_book")
  var id: Int = _

  @BeanProperty
  @OneToMany(cascade = Array{CascadeType.ALL}, fetch = FetchType.EAGER)
  @IndexColumn(name = "id_book")
  var comment: java.util.List[Comment] = new util.ArrayList[Comment]

  @BeanProperty
  @OneToMany(cascade = Array{CascadeType.ALL}, fetch = FetchType.EAGER)
  @IndexColumn(name = "id_book")
  var authors: java.util.List[Author] = new util.ArrayList[Author]

  var borrowedAmount: Int = 0

  def this() = this(null, null, null, null, null)
  
  def addComment(anUser: User, comment: String) {
    val aComment = new Comment(comment, new DateTime)
    aComment.user = anUser
    this.comment = aComment :: this.comment.asScala.toList
  }

  def isNew: Boolean = {
    registrationDate.toDate.after(new DateTime().withTimeAtStartOfDay().toDate)
  }

  def isAvailable: Boolean = amount > borrowedAmount
}
