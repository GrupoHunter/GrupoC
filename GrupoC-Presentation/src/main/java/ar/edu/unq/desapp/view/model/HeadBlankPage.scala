package ar.edu.unq.desapp.view.model

import java.util.Locale

import org.apache.wicket.markup.html.WebPage

abstract class HeadBlankPage extends WebPage {

  // Set locale
  getSession.setLocale(Locale.US)
//  getSession.setLocale(new Locale("es"))

//  add(new Button("locale") { override def onSubmit() { changeLocale() } })

  private var locale_es: Boolean = false

  def changeLocale() {
    if (locale_es) {
      getSession.setLocale(new Locale("es"))
      locale_es = !locale_es
    } else {
      getSession.setLocale(Locale.US)
      locale_es = !locale_es
    }
  }
}
