package ar.edu.unq.desapp.model.bean

import java.util
import javax.persistence.{Entity, GeneratedValue, Id, OneToMany, Table}

import scala.beans.BeanProperty

@Entity
@Table(name = "authors")
class Author(@BeanProperty var name: String) {

  @Id @GeneratedValue
  var id: Int = _
  
  @OneToMany @BeanProperty
  var writtenBooks: java.util.List[Book] = new util.ArrayList[Book]
  
  def this() = this(null)
}
