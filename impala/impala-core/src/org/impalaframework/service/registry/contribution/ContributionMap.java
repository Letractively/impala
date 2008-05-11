package org.impalaframework.service.registry.contribution;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class ContributionMap<K,V> implements Map<K,V> {
	
	private Map<K,V> localContributions = new ConcurrentHashMap<K, V>();
	private ServiceRegistryMap<K,V> externalContributions = new ServiceRegistryMap<K, V>();
	
	public void clear() {
		this.localContributions.clear();
	}

	public boolean containsKey(Object key) {
		boolean hasKey = this.localContributions.containsKey(key);
		if (!hasKey) hasKey = this.externalContributions.containsKey(key);
		return hasKey;
	}

	public boolean containsValue(Object value) {
		boolean hasValue = this.localContributions.containsValue(value);
		if (!hasValue) hasValue = this.externalContributions.containsValue(value);
		return hasValue;
	}

	public Set<java.util.Map.Entry<K, V>> entrySet() {
		Set<Entry<K, V>> localSet = this.localContributions.entrySet();
		Set<Entry<K, V>> externalSet = this.externalContributions.entrySet();
		Set<Entry<K,V>> allSet = new LinkedHashSet<Entry<K,V>>();
		allSet.addAll(localSet);
		allSet.addAll(externalSet);
		return allSet;
	}

	public V get(Object key) {
		V value = this.localContributions.get(key);
		if (value == null) {
			value = this.externalContributions.get(key);
		}
		return value;
	}

	public boolean isEmpty() {
		boolean isEmpty = this.localContributions.isEmpty();
		if (isEmpty) {
			isEmpty = this.externalContributions.isEmpty();
		}
		return isEmpty;
	}

	public Set<K> keySet() {
		Set<K> localSet = this.localContributions.keySet();
		Set<K> externalSet = this.externalContributions.keySet();
		Set<K> allSet = new LinkedHashSet<K>();
		allSet.addAll(localSet);
		allSet.addAll(externalSet);
		return allSet;
	}

	public V put(K key, V value) {
		return this.localContributions.put(key, value);
	}

	public void putAll(Map<? extends K, ? extends V> map) {
		this.localContributions.putAll(map);
	}

	public V remove(Object key) {
		return this.localContributions.remove(key);
	}

	public int size() {
		int localSize = this.localContributions.size();
		int externalSize = this.externalContributions.size();
		return localSize + externalSize;
	}

	public Collection<V> values() {
		Collection<V> localValues = this.localContributions.values();
		Collection<V> externalValues = this.externalContributions.values();
		Collection<V> allValues = new ArrayList<V>();
		allValues.addAll(localValues);
		allValues.addAll(externalValues);
		return allValues;
	}
	
	ServiceRegistryMap<K, V> getExternalContributions() {
		return externalContributions;
	}

	public void setLocalContributions(Map<K, V> localContributions) {
		this.localContributions = localContributions;
	}
	
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		String localString = localContributions.toString();
		String externalString = externalContributions.toString();
		sb.append("Local contributions: ").append(localString).append(", external contributions: ").append(externalString);
		return sb.toString();
	}	

}
