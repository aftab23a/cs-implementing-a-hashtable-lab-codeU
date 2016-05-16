/**
 * 
 */
package com.flatironschool.javacs;

import java.util.*;


/**
 * Implementation of a HashMap using a collection of MyLinearMap and
 * resizing when there are too many entries.
 * 
 * @author downey
 * @param <K>
 * @param <V>
 *
 */
public class MyHashMap<K, V> extends MyBetterMap<K, V> implements Map<K, V> {
	
	// average number of entries per map before we rehash
	protected static final double FACTOR = 1.0;

	@Override
	public V put(K key, V value) {
		V oldValue = super.put(key, value);
		
		//System.out.println("Put " + key + " in " + map + " size now " + map.size());
		
		// check if the number of elements per map exceeds the threshold
		if (size() > maps.size() * FACTOR) {
			rehash();
		}
		return oldValue;
	}

	/**
	 * Doubles the number of maps and rehashes the existing entries.
	 */
	/**
	 * 
	 */
	protected void rehash() {
		// collect entries in the table, resize table, put entries back in
		// 2x number of maps
		// maps.size()
		// add n more maps - resulting in 2x more maps.
		List<MyLinearMap<K, V>> mapSet = maps;
		makeMaps(mapSet.size()*2);
		for(MyLinearMap<K, V> m: mapSet) {
			for(Map.Entry<K, V> e: m.getEntries()) {
				put(e.getKey(), e.getValue());
			}
		}
        // TODO: fill this in.
      //  throw new UnsupportedOperationException();
	}



	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Map<String, Integer> map = new MyHashMap<String, Integer>();
		for (int i=0; i<10; i++) {
			map.put(new Integer(i).toString(), i);
		}
		Integer value = map.get("3");
		System.out.println(value);
	}
}
