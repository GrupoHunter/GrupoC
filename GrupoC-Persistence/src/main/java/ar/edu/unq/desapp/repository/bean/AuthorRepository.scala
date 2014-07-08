package ar.edu.unq.desapp.repository.bean

import ar.edu.unq.desapp.model.bean.Author
import ar.edu.unq.desapp.repository.generic.HibernateGenericDAO

class AuthorRepository extends HibernateGenericDAO[Author] {

  persistentClass = classOf[Author]
}
