package com.hit.memory;
import org.junit.Test;

import com.hit.algorithm.IAlgoCache;
import com.hit.algorithm.LRUAlgoCacheImpl;
import com.hit.dm.DataModel;

import static org.junit.Assert.assertEquals;

public class CacheUnitTest {
	
	IAlgoCache<Long,DataModel<Integer>> algo = new LRUAlgoCacheImpl<Long, DataModel<Integer>>(2);
	
	@Test
	 public void test1() {
	 Long ids[] = new Long [3];
	 ids[0] = (long) 1;
	 ids[1] = (long) 2;
	 ids[2] = (long) 3;
	 DataModel<Integer>[] dm = new DataModel[3]; 
	 dm[0] = new DataModel((long) 1,2);
	 dm[1] = new DataModel((long) 2,3);
	 dm[2] = new DataModel((long) 3,4);
	 CacheUnit<Integer> cu = new CacheUnit<Integer>(algo);
	 DataModel<Integer>[] dm2;
	 dm2 = cu.putDataModels(dm);
	 assertEquals(dm2[0], dm[0] );
	}
}
