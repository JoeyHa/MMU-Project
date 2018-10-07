package com.hit.algorithm;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;
import java.util.Map.Entry;


public class RandomAlgoCacheImpl<K,V> extends AbstractAlgoCache<K,V> {
	private HashMap<K, V> hm;
	private  int capacity;
	
	
	public RandomAlgoCacheImpl(int capacity) {
		super(capacity);
		this.setCapacity(capacity);
		hm = new HashMap<K, V>(capacity);
	}
	@Override
	public V getElement(K key) {
		return hm.get(key);
	}

	@Override
	public V putElement(K key, V value) {	
		if(hm.isEmpty()) {
			hm.put(key, value);
		}
		if(getElement(key) != value) 
		{
			if(hm.size() == capacity) {
			Object[] keys = hm.keySet().toArray();
			Object randomKey = keys[new Random().nextInt(capacity)];
			removeElement((K) randomKey);
			}
			hm.put(key,value);
		}
		return value;
	}

	@Override
	public void removeElement(K key) {
		hm.remove(key);
	}
	private void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	public void printWhatEver() {
		Set entrySet = hm.entrySet();
		Iterator it = entrySet.iterator();
		System.out.println("HashMap entries : "); 
		while(it.hasNext()) {
			System.out.println(it.next());
		}
	}

};
