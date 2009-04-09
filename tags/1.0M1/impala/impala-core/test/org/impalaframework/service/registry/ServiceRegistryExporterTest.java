package org.impalaframework.service.registry;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import junit.framework.TestCase;

import static org.easymock.EasyMock.*;

import org.impalaframework.module.definition.SimpleModuleDefinition;
import org.springframework.beans.factory.BeanFactory;

public class ServiceRegistryExporterTest extends TestCase {

	private ServiceRegistryExporter exporter;
	private ServiceRegistryImpl registry;
	private BeanFactory beanFactory;
	
	private String service = "myservice";

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		exporter = new ServiceRegistryExporter();
		registry = new ServiceRegistryImpl();
		beanFactory = createMock(BeanFactory.class);
		exporter.setBeanFactory(beanFactory);
		exporter.setModuleDefinition(new SimpleModuleDefinition("module1"));
		exporter.setServiceRegistry(registry);
	}
	
	public void testGetBean() throws Exception {
		exporter.setBeanName("myBean");
		
		expect(beanFactory.getBean("myBean")).andReturn(service);
		
		replay(beanFactory);
		exporter.afterPropertiesSet();
		verify(beanFactory);
		
		assertSame(service, registry.getService("myBean").getBean());
		
		exporter.destroy();
		assertNull(registry.getService("myBean"));
	}
	
	public void testGetBeanAlternative() throws Exception {
		exporter.setBeanName("myBean");
		exporter.setExportName("exportName");
		
		exporter.setTagsArray(new String[]{"tag1"});
		expect(beanFactory.getBean("myBean")).andReturn(service);
		
		replay(beanFactory);
		exporter.afterPropertiesSet();
		verify(beanFactory);
		
		ServiceReference serviceReference = registry.getService("exportName");
		assertFalse(serviceReference.getTags().isEmpty());
		assertSame(service, serviceReference.getBean());
		
		exporter.destroy();
		assertNull(registry.getService("exportName"));
	}
	
	public void testTagsAndAttribute() throws Exception {
		exporter.setBeanName("myBean");
		List<String> tags = Collections.singletonList("tag1");
		Map<String, String> attributes = Collections.singletonMap("attribute1", "value1");
		exporter.setTags(tags);
		exporter.setAttributes(attributes);
		
		expect(beanFactory.getBean("myBean")).andReturn(service);
		
		replay(beanFactory);
		exporter.afterPropertiesSet();
		verify(beanFactory);
		
		ServiceReference s = registry.getService("myBean");
		assertSame(service, s.getBean());
		assertSame(tags, s.getTags());
		assertSame(attributes, s.getAttributes());
		
		exporter.destroy();
		assertNull(registry.getService("myBean"));
	}
	
}