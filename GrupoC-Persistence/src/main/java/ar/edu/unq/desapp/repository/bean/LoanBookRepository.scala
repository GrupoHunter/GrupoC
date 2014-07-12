package ar.edu.unq.desapp.repository.bean

import ar.edu.unq.desapp.model.bean.LoanBook
import ar.edu.unq.desapp.repository.generic.HibernateGenericDAO

class LoanBookRepository extends HibernateGenericDAO[LoanBook] {
  persistentClass = classOf[LoanBook]
}
