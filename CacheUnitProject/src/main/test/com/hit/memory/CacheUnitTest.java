package com.hit.memory;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import com.hit.algorithm.IAlgoCache;
import com.hit.algorithm.LRUAlgoCacheImpl;
import com.hit.dm.DataModel;

public class CacheUnitTest {
	
	@SuppressWarnings("unchecked")
	@Test
	public void getDataModelsTest(){
		
		IAlgoCache<Long, DataModel<String>> algoTest = new LRUAlgoCacheImpl<>(1);
		CacheUnit<String> cacheUnitTest = new CacheUnit<String>(algoTest);
		DataModel<String>[] dataModelTest1,dataModelTest2;
		DataModel<String> dm1 = new DataModel<String>(1L, "str");
		DataModel<String> dm2 = new DataModel<String>(2L, "str2");
		dataModelTest1 = new DataModel[2];
		dataModelTest1[0] = dm1;
		dataModelTest1[1] = dm2;
		
    	dataModelTest2 = cacheUnitTest.putDataModels(dataModelTest1);
    	assertEquals(dataModelTest1[0].getDataModelId(),dataModelTest2[0].getDataModelId());

	}
}

