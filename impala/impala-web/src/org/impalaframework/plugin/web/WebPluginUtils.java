package org.impalaframework.plugin.web;

import javax.servlet.ServletContext;

public class WebPluginUtils {

	public static String getLocationsResourceName(ServletContext servletContext, String paramName) {
		// first look for System property which contains plugins definitions
		// location
		String resourceName = System.getProperty(paramName);
	
		if (resourceName == null) {
			resourceName = servletContext
					.getInitParameter(paramName);
		}
		return resourceName;
	}

}
