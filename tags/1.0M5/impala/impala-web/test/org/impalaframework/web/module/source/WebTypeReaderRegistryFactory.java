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

package org.impalaframework.web.module.source;

import java.util.Map;

import org.impalaframework.module.spi.TypeReader;
import org.impalaframework.module.type.ApplicationModuleTypeReader;
import org.impalaframework.module.type.TypeReaderRegistry;
import org.impalaframework.module.type.TypeReaderRegistryFactory;
import org.impalaframework.web.module.WebModuleTypes;
import org.impalaframework.web.module.type.WebPlaceholderTypeReader;

public class WebTypeReaderRegistryFactory {
	public static Map<String, TypeReader> getTypeReaders() {
		Map<String, TypeReader> typeReaders = TypeReaderRegistryFactory.getTypeReaders();
		typeReaders.put(WebModuleTypes.WEB_ROOT.toLowerCase(), new ApplicationModuleTypeReader());
		typeReaders.put(WebModuleTypes.SERVLET.toLowerCase(), new ApplicationModuleTypeReader());
		typeReaders.put(WebModuleTypes.WEB_PLACEHOLDER.toLowerCase(), new WebPlaceholderTypeReader());
		return typeReaders;
	}

	public static TypeReaderRegistry getTypeReaderRegistry() {
		final Map<String, TypeReader> typeReaders = getTypeReaders();
		final TypeReaderRegistry registry = new TypeReaderRegistry();
		registry.setTypeReaders(typeReaders);
		return registry;
	}
}