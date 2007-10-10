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

package net.java.impala.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import net.java.impala.exception.ExecutionException;

import org.springframework.core.io.Resource;

public class PropertyUtils {

	public static Properties loadProperties(Resource resource) {
		Properties props = new Properties();
		InputStream inputStream = null;
		try {
			inputStream = resource.getInputStream();
			props.load(inputStream);
		}
		catch (IOException e) {
			throw new ExecutionException("Unable to load properties file " + resource.getDescription());
		}
		finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				}
				catch (IOException e) {
				}
			}
		}
		return props;
	}

}
