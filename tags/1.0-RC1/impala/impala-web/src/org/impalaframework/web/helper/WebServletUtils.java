/*
 * Copyright 2007-2008 the original author or authors.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package org.impalaframework.web.helper;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.impalaframework.facade.ModuleManagementFacade;
import org.impalaframework.util.ObjectUtils;
import org.impalaframework.web.WebConstants;
/**
 * Class with static convenience methods for publish, accessing, and removing <code>ServletContext</code>-based state. 
 * @author Phil Zoio
 */
public abstract class WebServletUtils {
    
    private static final Log logger = LogFactory.getLog(WebServletUtils.class);

    public static ModuleManagementFacade getModuleManagementFacade(ServletContext servletContext) {
        final String attributeName = WebConstants.IMPALA_FACTORY_ATTRIBUTE;
        final Object attribute = servletContext.getAttribute(attributeName);
        
        if (logger.isDebugEnabled()) {
            logger.debug("Retrieved ModuleManagementFacade from ServletContext with attribute name '" + attributeName + "': " + attribute);
        }
        
        return ObjectUtils.cast(attribute, ModuleManagementFacade.class);
    }

    public static String getModuleServletContextKey(String moduleName, String attributeName) {
        return "module_" + moduleName + ":" + attributeName;
    }

    public static void publishServlet(ServletContext servletContext, String servletName, HttpServlet servlet) {
        
        String attributeName = WebConstants.SERVLET_MODULE_ATTRIBUTE_PREFIX + servletName;
        servletContext.setAttribute(attributeName , servlet);
        
        if (logger.isDebugEnabled()) {
            logger.debug("Published Servlet with name '" + servletName
                    + "' as ServletContext attribute with name [" + attributeName + "]");
        }
    }

    public static void unpublishServlet(ServletContext servletContext, String servletName) {
        
        String attributeName = WebConstants.SERVLET_MODULE_ATTRIBUTE_PREFIX + servletName;
        servletContext.removeAttribute(attributeName);
        
        if (logger.isDebugEnabled()) {
            logger.debug("Removed Servlet with name '" + servletName
                    + "' as ServletContext attribute with name [" + attributeName + "]");
        }
    }

    
    
}