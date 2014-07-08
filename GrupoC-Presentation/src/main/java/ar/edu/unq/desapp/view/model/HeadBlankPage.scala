package ar.edu.unq.desapp.view.model

import java.util.Locale

import org.apache.wicket.markup.html.WebPage

abstract class HeadBlankPage extends WebPage {

  // Set locale
//  getSession.setLocale(new Locale("es"))
  getSession.setLocale(Locale.US)


//  private var locale_es: Boolean = false
//
//  private def changeLocale() {
//    if (locale_es) {
//      getSession.setLocale(new Locale("es"))
//      locale_es = !locale_es
//    } else {
//      getSession.setLocale(Locale.US)
//      locale_es = !locale_es
//    }
//  }
}
