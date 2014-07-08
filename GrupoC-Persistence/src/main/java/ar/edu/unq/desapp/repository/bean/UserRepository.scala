package ar.edu.unq.desapp.repository.bean

import ar.edu.unq.desapp.model.bean.User
import ar.edu.unq.desapp.repository.generic.HibernateGenericDAO

class UserRepository extends HibernateGenericDAO[User] {

  persistentClass = classOf[User]
}
