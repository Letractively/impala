package org.impalaframework.osgi.util;

import java.net.URL;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.List;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;

public class OsgiUtils {
	
	//FIXME test
	public static URL[] findResources(BundleContext bundleContext, String[] names) {
		List<URL> urls = new ArrayList<URL>();
		
		for (int i = 0; i < names.length; i++) {
			URL findResource = findResource(bundleContext, names[i]);
			if (findResource != null) {
				urls.add(findResource);
			} else {
				//TODO log
			}
		}
		return urls.toArray(new URL[0]);
	}

	//FIXME test
	public static URL findResource(BundleContext bundleContext, String name) {
		URL resource = null;
		//find the resources
		
		final Bundle hostBundle = bundleContext.getBundle();
		resource = hostBundle.getResource(name);
		
		if (resource != null) return resource;
		
		Bundle[] bundles = bundleContext.getBundles();
		for (Bundle bundle : bundles) {
			resource = bundle.getResource(name);
			if (resource != null) {
				return resource;
			}
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public static Bundle findBundle(BundleContext bundleContext, String name) {
		String resource = null;
		//find the resources
		Bundle[] bundles = bundleContext.getBundles();
		for (Bundle bundle : bundles) {
			final Dictionary headers = bundle.getHeaders();
			resource = (String) headers.get(org.osgi.framework.Constants.BUNDLE_NAME);
			if (name.equals(resource)) {
				return bundle;
			}
		}
		return null;
	}
}
