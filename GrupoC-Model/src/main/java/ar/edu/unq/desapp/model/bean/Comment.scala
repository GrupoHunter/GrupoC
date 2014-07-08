package ar.edu.unq.desapp.model.bean

import javax.persistence.{Column, Entity, GeneratedValue, Id, ManyToOne, Table}

import org.joda.time.DateTime

import scala.beans.BeanProperty

@Entity
@Table(name = "comments")
class Comment(
  @BeanProperty
  var comment: String,
  @BeanProperty
  var date: DateTime) {
  
  @Id @GeneratedValue
  @Column(name = "id_comment")
  var id: Int = _
  
  @ManyToOne @BeanProperty
  var user: User = _
  
  def this() = this(null, null)
}
