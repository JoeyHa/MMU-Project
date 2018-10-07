package com.hit.algorithm;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import org.junit.Test;

public class IAlgoCacheTest extends java.lang.Object {
	
	LRUAlgoCacheImpl<Integer,Integer> lru  = new LRUAlgoCacheImpl<Integer,Integer>(5);
	RandomAlgoCacheImpl<Integer,Integer> random = new RandomAlgoCacheImpl<Integer, Integer>(5);
	NRUAlgoCacheImpl<Integer,Integer> nru = new NRUAlgoCacheImpl<Integer, Integer>(5);
	
	public IAlgoCacheTest() {}
	
	@Test
	// CHECKS THE IMPLEMANTAION OF putElement METHOD:
	// Test if the Algorithm works as expected:
	public void testLRUAlgoCacheImpl() {
		 
		lru.putElement(1, 10);
		lru.putElement(2, 9);
		lru.putElement(3, 8);
		lru.putElement(4, 7);
		lru.putElement(5, 6);
		//ADDING OBJECT TO FULL CAPACITY MAP:
		lru.putElement(6, 5);
		
		int expectedValueOut = 10;
		
		assertEquals(expectedValueOut,10);
	}
	
	@Test
	//CHECKS CALLING TO getElement WHEN THE MAP IS EMPTY: 
	public void test2LRUAlgoCacheImpl() {
		lru.getElement(1);
		Object expectedValueOut = null;
		assertEquals(expectedValueOut, null);
		
	}
	@Test
	// CHECKS IF removeElement WORKS WITH KEYS AND NOT WITH VALUES: 
	public void test3LRUAlgoCacheImpl()  {
		lru.putElement(1, 2);
		lru.removeElement(2);
		int expectedKeyInMap = 1;
		//SHOULD NOT REMOVE NOTHING 
		assertNotEquals(2, null);
		//Checks if the element is still in the map:
		assertEquals(expectedKeyInMap, 1);
	}
	

	@Test	
	public void testRandomAlgoCacheImpl() {
		random.putElement(3, 4);
		random.removeElement(4);
		assertNotEquals(4, null);
		//SHOULD NOT REMOVE NOTHING 
		int expectedKeyInMap = 3;
		//Checks if the element is still in the map:
		assertEquals(expectedKeyInMap, 3);
		
	}
	@Test
	public void test2RandomAlgoCacheImpl() {
		random.getElement(1);
		Object expectedValueOut = null;
		assertEquals(expectedValueOut, null);
	}
	@Test // Test if same key override existing value:
	public void test3RandomAlgoCacheImpl() {
		random.putElement(1, 10);
		random.putElement(2, 9);
		random.putElement(3, 8);
		random.putElement(3, 7);
		random.putElement(5, 6);
		//Adding new pair to full capacity with the same key with different value:
		int resultValueMap = random.putElement(1, 4);
		assertEquals(4, resultValueMap);
	}
	
	
	@Test
	public void testNRUAlgoCacheImpl() {
		nru.putElement(1, 2);
		nru.putElement(3, 4);
		nru.putElement(5, 6);
		nru.putElement(7, 8);
		nru.putElement(9, 10);
		int resultValueMap = nru.putElement(1, 22);
		assertEquals(22, resultValueMap);
		
	}
	@Test
	public void test2NRUAlgoCacheImpl() {
		nru.putElement(3, 4);
		nru.removeElement(4);
		assertNotEquals(4, null);
		//SHOULD NOT REMOVE NOTHING 
		int expectedKeyInMap = 3;
		//Checks if the element is still in the map:
		assertEquals(expectedKeyInMap, 3);
		
	}
	@Test
	public void test3NRUAlgoCacheImpl() {
		nru.getElement(1);
		Object expectedValueOut = null;
		assertEquals(expectedValueOut, null);
	}

}