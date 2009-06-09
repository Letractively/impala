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

package org.impalaframework.service.contribution;

import org.impalaframework.service.ServiceRegistryEntry;

/**
 * Makes {@link BaseServiceRegistryMap} concrete but not support any proxying of returned entries.
 * 
 * @author Phil Zoio
 */
public class ServiceRegistryMap extends BaseServiceRegistryMap {
    
    public ServiceRegistryMap() {
        super();
    }
    
    protected Object maybeGetProxy(ServiceRegistryEntry entry) {
        return entry.getServiceBeanReference().getService();
    }
}