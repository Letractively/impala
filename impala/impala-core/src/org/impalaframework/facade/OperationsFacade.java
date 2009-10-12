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

package org.impalaframework.facade;

import org.impalaframework.module.ModuleDefinition;
import org.impalaframework.module.ModuleDefinitionSource;
import org.impalaframework.module.RootModuleDefinition;
import org.impalaframework.module.RuntimeModule;
import org.springframework.context.ApplicationContext;

/**
 * Interface which can, but doesn't necessarily need to be, used by the application to expose common 
 * operations, such as loading and unloading modules, retrieving module definitions and retrieving 
 * beans defined within modules.
 * 
 * @author Phil Zoio
 */
public interface OperationsFacade {

    /**
     * Loads the module hierarchy described by the provided {@link ModuleDefinitionSource} instance
     */
    void init(ModuleDefinitionSource source);

    /**
     * Reloads the named module
     */
    boolean reload(String moduleName);

    /**
     * Reloads the first module encountered in the current module definition set whose name contains the <code>likeModuleName</code> String.
     * Used with caution to avoid unintentional results
     */
    String reloadLike(String likeModuleName);

    /**
     * Reloads all modules, starting from the root module
     */
    void reloadRootModule();

    /**
     * Attempt to load all modules for the current module definition set which failed to load in previous attempts.
     */
    void repairModules();
    
    /**
     * Unload all modules, up to and including the root module.
     */
    void unloadRootModule();

    /**
     * Unload and remove the named module from the currently loaded module definition set
     */
    boolean remove(String moduleName);

    /**
     * Add the specified module definition to the current module definition set, and load the associated runtime module
     */
    void addModule(final ModuleDefinition moduleDefinition);

    /**
     * Checks whether the current module definition set contains a module named <code>moduleName</code>
     */
    boolean hasModule(String moduleName);

    /**
     * Finds the actual name of the first module found in the current module definition 
     * whose name contains the <code>likeModuleName</code> String.
     */
    String findLike(String moduleName);

    /**
     * Returns the {@link RuntimeModule} instance corresponding with the root module definition
     */
    RuntimeModule getRootRuntimeModule();

    /**
     * Returns the {@link RuntimeModule} associated with the named module
     */
    RuntimeModule getRuntimeModule(String moduleName);

    /**
     * Returns a named bean associated with the root module definition in the
     * current module definition set. Corresponds for Spring modules to a named
     * Spring bean in the {@link ApplicationContext} for that module.
     */
    <T extends Object> T getBean(String beanName, Class<T> type);

    /**
     * Returns a named bean associated with the named module Corresponds for
     * Spring modules to a named Spring bean in the {@link ApplicationContext}
     * for that module.
     */
    <T extends Object> T getModuleBean(String moduleName, String beanName, Class<T> type);
    
    /**
     * Returns the {@link RootModuleDefinition}, that is the root module definition in the 
     * current loaded module definition set. From this, the hierarchy or graph of module
     * relationships can be introspected.
     */
    RootModuleDefinition getRootModuleDefinition();

}
