package com.hit.algorithm;

import java.sql.Wrapper;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Map.Entry;
import java.util.Map;

public class NRUAlgoCacheImpl<K,V> extends AbstractAlgoCache<K,V> {
	
	private HashMap<K,WrapperV<V>>hm;
	private HashMap<Integer,Set<K>>hmDelete;
	private  int capacity;
	
	public NRUAlgoCacheImpl(int capacity) {
		super(capacity);
		this.capacity=capacity;
		hm = new HashMap<K,WrapperV<V>>(capacity);
		hmDelete = new HashMap<Integer,Set<K>>(capacity);
	}
	
	@Override
	public V getElement(K key) {
		WrapperV<V> wrap= hm.get(key);
		if (hm.get(key)!=null)
		{
			deleteFromHmDelete(hmDelete,wrap,key);
			wrap.setBits(10);
			putInHmDelete(hmDelete,wrap,key);
			return hm.get(key).getV();
		}
		return null; 
	}
	@SuppressWarnings("unchecked")
	@Override
	public V putElement(K key, V value) {
		WrapperV<V> wrap;
	    if(getElement(key) == value) {
			wrap=hm.get(key);
			deleteFromHmDelete(hmDelete,wrap,key);
			wrap.setBits(11);
			hm.put(key,wrap);
			putInHmDelete(hmDelete,wrap,key);
		}
		else
			{ 
			 if(hm.size() == capacity) {
				K kRemove= removeByBits(hmDelete);
				wrap=hm.get(kRemove);
	     		deleteFromHmDelete(hmDelete,wrap,kRemove);
				removeElement(kRemove);
			 }
				wrap = new WrapperV<V>(value,0);
				hm.put(key, wrap);
				putInHmDelete(hmDelete,wrap,key);
			}
		return value;
		}
	public void deleteFromHmDelete(HashMap<Integer,Set<K>> hmDelete,WrapperV<V> wrap,K key)
	{
		Set<K> theSet;
		theSet=hmDelete.get(wrap.getBits());
		theSet.remove(key);
	    hmDelete.put(wrap.getBits(),theSet);
	}
	public void putInHmDelete(HashMap<Integer,Set<K>> hmDelete,WrapperV<V> wrap,K key)
	{
		Set<K> theSet;
		theSet = hmDelete.get(wrap.getBits());
		if (theSet == null) {
		    theSet = new HashSet<K>();
		    hmDelete.put(wrap.getBits(),theSet);
		}
	      theSet.add(key);
	      hmDelete.put(wrap.getBits(),theSet);
	}
       
	public K removeByBits(HashMap<Integer,Set<K>> hmDelete)
    {
		Set<K> theSet;
		K kRemove = null;
		int bits=0;
		if (hmDelete.get(0).size()>0) bits=0;
		else if (hmDelete.get(10).size()>0) bits=10;
		else if (hmDelete.get(11).size()>0) bits=11;
		theSet=hmDelete.get(bits);
		for (Iterator<K> it = theSet.iterator(); it.hasNext();) {
			   kRemove = it.next();
			   break;
		}
		return kRemove;
	}
	@Override
	public void removeElement(K key) {
		hm.remove(key);
	}
	
//	public void printForTest() {
//		Set entrySet = hm.entrySet();
//		Iterator it = entrySet.iterator();
//		System.out.println("HashMap entries : "); 
//		while(it.hasNext()) {
//			System.out.println(it.next());
//		}
//	}
	
	
}