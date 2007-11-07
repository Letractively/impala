/*
 * Copyright 2007 the original author or authors.
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

package org.impalaframework.plugin.spec;

import java.io.Serializable;
import java.util.Collection;

/**
 * @author Phil Zoio
 */
public interface ChildSpecContainer extends Serializable {

	Collection<String> getPluginNames();

	PluginSpec getPlugin(String pluginName);

	boolean hasPlugin(String pluginName);

	Collection<PluginSpec> getPlugins();

	void add(PluginSpec pluginSpec);
	
	PluginSpec remove(String pluginName);
	
	public int hashCode();
	
	public boolean equals(Object obj);

}