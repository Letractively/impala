## Background ##

When writing Java applications with JSP as the view technology, the normal practice is to put JSPs in the root context directory, so some subdirectory below this.
This works fine with Impala-based applications. However, when writing multi-module web applications, you typically want all your module resources to be
contained within the module. This includes JavaScript files, CSS files, resources, etc. as well as JSPs.

With Freemarker and other view technologies, setting up view templates to be hosted within a web module is very straightforward.
With JSPs, it's a bit more complex, because typically JSP container is typically provided by the servlet container rather than the application itself.

With Impala, setting up JSPs is accomplished by embedding the Tomcat Jasper JSP engine into the application.

An example is in the [Maven Sample](SamplesMaven.md). If you look in the _maven-web_ module in this sample, you will find the JSP hosted in
_maven-web/src/main/resources/maven/test.jspi_. The 'traditional' location for a JSP will be in _maven-host/src/main/webapp/maven_.

## Setup for 1.0 RC3 to 1.0 final ##

**Note - this feature is available from Impala 1.0 RC3**

**1) Add the Tomcat Jasper runtime libraries into your application**

For example, from _dependencies.txt_:
```
jetty from tomcat:jasper-runtime:5.5.15 source=false
jetty from tomcat:jasper-compiler:5.5.15 source=false
jetty from tomcat:jasper-compiler-jdt:5.5.15 source=false
```

or

```
provided from org.apache.tomcat:jasper:6.0.20
provided from org.apache.tomcat:jasper-el:6.0.20
provided from org.apache.tomcat:jasper-jdt:6.0.20
provided from org.apache.tomcat:juli:6.0.20
```

The equivalent for the latter is shown below:

```
<dependency>
	<groupId>org.apache.tomcat</groupId>
	<artifactId>jasper</artifactId>
	<version>6.0.20</version>
	<scope>provided</scope>
</dependency>
<dependency>
	<groupId>org.apache.tomcat</groupId>
	<artifactId>jasper-el</artifactId>
	<version>6.0.20</version>
	<scope>provided</scope>
</dependency>
<dependency>
	<groupId>org.apache.tomcat</groupId>
	<artifactId>jasper-jdt</artifactId>
	<version>6.0.20</version>
	<scope>provided</scope>
</dependency>
<dependency>
	<groupId>org.apache.tomcat</groupId>
	<artifactId>juli</artifactId>
	<version>6.0.20</version>
	<scope>provided</scope>
</dependency>	
```

**2) Add JSP support to the module using the following entry in your web module's application context XML file**

An example is shown below:

```
<bean id="jspServlet" class="org.impalaframework.web.jsp.JasperServletFactoryBean">
	<property name="servletName" value = "jspServlet"/>
</bean>
```

This registers a JSP servlet with the module, which JSP requests to your module can use to service requests.

3) Add the following entry to your application's _web.xml_ file.

```
<servlet>
    <servlet-name>JSP</servlet-name>
    <servlet-class>org.impalaframework.web.jsp.ModuleJspServlet</servlet-class>
    <load-on-startup>1</load-on-startup>
</servlet>

<servlet-mapping>
    <servlet-name>JSP</servlet-name>
    <url-pattern>*.jsp</url-pattern>
    </servlet-mapping> 
```

This allows the servlet container's request dispatcher to route forwarded requests to JSP's to your module-defined JSP servlet.

## Caveats ##

1) The usage mode which is supported in this integration allows JSPs to be accessed above only via forwards from within the application,
that is via `requestDispatcher.forward()`. This assumes that JSPs are not accessed directly elsewhere in the application.
The reason the latter does not work is because of the _web.xml_ entry: by mapping to the URL pattern `*.jsp`, you have effectively overridden
the container's built-in support for JSPs.

If you do need to use vanilla JSPs, the workaround is to instead map to JSPs forwarded to within the application to another extension, such as `*.jspi`. You can
You will then need to make changes to the application to ensure that when using JSPs, you forward requests on to 'pagename.jspi' instead of 'pagename.jsp'.
You can then use the **.jsp mapping in the normal way.**

**Note**: this restriction no longer applies as of Impala 1.0.1. See below.

2) If you need to map directly using the JSP extension to JSPs hosted within a module, you can simply add a `module:to-handler` entry in your
module's application context XML file. For example:

```
<web:to-handler extension = "jsp" servletName="jspServlet"/> 
```

3) Note that while the use of in-module JSPs is possible, other alternatives exist. For example, Spring has good support for
[Freemarker](http://freemarker.sourceforge.net/), which
can be used instead of JSPs. Freemarker even supports JSP tags (albeit with quite a different syntax). Using Freemarker or some other
templating engine such as [Velocity](http://velocity.apache.org/) provides a simple alternative to JSPs which is less dependent on
application server version, and less likely to 'step on the foot' of the application server.

## Setup for 1.0.1 ##

Forthcoming changes for 1.0.1 simplify the setup of JSPs and allows for better support for JSP includes and forwards. The features below are now present in the [snapshot build](http://impala.googlecode.com/svn/trunk/impala/impala/dist/impala-1.0.1-SNAPSHOT.zip).

**1) _WEB-INF/web.xml_**

You no longer need a `org.impalaframework.web.jsp.ModuleJspServlet` registered in `web.xml` for the mapping `*.jsp`. In other words, you can **remove** any entry that looks like this:

```
<servlet>
    <servlet-name>JSP</servlet-name>
    <servlet-class>org.impalaframework.web.jsp.ModuleJspServlet</servlet-class>
    <load-on-startup>2</load-on-startup>
</servlet>
   
<servlet-mapping>
	<servlet-name>JSP</servlet-name>
	<url-pattern>*.jsp</url-pattern>
</servlet-mapping>
```

This is good, because it means that you no longer need to override the `*.jsp` mapping to enable in-module JSP support (something that some application servers might not like).

Make sure that the filter mapping for the `ModuleProxyFilter` has the `dispatcher` element set for `REQUEST`, `INCLUDE`, `FORWARD` and `ERROR`.

```
<filter>
    <filter-name>web</filter-name>
    <filter-class>org.impalaframework.web.spring.integration.ModuleProxyFilter</filter-class>
</filter>

<filter-mapping>
    <filter-name>web</filter-name>
    <url-pattern>/*</url-pattern>
    <dispatcher>REQUEST</dispatcher>
    <dispatcher>FORWARD</dispatcher>
    <dispatcher>INCLUDE</dispatcher>
    <dispatcher>ERROR</dispatcher>
</filter-mapping>
```

The **full _web.xml_** might look as follows:

```
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns="http://java.sun.com/xml/ns/javaee"
          xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
          xsi:schemaLocation="http://java.sun.com/xml/ns/javaee/web-app_2_5.xsd"
          version="2.5">
    
    <display-name>Enter display name here</display-name>

    <description>Enter description here</description>
    
    <context-param>
        <param-name>contextLoaderClassName</param-name>
        <param-value>org.impalaframework.web.spring.loader.ExternalModuleContextLoader</param-value>
    </context-param>

    <listener>
        <listener-class>org.impalaframework.web.spring.loader.ImpalaContextLoaderListener</listener-class>
    </listener>
    
    <filter>
        <filter-name>web</filter-name>
        <filter-class>org.impalaframework.web.spring.integration.ModuleProxyFilter</filter-class>
    </filter>

    <filter-mapping>
        <filter-name>web</filter-name>
        <url-pattern>/*</url-pattern>
        <dispatcher>REQUEST</dispatcher>
        <dispatcher>FORWARD</dispatcher>
        <dispatcher>INCLUDE</dispatcher>
        <dispatcher>ERROR</dispatcher>
    </filter-mapping>

    <welcome-file-list>
        <welcome-file>index.html</welcome-file>
    </welcome-file-list>
    
</web-app>
```

**2) Module application context XML**

Make sure that you have a JSP servlet entry, and a mapping to this, as shown below:

```
<web:mapping>
    <web:to-module prefix = "/web" setContextPath="true"/> 
    ...
    <web:to-handler extension = "jsp" servletName="jsp"/>
</web:mapping>

...
    
<web:jsp-servlet id="jsp"/>
```