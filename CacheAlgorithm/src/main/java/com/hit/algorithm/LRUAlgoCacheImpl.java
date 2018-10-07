package com.hit.algorithm;
import java.util.LinkedHashMap;
import java.util.Set;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;


public class LRUAlgoCacheImpl<K,V> extends AbstractAlgoCache<K,V> {
	
	private LinkedHashMap<K, V> lhmap;
	private  int capacity;
	
	public LRUAlgoCacheImpl(int capacity) {
		super(capacity);
		this.setCapacity(capacity);
		lhmap = new LinkedHashMap<K, V>(capacity);
	}

	@Override
	public V getElement(K key) { 
		return lhmap.get(key);
	}

	@Override
	public V putElement(K key, V value) {// return value need to be replaced 
		if(lhmap.isEmpty()) {
			lhmap.put(key, value);
		}
		Set<Entry<K, V>> entrySet = lhmap.entrySet();
		Iterator<Entry<K, V>> it = entrySet.iterator();
		if(getElement(key) == value) {
			removeElement(key);
			lhmap.put(key,value);
		}
		else {
			if(lhmap.size() == capacity) {
				removeElement(it.next().getKey());
			}
			lhmap.put(key,value);
		}
		return value;
		
	}
	@Override
	public void removeElement(K key) {
		lhmap.remove(key);
		
		
	}
//	public void printWhatEver() {
//		Set entrySet = lhmap.entrySet();
//		Iterator it = entrySet.iterator();
//		System.out.println("LinkedHashMap entries : "); 
//		while(it.hasNext()) {
//			System.out.println(it.next());
//		}
//	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	
};

	
	
