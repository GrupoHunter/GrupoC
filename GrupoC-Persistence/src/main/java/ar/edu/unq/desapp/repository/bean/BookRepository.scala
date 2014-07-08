package ar.edu.unq.desapp.repository.bean

import ar.edu.unq.desapp.model.bean.Book
import ar.edu.unq.desapp.repository.generic.HibernateGenericDAO

class BookRepository extends HibernateGenericDAO[Book] {

  persistentClass = classOf[Book]
  
  def findTheTwentyMostBorrowedBook: java.util.List[Book] = {
    this.getSession.createCriteria(this.persistentClass).asInstanceOf[java.util.List[Book]]
  }
}
