package ar.edu.unq.desapp.view.model

import ar.edu.unq.desapp.model.bean.Book
import ar.edu.unq.desapp.services.GeneralService
import ar.edu.unq.desapp.utils.builder.Builder
import ar.edu.unq.desapp.view.security.{ComponentSecurityConfigurer, WebSession}
import de.agilecoders.wicket.core.Bootstrap
import org.apache.wicket.authroles.authentication.{AbstractAuthenticatedWebSession, AuthenticatedWebApplication}
import org.apache.wicket.markup.html.WebPage
import org.apache.wicket.spring.injection.annot.SpringComponentInjector
import org.springframework.context.{ApplicationContext, ApplicationContextAware}

import scala.beans.BeanProperty

class HomeApplication extends AuthenticatedWebApplication with ApplicationContextAware with Builder{
  
  @BeanProperty
  var generalService: GeneralService = _
  
  private var isInitialized: Boolean = false
  private var context: ApplicationContext = _
  private var componentSecurityConfigurer: ComponentSecurityConfigurer = _
  
  override def getHomePage: Class[HomePage] = classOf[HomePage]

  override def init(){
    if(!isInitialized) {
      super.init()
      this.getComponentInstantiationListeners.add(new SpringComponentInjector(this))
      isInitialized = true
    }
    this.generateFakeData()
    Bootstrap.install(this)
  }

  override def getWebSessionClass: Class[_ <: AbstractAuthenticatedWebSession] = {
    classOf[WebSession]
  }

  override def getSignInPageClass: Class[_ <: WebPage] = {
    classOf[LoginPage]
  }

  override def setApplicationContext(applicationContext: ApplicationContext) {
    context = applicationContext
  }

  def getApplicationContext: ApplicationContext = {
    context
  }

  def setComponentSecurityConfigurer(componentSecurityConfigurer: ComponentSecurityConfigurer) {
    this.componentSecurityConfigurer = componentSecurityConfigurer
  }

  private def generateFakeData() {
    val books: List[Book] =
    aBook.build :: aBook.withTitle("Test").build :: aBook.withDescription("This is a book").build :: aBook.withEditorial("NanaEditorial").build :: aBook.withIsbn("234567890").build :: aBook.build :: aBook.build :: aBook.build :: Nil

    for(book <- books)
      generalService.bookService.save(book)
  }
}
