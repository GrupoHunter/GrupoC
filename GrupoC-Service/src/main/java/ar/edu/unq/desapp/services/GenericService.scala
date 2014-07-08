package ar.edu.unq.desapp.services

import org.springframework.transaction.annotation.Transactional
import ar.edu.unq.desapp.repository.generic.GenericRepository
import scala.beans.BeanProperty

class GenericService[T] extends Serializable {

  @BeanProperty
  var repository: GenericRepository[T] = _
  
  @Transactional
  def save(obj: T) {
    this.repository.save(obj)
  }

  @Transactional(readOnly = true)
  def delete(obj: T) {
    this.repository.delete(obj)
  }

  @Transactional
  def update(obj: T) {
    this.repository.update(obj)
  }

  @Transactional
  def findById(id: Serializable): T = {
    this.repository.findById(id)
  }

  @Transactional
  def deleteById(id: Serializable) {
    this.repository.deleteById(id)
  }

  @Transactional
  def retriveAll: List[T] = {
    this.repository.findAll
  }
}
