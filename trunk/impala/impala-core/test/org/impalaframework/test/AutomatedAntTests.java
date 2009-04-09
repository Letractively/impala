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

package org.impalaframework.test;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.impalaframework.bootstrap.SimpleContextLocationResolverTest;
import org.impalaframework.classloader.BaseModuleClassLoaderFactoryTest;
import org.impalaframework.classloader.ClassLoaderUtilsTest;
import org.impalaframework.classloader.ModuleClassLoaderFactoryTest;
import org.impalaframework.classloader.ParentClassLoaderFactoryTest;
import org.impalaframework.classloader.SharedClassLoaderFactoryTest;
import org.impalaframework.classloader.URLClassRetrieverTest;
import org.impalaframework.classloader.graph.DelegateClassLoaderFactoryTest;
import org.impalaframework.classloader.graph.DelegateClassLoaderTest;
import org.impalaframework.classloader.graph.GraphBasedClassLoaderTest;
import org.impalaframework.config.CompositePropertySourceTest;
import org.impalaframework.config.DateValueTest;
import org.impalaframework.config.LocationModificationStateHolderTest;
import org.impalaframework.config.PrefixedCompositePropertySourceTest;
import org.impalaframework.config.PropertiesHolderTest;
import org.impalaframework.config.PropertySourceHolderTest;
import org.impalaframework.config.PropertyValueTest;
import org.impalaframework.config.StaticPropertiesPropertySourceTest;
import org.impalaframework.config.StringPropertyValueTest;
import org.impalaframework.config.SystemPropertiesPropertySourceTest;
import org.impalaframework.facade.BeanFactoryModuleManagementSourceTest;
import org.impalaframework.facade.BootstrapContextTest;
import org.impalaframework.facade.ImpalaInitClearTest;
import org.impalaframework.facade.SimpleOperationsFacadeTest;
import org.impalaframework.file.handler.DefaultClassFilterTest;
import org.impalaframework.file.monitor.FileMonitorImplTest;
import org.impalaframework.graph.GraphHelperTest;
import org.impalaframework.graph.VertexTestCase;
import org.impalaframework.module.definition.ChildModuleContainerTest;
import org.impalaframework.module.definition.ConstructedModuleDefinitionSourceTest;
import org.impalaframework.module.definition.DependencyManagerErrorTest;
import org.impalaframework.module.definition.DependencyManagerTest;
import org.impalaframework.module.definition.ModuleDefinitionToStringTest;
import org.impalaframework.module.definition.ModuleDefinitionUtilsTest;
import org.impalaframework.module.definition.SimpleGraphModuleDefinitionTest;
import org.impalaframework.module.definition.SimpleGraphRootModuleDefinitionTest;
import org.impalaframework.module.definition.SimpleModuleDefinitionTest;
import org.impalaframework.module.definition.SimpleRootModuleDefinitionTest;
import org.impalaframework.module.definition.SimpleSpringContextTest;
import org.impalaframework.module.holder.DefaultModuleStateHolderTest;
import org.impalaframework.module.holder.ModuleStateChangeNotifierTest;
import org.impalaframework.module.loader.ModuleUtilsTest;
import org.impalaframework.module.modification.ModificationExtractorRegistryTest;
import org.impalaframework.module.modification.ModificationExtractorTest;
import org.impalaframework.module.modification.StickyModificationExtractorTest;
import org.impalaframework.module.modification.graph.GraphModificationExtractorDelegateTest;
import org.impalaframework.module.modification.graph.GraphModificationExtractorTest;
import org.impalaframework.module.modification.graph.StickyGraphModificationExtractorTest;
import org.impalaframework.module.monitor.BaseModuleChangeListenerTest;
import org.impalaframework.module.monitor.ScheduledModuleChangeMonitorBeanTest;
import org.impalaframework.module.monitor.ScheduledModuleChangeMonitorTest;
import org.impalaframework.module.operation.AddModuleOperationTest;
import org.impalaframework.module.operation.CloseRootModuleOperationTest;
import org.impalaframework.module.operation.LockingModuleOperationTest;
import org.impalaframework.module.operation.ModuleOperationRegistryTest;
import org.impalaframework.module.operation.ReloadNamedModuleLikeOperationTest;
import org.impalaframework.module.operation.ReloadNamedModuleOperationTest;
import org.impalaframework.module.operation.RemoveModuleOperationTest;
import org.impalaframework.module.operation.UpdateRootModuleOperationTest;
import org.impalaframework.module.resource.ApplicationModuleLocationsResourceLoaderTest;
import org.impalaframework.module.runtime.BaseModuleRuntimeTest;
import org.impalaframework.module.runtime.DefaultModuleRuntimeManagerTest;
import org.impalaframework.module.runtime.SimpleModuleRuntimeTest;
import org.impalaframework.module.source.IncrementalModuleDefinitionSourceTest;
import org.impalaframework.module.source.InternalModuleDefinitionSourceExtraTest;
import org.impalaframework.module.source.InternalModuleDefinitionSourceTest;
import org.impalaframework.module.source.InternalPropertiesModuleDefinitionSourceTest;
import org.impalaframework.module.source.InternalXmlModuleDefinitionSourceTest;
import org.impalaframework.module.source.SingleStringModuleDefinitionSourceTest;
import org.impalaframework.module.source.XMLModuleDefinitionDocumentLoaderTest;
import org.impalaframework.module.source.XMLModuleDefinitionSourceTest;
import org.impalaframework.module.transition.ReloadTransitionProcessorTest;
import org.impalaframework.module.transition.TransitionProcessorRegistryTest;
import org.impalaframework.module.type.RootModuleTypeReaderTest;
import org.impalaframework.module.type.TypeReaderRegistryTest;
import org.impalaframework.resolver.AbstractModuleLocationResolverTest;
import org.impalaframework.resolver.CascadingModuleLocationResolverTest;
import org.impalaframework.resolver.StandaloneModuleLocationResolverTest;
import org.impalaframework.service.contribution.ContributionMapTest;
import org.impalaframework.service.filter.ldap.FilterParserTest;
import org.impalaframework.service.filter.ldap.FilterTest;
import org.impalaframework.service.filter.ldap.LdapServiceReferenceFilterTest;
import org.impalaframework.service.filter.ldap.TypeHelperTest;
import org.impalaframework.service.proxy.ProxyHelperTest;
import org.impalaframework.service.registry.DynamicServiceRegistryTargetSourceTest;
import org.impalaframework.service.registry.internal.ServiceRegistryImplTest;
import org.impalaframework.spring.MissingBeanTest;
import org.impalaframework.spring.SystemPropertyBasedPlaceholderConfigurerTest;
import org.impalaframework.spring.bean.BooleanFactoryBeanTest;
import org.impalaframework.spring.bean.NamedFactoryBeanTest;
import org.impalaframework.spring.bean.OptionalPropertiesFactoryBeanTest;
import org.impalaframework.spring.bean.SystemPropertiesFactoryBeanTest;
import org.impalaframework.spring.bean.SystemPropertyFactoryBeanTest;
import org.impalaframework.spring.config.DynamicPropertiesFactoryBeanTest;
import org.impalaframework.spring.config.ExternalDynamicPropertySourceTest;
import org.impalaframework.spring.config.PropertiesHolderFactoryBeanTest;
import org.impalaframework.spring.config.PropertySourceHolderFactoryBeanTest;
import org.impalaframework.spring.config.PropertySourceValueFactoryBeanTest;
import org.impalaframework.spring.facade.ImpalaTest;
import org.impalaframework.spring.module.ModuleDefinitionPostProcessorTest;
import org.impalaframework.spring.module.ModuleStateHolderTest;
import org.impalaframework.spring.module.ProcessTransitionsTest;
import org.impalaframework.spring.module.SimpleParentContextTest;
import org.impalaframework.spring.module.SpringModuleRuntimeTest;
import org.impalaframework.spring.module.graph.GraphDelegatingApplicationContextTest;
import org.impalaframework.spring.module.graph.SpringGraphModuleRuntimeTest;
import org.impalaframework.spring.module.loader.ApplicationModuleLoaderTest;
import org.impalaframework.spring.module.loader.BaseSpringModuleLoaderTest;
import org.impalaframework.spring.module.loader.ModuleLoaderRegistryTest;
import org.impalaframework.spring.module.registry.RegistryContributionProcessorTest;
import org.impalaframework.spring.module.registry.RegistryContributorTest;
import org.impalaframework.spring.resource.ClassPathResourceLoaderTest;
import org.impalaframework.spring.resource.CompositeResourceLoaderTest;
import org.impalaframework.spring.resource.DirectoryResourceTest;
import org.impalaframework.spring.service.exporter.AutoRegisteringModuleContributionExporterTest;
import org.impalaframework.spring.service.exporter.ContributionEndpointInterceptorTest;
import org.impalaframework.spring.service.exporter.ModuleContributionExportersTest;
import org.impalaframework.spring.service.exporter.ModuleContributionPostProcessorTest;
import org.impalaframework.spring.service.exporter.ModuleContributionUtilsTest;
import org.impalaframework.spring.service.exporter.ParentWithChildContextTest;
import org.impalaframework.spring.service.exporter.ServiceArrayRegistryExporterTest;
import org.impalaframework.spring.service.exporter.ServiceRegistryExporterTest;
import org.impalaframework.spring.service.proxy.ContributionProxyFactoryBeanTest;
import org.impalaframework.spring.service.proxy.DynamicServiceProxyFactoryCreatorTest;
import org.impalaframework.spring.service.registry.FilteredServiceRegistryTargetSourceTest;
import org.impalaframework.spring.service.registry.ServiceRegistryPostProcessorTest;
import org.impalaframework.spring.service.registry.StaticServiceRegistryTargetSourceTest;
import org.impalaframework.util.ArrayUtilsTest;
import org.impalaframework.util.FileUtilsTest;
import org.impalaframework.util.InstantiationUtilsTest;
import org.impalaframework.util.MemoryUtilsTest;
import org.impalaframework.util.ObjectMapUtilsTest;
import org.impalaframework.util.ObjectUtilsTest;
import org.impalaframework.util.PathUtilsTest;
import org.impalaframework.util.PropertyUtilsTest;
import org.impalaframework.util.ReflectionUtilsTest;
import org.impalaframework.util.ResourceUtilsTest;
import org.impalaframework.util.SerializationUtilsTest;
import org.impalaframework.util.StringBufferUtilsTest;
import org.impalaframework.util.URLUtilsTest;
import org.impalaframework.util.serialize.ClassLoaderAwareSerializationStreamFactoryTest;

public class AutomatedAntTests {
	
	public static Test suite() {
		
		TestSuite suite = new TestSuite();
		
		suite.addTestSuite(AbstractModuleLocationResolverTest.class);
		suite.addTestSuite(AddModuleOperationTest.class);
		suite.addTestSuite(ApplicationModuleLoaderTest.class);
		suite.addTestSuite(ApplicationModuleLocationsResourceLoaderTest.class);
		suite.addTestSuite(ArrayUtilsTest.class);
		suite.addTestSuite(AutoRegisteringModuleContributionExporterTest.class);
		suite.addTestSuite(BaseModuleClassLoaderFactoryTest.class);
		suite.addTestSuite(BaseSpringModuleLoaderTest.class);	
		suite.addTestSuite(BaseModuleChangeListenerTest.class);	
		suite.addTestSuite(BeanFactoryModuleManagementSourceTest.class);
		suite.addTestSuite(BooleanFactoryBeanTest.class);
		suite.addTestSuite(BootstrapContextTest.class);
		suite.addTestSuite(CascadingModuleLocationResolverTest.class);
		suite.addTestSuite(ChildModuleContainerTest.class);
		suite.addTestSuite(ClassPathResourceLoaderTest.class);	
		suite.addTestSuite(ClassLoaderAwareSerializationStreamFactoryTest.class);	
		suite.addTestSuite(ClassLoaderUtilsTest.class);
		suite.addTestSuite(CloseRootModuleOperationTest.class);
		suite.addTestSuite(CompositeResourceLoaderTest.class);
		suite.addTestSuite(CompositePropertySourceTest.class);
		suite.addTestSuite(ConstructedModuleDefinitionSourceTest.class);
		suite.addTestSuite(ContributionEndpointInterceptorTest.class);
		suite.addTestSuite(ContributionProxyFactoryBeanTest.class);
		suite.addTestSuite(ContributionMapTest.class);
		suite.addTestSuite(DateValueTest.class);
		suite.addTestSuite(DefaultClassFilterTest.class);
		suite.addTestSuite(DefaultModuleRuntimeManagerTest.class);
		suite.addTestSuite(DefaultModuleStateHolderTest.class);
		suite.addTestSuite(DelegateClassLoaderFactoryTest.class);
		suite.addTestSuite(DelegateClassLoaderTest.class);
		suite.addTestSuite(DependencyManagerErrorTest.class);
		suite.addTestSuite(DependencyManagerTest.class);
		suite.addTestSuite(DirectoryResourceTest.class);
		suite.addTestSuite(DynamicPropertiesFactoryBeanTest.class);
		suite.addTestSuite(DynamicServiceProxyFactoryCreatorTest.class);
		suite.addTestSuite(ExternalDynamicPropertySourceTest.class);
		suite.addTestSuite(FileMonitorImplTest.class);
		suite.addTestSuite(FileUtilsTest.class);
		suite.addTestSuite(FilteredServiceRegistryTargetSourceTest.class);
		suite.addTestSuite(FilterParserTest.class);
		suite.addTestSuite(FilterTest.class);
		suite.addTestSuite(GraphBasedClassLoaderTest.class);
		suite.addTestSuite(GraphHelperTest.class);
		suite.addTestSuite(GraphModificationExtractorDelegateTest.class);
		suite.addTestSuite(GraphModificationExtractorTest.class);
		suite.addTestSuite(GraphDelegatingApplicationContextTest.class);		
		suite.addTestSuite(ImpalaTest.class);
		suite.addTestSuite(ImpalaInitClearTest.class);
		suite.addTestSuite(IncrementalModuleDefinitionSourceTest.class);
		suite.addTestSuite(InternalPropertiesModuleDefinitionSourceTest.class);
		suite.addTestSuite(InternalModuleDefinitionSourceTest.class);
		suite.addTestSuite(InternalModuleDefinitionSourceExtraTest.class);
		suite.addTestSuite(InternalXmlModuleDefinitionSourceTest.class);
		suite.addTestSuite(InstantiationUtilsTest.class);
		suite.addTestSuite(LdapServiceReferenceFilterTest.class);
		suite.addTestSuite(LocationModificationStateHolderTest.class);
		suite.addTestSuite(LockingModuleOperationTest.class);
		suite.addTestSuite(MemoryUtilsTest.class);
		suite.addTestSuite(ModificationExtractorTest.class);
		suite.addTestSuite(ModificationExtractorRegistryTest.class);
		suite.addTestSuite(ModuleClassLoaderFactoryTest.class);
		suite.addTestSuite(ModuleContributionExportersTest.class);
		suite.addTestSuite(ModuleContributionUtilsTest.class);
		suite.addTestSuite(ModuleContributionPostProcessorTest.class);
		suite.addTestSuite(ModuleDefinitionPostProcessorTest.class);
		suite.addTestSuite(ModuleDefinitionToStringTest.class);
		suite.addTestSuite(ModuleDefinitionUtilsTest.class);
		suite.addTestSuite(ModuleLoaderRegistryTest.class);
		suite.addTestSuite(ModuleOperationRegistryTest.class);
		suite.addTestSuite(ModuleStateChangeNotifierTest.class);
		suite.addTestSuite(ModuleStateHolderTest.class);
		suite.addTestSuite(ModuleUtilsTest.class);
		suite.addTestSuite(MissingBeanTest.class);
		suite.addTestSuite(NamedFactoryBeanTest.class);
		suite.addTestSuite(ObjectUtilsTest.class);
		suite.addTestSuite(ObjectMapUtilsTest.class);
		suite.addTestSuite(OptionalPropertiesFactoryBeanTest.class);
		suite.addTestSuite(ParentWithChildContextTest.class);
		suite.addTestSuite(ParentClassLoaderFactoryTest.class);
		suite.addTestSuite(PathUtilsTest.class);
		suite.addTestSuite(PrefixedCompositePropertySourceTest.class);
		suite.addTestSuite(ProcessTransitionsTest.class);
		suite.addTestSuite(PropertiesHolderTest.class);
		suite.addTestSuite(PropertySourceHolderTest.class);
		suite.addTestSuite(PropertiesHolderFactoryBeanTest.class);
		suite.addTestSuite(PropertySourceHolderFactoryBeanTest.class);
		suite.addTestSuite(PropertySourceValueFactoryBeanTest.class);
		suite.addTestSuite(PropertyUtilsTest.class);
		suite.addTestSuite(PropertyValueTest.class);
		suite.addTestSuite(ProxyHelperTest.class);
		suite.addTestSuite(ReflectionUtilsTest.class);
		suite.addTestSuite(RegistryContributorTest.class);
		suite.addTestSuite(RegistryContributionProcessorTest.class);
		suite.addTestSuite(ReloadNamedModuleOperationTest.class);
		suite.addTestSuite(ReloadNamedModuleLikeOperationTest.class);
		suite.addTestSuite(ReloadTransitionProcessorTest.class);
		suite.addTestSuite(RemoveModuleOperationTest.class);
		suite.addTestSuite(ResourceUtilsTest.class);
		suite.addTestSuite(RootModuleTypeReaderTest.class);
		suite.addTestSuite(ScheduledModuleChangeMonitorBeanTest.class);
		suite.addTestSuite(ScheduledModuleChangeMonitorTest.class);
		suite.addTestSuite(SerializationUtilsTest.class);
		suite.addTestSuite(ServiceArrayRegistryExporterTest.class);
		suite.addTestSuite(ServiceRegistryImplTest.class);
		suite.addTestSuite(ServiceRegistryExporterTest.class);
		suite.addTestSuite(ServiceRegistryPostProcessorTest.class);
		suite.addTestSuite(DynamicServiceRegistryTargetSourceTest.class);
		suite.addTestSuite(SharedClassLoaderFactoryTest.class);
		suite.addTestSuite(SimpleContextLocationResolverTest.class);
		suite.addTestSuite(SimpleGraphModuleDefinitionTest.class);
		suite.addTestSuite(SimpleGraphRootModuleDefinitionTest.class);
		suite.addTestSuite(SimpleModuleRuntimeTest.class);
		suite.addTestSuite(SimpleModuleDefinitionTest.class);
		suite.addTestSuite(SimpleOperationsFacadeTest.class);
		suite.addTestSuite(SimpleParentContextTest.class);
		suite.addTestSuite(SimpleRootModuleDefinitionTest.class);
		suite.addTestSuite(SimpleSpringContextTest.class);
		suite.addTestSuite(SingleStringModuleDefinitionSourceTest.class);
		suite.addTestSuite(SpringGraphModuleRuntimeTest.class);	
		suite.addTestSuite(SpringModuleRuntimeTest.class);	
		suite.addTestSuite(BaseModuleRuntimeTest.class);	
		suite.addTestSuite(StandaloneModuleLocationResolverTest.class);
		suite.addTestSuite(StaticPropertiesPropertySourceTest.class);
		suite.addTestSuite(StaticServiceRegistryTargetSourceTest.class);
		suite.addTestSuite(StickyModificationExtractorTest.class);
		suite.addTestSuite(StickyGraphModificationExtractorTest.class);
		suite.addTestSuite(StringPropertyValueTest.class);
		suite.addTestSuite(StringBufferUtilsTest.class);
		suite.addTestSuite(SystemPropertyBasedPlaceholderConfigurerTest.class);
		suite.addTestSuite(SystemPropertiesPropertySourceTest.class);
		suite.addTestSuite(SystemPropertyFactoryBeanTest.class);
		suite.addTestSuite(SystemPropertiesFactoryBeanTest.class);
		suite.addTestSuite(TransitionProcessorRegistryTest.class);
		suite.addTestSuite(TypeHelperTest.class);
		suite.addTestSuite(TypeReaderRegistryTest.class);
		suite.addTestSuite(UpdateRootModuleOperationTest.class);
		suite.addTestSuite(URLClassRetrieverTest.class);
		suite.addTestSuite(URLUtilsTest.class);		
		suite.addTestSuite(VertexTestCase.class);
		suite.addTestSuite(XMLModuleDefinitionDocumentLoaderTest.class);
		suite.addTestSuite(XMLModuleDefinitionSourceTest.class);
		
		return suite;
	}
}