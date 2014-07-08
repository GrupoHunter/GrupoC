package ar.edu.unq.desapp.repository.bean

import ar.edu.unq.desapp.model.bean.LoanBook
import ar.edu.unq.desapp.repository.generic.HibernateGenericDAO
import org.hibernate.criterion.Projections

class LoanBookRepository extends HibernateGenericDAO[LoanBook] {

  persistentClass = classOf[LoanBook]
  
  def findTheTwentyMostBorrowedBook: java.util.List[LoanBook] = {
    val criteria = this.getSession.createCriteria(this.persistentClass)
    val proList = Projections.projectionList()
    
    proList.add(Projections.distinct(Projections.countDistinct("book")))
    criteria.setProjection(proList).list().asInstanceOf[java.util.List[LoanBook]]
  }
}
