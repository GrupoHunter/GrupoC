package ar.edu.unq.desapp.repository.generic

import org.hibernate.{Criteria, SessionFactory}
import org.hibernate.criterion.Projections
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.orm.hibernate3.support.HibernateDaoSupport

import scala.collection.JavaConversions._

class HibernateGenericDAO[T] extends HibernateDaoSupport with GenericRepository[T] with Serializable {

  protected var persistentClass: Class[T] = _

  @Autowired
  def anyMethodName(sessionFactory: SessionFactory) {
    this.setSessionFactory(sessionFactory)
  }

  override def save(entity: T) {
    this.getHibernateTemplate.save(entity)
    this.getHibernateTemplate.flush()
  }

  override def findByExample(entity: T): List[T] = {
    this.getHibernateTemplate.findByExample(entity).asInstanceOf[java.util.List[T]].toList
  }

  override def findAll: List[T] = {
    this.getHibernateTemplate.loadAll(this.persistentClass)
      .asInstanceOf[java.util.List[T]].toList
  }

  override def count: Int = {
    var criteria: Criteria = this.getSession.createCriteria(this.persistentClass)
    criteria.setProjection(Projections.rowCount()).list().get(0).asInstanceOf[java.lang.Long].intValue()
  }

  override def delete(entity: T) {
    this.getHibernateTemplate.delete(entity)
  }

  override def deleteById(id: Serializable) {
    val obj: T = this.findById(id)
    this.getHibernateTemplate.delete(obj)
  }

  override def update(entity: T) {
    this.getHibernateTemplate.update(entity)
  }

  override def findById(id: Serializable): T = {
    this.getHibernateTemplate.get(this.persistentClass, id)
  }
}
