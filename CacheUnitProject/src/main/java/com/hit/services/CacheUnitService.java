package com.hit.services;

import com.hit.algorithm.IAlgoCache;
import com.hit.algorithm.LRUAlgoCacheImpl;
import com.hit.dao.DaoFileImpl;
import com.hit.dao.IDao;
import com.hit.dm.DataModel;
import com.hit.memory.CacheUnit;

public class CacheUnitService<T> extends java.lang.Object {
	
	private static final String Algorithm = "LRU";
	private static final Integer Capacity = 4;
    private	Integer swaps=0,requests=0,dataModelsCounter=0;
	IAlgoCache<Long,DataModel<T>> algo;
	CacheUnit<T> cu;
	DataModel<T> dm;
    IDao<Long, DataModel<T>> dao;
	 
	public CacheUnitService() 
	{
		 algo = new LRUAlgoCacheImpl<Long, DataModel<T>>(Capacity);
		 cu =  new CacheUnit<T>(algo);	
	     dao = new DaoFileImpl<>("src\\main\\resources\\datasource.txt",500);
	}

	public boolean update(DataModel<T>[] dataModels) 
	{
		try 
		{
			DataModel<T>[] temp = cu.putDataModels(dataModels);
			for(int i=0; i < dataModels.length;i++) 
			{
				dataModelsCounter++;
				if(temp[i] != null) 
				{
					dao.save(temp[i]);
					swaps++;
				}
			}
			requests++;
		}
		  catch (Exception e) 
		{
			  return false;
		}
		return true;
	}
	public boolean delete(DataModel<T>[] dataModels) 
	{
		try {
			int dmSize = dataModels.length;
			Long [] dmId = new Long[dmSize];
			for(int i =0; i < dmSize; i++) {
				dmId[i] = dataModels[i].getDataModelId();
				T t = (T)dataModels[i].getContent();
				DataModel<T> entity = new DataModel<T>(dmId[i], t);
				dao.delete(entity);
				dataModelsCounter++;
			}
			requests++;
			cu.removeDataModels(dmId);
		}
		  catch (Exception e) {
			  e.printStackTrace();
			  return false;
		  }
		return true;
	}
	
	@SuppressWarnings("unchecked")
	public DataModel<T>[] get(DataModel<T>[] dataModels) 
	{
		requests++;
		int dmSize = dataModels.length;
		Long [] dmId = new Long[dmSize];
		for(int i =0; i < dmSize; i++) 
		{
			dmId[i] = dataModels[i].getDataModelId();
			dataModelsCounter++;
		}
		DataModel<T>[] temp = new DataModel[dataModels.length];
		temp = (DataModel<T>[]) cu.getDataModels(dmId);

		for (int i = 0; i < dataModels.length; i++) {
			if (temp[i] == null)
			{
				temp[i] = (DataModel<T>) dao.find(dataModels[i].getDataModelId());
			}
		}
		cu.putDataModels(temp);
		return temp;
	}

	public String showStats() {
		String stats = Algorithm + "," + Capacity + "," + swaps + "," + requests + "," + dataModelsCounter;
		return stats;
	}
}
