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

package org.impalaframework.service.registry;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;
import junit.framework.TestCase;

import org.impalaframework.module.definition.SimpleModuleDefinition;
import org.impalaframework.service.registry.internal.ServiceRegistryImpl;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.util.ClassUtils;

public class ServiceArrayRegistryExporterTest extends TestCase {

	private ServiceArrayRegistryExporter exporter;
	private ServiceRegistryImpl registry;
	private BeanFactory beanFactory;
	
	private String service1 = "myservice1";
	private String service2 = "myservice2";

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		exporter = new ServiceArrayRegistryExporter();
		registry = new ServiceRegistryImpl();
		beanFactory = createMock(BeanFactory.class);
		exporter.setBeanFactory(beanFactory);
		exporter.setModuleDefinition(new SimpleModuleDefinition("module1"));
		exporter.setServiceRegistry(registry);
		exporter.setBeanClassLoader(ClassUtils.getDefaultClassLoader());
	}
	
	public void testGetBean() throws Exception {
		exporter.setBeanNames(new String[]{"myBean1","myBean2"});
		
		expect(beanFactory.getBean("myBean1")).andReturn(service1);
		expect(beanFactory.getBean("myBean2")).andReturn(service2);
		
		replay(beanFactory);
		exporter.afterPropertiesSet();
		verify(beanFactory);
		
		assertSame(service1, registry.getService("myBean1").getBean());
		assertSame(service2, registry.getService("myBean2").getBean());
		
		exporter.destroy();
		assertNull(registry.getService("myBean1"));
		assertNull(registry.getService("myBean2"));
	}
	
	public void testGetBeanWithExportNames() throws Exception {
		exporter.setBeanNames(new String[]{"myBean1","myBean2"});
		exporter.setExportNames(new String[]{"myExport1","myExport2"});
		
		expect(beanFactory.getBean("myBean1")).andReturn(service1);
		expect(beanFactory.getBean("myBean2")).andReturn(service2);
		
		replay(beanFactory);
		exporter.afterPropertiesSet();
		verify(beanFactory);
		
		assertSame(service1, registry.getService("myExport1").getBean());
		assertSame(service2, registry.getService("myExport2").getBean());
		
		exporter.destroy();
		assertNull(registry.getService("myExport1"));
		assertNull(registry.getService("myExport2"));
	}
	
}
