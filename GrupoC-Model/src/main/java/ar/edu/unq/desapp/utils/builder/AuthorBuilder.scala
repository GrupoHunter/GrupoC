package ar.edu.unq.desapp.utils.builder

import ar.edu.unq.desapp.model.bean.{Author, Book}

import scala.collection.JavaConversions._

class AuthorBuilder extends BuilderToTest[Author] {
	
  var name: String = "no name"
  var writtenBooks: List[Book] = _
  
  def withName(aName: String): AuthorBuilder = {
    this.name = aName
    this
  }
  
  def withWrittenBooks(writtenBooks: List[Book]): AuthorBuilder = {
    this.writtenBooks = writtenBooks
    this
  }
  
  override def build: Author = {
    val author = new Author(name)
    author.writtenBooks = writtenBooks
    author
  }
}
