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

package net.java.impala.file;

import java.io.File;

import org.springframework.util.Assert;

public class FileRecurser {

	public void recurse(FileRecurseHandler handler, File directory) {
		Assert.isTrue(directory.isDirectory(), directory + " is not a directory");
		handler.handleDirectory(directory);
		
		File[] files = directory.listFiles(handler.getDirectoryFilter());
		for (File subfile : files) {
			if (subfile.isFile()) {
				handler.handleFile(subfile);
			}
			else if (subfile.isDirectory()) {
				recurse(handler, subfile);
			}
		}
	}

}
