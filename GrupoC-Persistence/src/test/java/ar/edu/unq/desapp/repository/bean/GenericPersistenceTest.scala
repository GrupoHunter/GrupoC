package ar.edu.unq.desapp.repository.bean

import javax.annotation.Resource

import ar.edu.unq.desapp.repository.generic.HibernateGenericDAO
import ar.edu.unq.desapp.utils.builder.{Builder, BuilderToTest}
import org.scalatest.{BeforeAndAfter, FunSpec, GivenWhenThen, Matchers}
import org.springframework.test.context.{ContextConfiguration, TestContextManager}

@ContextConfiguration( Array("classpath:/META-INF/spring-persistence-context.xml", "classpath:/spring/*.xml"))
class GenericPersistenceTest extends FunSpec with Matchers with GivenWhenThen with BeforeAndAfter with Builder {

  new TestContextManager(classOf[GenericPersistenceTest]).prepareTestInstance(this)
  
  @Resource(name="hibernateGeneric")
  var repository: HibernateGenericDAO[java.lang.Object] = _
  
  private def getAllMainClassBuilders: List[_ <: BuilderToTest[_]] = {
    val books = Nil
    val books_written = Nil
    val comments = Nil
    
    List(
    		anUser.withName("Nadie").withEmail("Ninguno").withPassword("Boludo").withBorrowedBooks(books),
    		aBook.withTitle("book?").withEditorial("what editorial?").withIsbn("what is that?").withAmount(2).withComments(comments),
    		anAuthor.withName("Erudito").withWrittenBooks(books_written)
    		)
  }
  
  describe("Persistence all Entities ") {
    ignore("all entities should be persisted") {
    	val builders = this.getAllMainClassBuilders
    	
    	for(builder <- builders) {
    	  val obj = builder.build
    	  try {
    		  repository.save(obj)
    	  } catch {
    	    case e: Exception => fail("the entity couldn't persist: " + e.getMessage)
    	  }
    	}
    }
    
    it("Save and retrieve all entities") {
      val builders = this.getAllMainClassBuilders
      
      for(builder <- builders) {
        val obj = builder.build
        
        try {
        	repository.save(obj)
        	repository.getSessionFactory.getCurrentSession.flush()
          
        	val obj_from_DB = repository.findByExample(obj)(0)
        	obj_from_DB should equal(obj)
        	repository.delete(obj)
        } catch {
          case e: Exception => fail("test failed due to: " + e.getMessage + e.getCause)
        }
      }
    }
  }
}
