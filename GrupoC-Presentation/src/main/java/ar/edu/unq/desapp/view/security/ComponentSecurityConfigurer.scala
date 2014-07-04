package ar.edu.unq.desapp.view.security

import org.apache.wicket.application.IComponentInstantiationListener

import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.wicket.Component;
import org.apache.wicket.authorization.Action;
import org.apache.wicket.authroles.authorization.strategies.role.metadata.MetaDataRoleAuthorizationStrategy;
import org.apache.wicket.markup.html.WebPage;

class ComponentSecurityConfigurer extends IComponentInstantiationListener {
	
  private val LOGGER: Logger = Logger.getLogger(classOf[ComponentSecurityConfigurer]);
  
  private var properties: Properties = _
  
  override def onInstantiation(component: Component) {
    val isPage = component.isInstanceOf[WebPage]
    
    var targetObjectID: String = this.getTargetObjectId(isPage, component)
      
    var componentSecurityConfiguration: String = properties.getProperty(targetObjectID)
    if(componentSecurityConfiguration != null) {
    	var fullName: String = this.getFullName(isPage, component)
    	if (!targetObjectID.equals(fullName)) {
				LOGGER.info("warning: matching " + targetObjectID + " to "+ fullName)
		}
    	this.applySecurityConstraintsTo(component, targetObjectID)
    }      
  }
  
  	def applySecurityConstraintsTo(targetComponent: Component,targetObjectId: String) {
		// search registered configuration for target object
		val componentSecurityConfiguration: String = this.getSecurityConfigurationFor(targetObjectId);

		if (componentSecurityConfiguration != null) {
			// get target action and expected roles (any of those roles)
			val configValuesSeparatorIndex: Int = componentSecurityConfiguration.indexOf(',')
			val targetActionName: String  = componentSecurityConfiguration.substring(0, configValuesSeparatorIndex)
			val expectedRoles: String = componentSecurityConfiguration.substring(configValuesSeparatorIndex + 1)
			val targetAction: Action = this.asWicketAction(targetActionName)

			LOGGER.info("Configuring " + targetObjectId + ". Action: "
					+ targetActionName + ". Role: " + expectedRoles)

			MetaDataRoleAuthorizationStrategy.authorize(targetComponent, targetAction, expectedRoles)
		}
	}
  	
  	def asWicketAction(actionName: String): Action = {
  	  var action: Action = null
  	  if (actionName.equalsIgnoreCase("RENDER")) {
			action = Component.RENDER;
		} else if (actionName.equalsIgnoreCase("ENABLE")) {
			action = Component.ENABLE;
		} else {
			LOGGER.error("No Action found: " + actionName);
		}
  	  return action;
  	}
  	
  	def getSecurityConfigurationFor(componentId: String): String = {
  	  return properties.getProperty(componentId)
  	}
  	
  	private def getFullName(isPage: Boolean, component: Component): String = {
  	  if(isPage) 
  	    return component.getClass().getName()
  	  else
  		return component.getPath()
  	}
  
  	private def getTargetObjectId(isPage: Boolean, component: Component): String = {
  	  if(isPage) 
  	    return component.getClass().getSimpleName()
  	  else
  		return component.getId()
  	}
}
