package net.java.impala.spring.monitor;

import java.util.Collections;
import java.util.List;

/**
 * Used to indicate that a plugin has been modifiedPlugins
 * @author Phil Zoio
 */
public class PluginModificationEvent {

	private List<PluginModificationInfo> modifiedPlugins;

	public PluginModificationEvent(List<PluginModificationInfo> modified) {
		super();
		this.modifiedPlugins = modified;
	}

	public List<PluginModificationInfo> getModifiedPlugins() {
		return Collections.unmodifiableList(modifiedPlugins);
	}

}
