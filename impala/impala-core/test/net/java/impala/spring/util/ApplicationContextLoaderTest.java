package net.java.impala.spring.util;

import junit.framework.TestCase;
import net.java.impala.classloader.DefaultContextResourceHelper;
import net.java.impala.location.PropertyClassLocationResolver;
import net.java.impala.monitor.FileMonitor;
import net.java.impala.spring.plugin.ApplicationContextSet;
import net.java.impala.spring.plugin.NoServiceException;
import net.java.impala.spring.plugin.PluginSpec;
import net.java.impala.spring.plugin.SimplePluginSpec;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.io.ClassPathResource;

public class ApplicationContextLoaderTest extends TestCase {

	private ApplicationContextLoader loader;

	private static final String plugin1 = "impala-sample-dynamic-plugin1";

	private static final String plugin2 = "impala-sample-dynamic-plugin2";

	public void setUp() {
		PropertyClassLocationResolver locationResolver = new PropertyClassLocationResolver();
		loader = new ApplicationContextLoader(new DefaultContextResourceHelper(locationResolver));
	}

	public void testNewParentClassLoader() {
		assertNotNull(loader.newParentClassLoader());
	}

	public void testLoadUnloadPlugins() {

		PropertyClassLocationResolver locationResolver = new PropertyClassLocationResolver();
		loader = new ApplicationContextLoader(new DefaultContextResourceHelper(locationResolver));
		PluginSpec spec = new SimplePluginSpec("parentTestContext.xml", new String[] { plugin1,
				"impala-sample-dynamic-plugin2" });
		ApplicationContextSet loaded = loader.loadParentContext(spec, this.getClass().getClassLoader());

		ConfigurableApplicationContext parent = loaded.getContext();
		assertNotNull(parent);
		assertEquals(2, loaded.getPluginContext().size());

		FileMonitor bean1 = (FileMonitor) parent.getBean("bean1");
		assertEquals(999L, bean1.lastModified(null));

		FileMonitor bean2 = (FileMonitor) parent.getBean("bean2");
		assertEquals(100L, bean2.lastModified(null));

		// shutdown plugin and check behaviour has gone
		ConfigurableApplicationContext child2 = loaded.getPluginContext().get(plugin2);
		child2.close();

		try {
			bean2.lastModified(null);
			fail();
		}
		catch (NoServiceException e) {
		}

		// bean 2 still works
		assertEquals(999L, bean1.lastModified(null));

		ConfigurableApplicationContext child1 = loaded.getPluginContext().get(plugin1);
		child1.close();

		try {
			bean1.lastModified(null);
			fail();
		}
		catch (NoServiceException e) {
		}

		// now reload the plugin, and see that behaviour returns
		loader.addPlugin(parent, plugin2);
		bean2 = (FileMonitor) parent.getBean("bean2");
		assertEquals(100L, bean2.lastModified(null));

		loader.addPlugin(parent, plugin1);
		bean1 = (FileMonitor) parent.getBean("bean1");
		assertEquals(999L, bean1.lastModified(null));

	}

	public void testLoadContextFromClasspath() {
		ConfigurableApplicationContext parent = loader.loadContextFromClasspath(
				new String[] { "childcontainer/parent-context.xml" }, this.getClass().getClassLoader());
		assertNotNull(parent.getBean("parent"));

		ConfigurableApplicationContext child = loader.loadContextFromResource(parent, new ClassPathResource(
				"beanset/imported-context.xml"), this.getClass().getClassLoader());
		assertNotNull(child.getBean("bean2"));
	}

	public void testLoadContextFromResource() {
		ConfigurableApplicationContext context = loader.loadContextFromResource(null, new ClassPathResource(
				"beanset/imported-context.xml"), this.getClass().getClassLoader());
		assertNotNull(context.getBean("bean2"));
	}

}
