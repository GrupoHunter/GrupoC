package ar.edu.unq.desapp.repository.generic

trait GenericRepository[T] {

  def save(t: T)

  def delete(t: T)

  def update(t: T)

  def findById(id: Serializable): T

  def deleteById(id: Serializable)

  def findAll: List[T]

  def count: Int

  def findByExample(t: T): List[T]
}
