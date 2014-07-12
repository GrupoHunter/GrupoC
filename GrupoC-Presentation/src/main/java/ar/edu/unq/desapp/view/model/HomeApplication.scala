package ar.edu.unq.desapp.view.model

import ar.edu.unq.desapp.model.example.DataFake
import ar.edu.unq.desapp.services.GeneralService
import ar.edu.unq.desapp.utils.builder.Builder
import ar.edu.unq.desapp.view.security.{ComponentSecurityConfigurer, ScalaBaseProjectSession}
import de.agilecoders.wicket.core.Bootstrap
import org.apache.wicket.Session
import org.apache.wicket.authroles.authentication.{AbstractAuthenticatedWebSession, AuthenticatedWebApplication}
import org.apache.wicket.markup.html.WebPage
import org.apache.wicket.request.{Request, Response}
import org.apache.wicket.spring.injection.annot.{SpringBean, SpringComponentInjector}
import org.springframework.context.{ApplicationContext, ApplicationContextAware}

import scala.beans.BeanProperty

class HomeApplication extends AuthenticatedWebApplication with ApplicationContextAware with Builder{

  @BeanProperty @SpringBean(name = "services.general")
  var generalService: GeneralService = _

  private var isInitialized: Boolean = false
  private var context: ApplicationContext = _
  private var componentSecurityConfigurer: ComponentSecurityConfigurer = _

  override def getHomePage: Class[LoginPage] = classOf[LoginPage]

  override def init(){
    if(!isInitialized) {
      super.init()
      this.getComponentInstantiationListeners.add(new SpringComponentInjector(this, getApplicationContext, true))
      this.getComponentInstantiationListeners.add(componentSecurityConfigurer)
      isInitialized = true
    }
    this.generateFakeData()
    Bootstrap.install(this)
  }

  override def newSession(request: Request, response: Response): Session = {
    new ScalaBaseProjectSession(request)
  }

  override def getWebSessionClass: Class[_ <: AbstractAuthenticatedWebSession] = {
    classOf[ScalaBaseProjectSession]
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
    generalService.userService.loadDefaultUsers()

    generalService.bookService.save(DataFake.bookA)
    generalService.bookService.save(DataFake.bookB)
    generalService.bookService.save(DataFake.bookC)
    generalService.bookService.save(DataFake.bookD)

    generalService.userService.save(DataFake.userA)
    generalService.userService.save(DataFake.userB)
    generalService.userService.save(DataFake.userC)
    generalService.userService.save(DataFake.userD)
    generalService.userService.save(DataFake.userE)

    for(loan <- DataFake.generateLoan()) {
      generalService.loanBookService.save(loan)
    }
  }
}
