package ar.edu.unq.desapp.repository.bean

import ar.edu.unq.desapp.model.bean.Librarian
import ar.edu.unq.desapp.repository.generic.HibernateGenericDAO

class LibrarianRepository extends HibernateGenericDAO[Librarian] {

  persistentClass = classOf[Librarian]
}
