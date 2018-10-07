package com.hit.services;

import com.hit.algorithm.IAlgoCache;
import com.hit.algorithm.LRUAlgoCacheImpl;
import com.hit.dao.DaoFileImpl;
import com.hit.dao.IDao;
import com.hit.dm.DataModel;
import com.hit.memory.CacheUnit;

public class CacheUnitService<T> extends java.lang.Object {
	IAlgoCache<Long,DataModel<String>> algo;
	CacheUnit<String> cu;
    IDao<Long, DataModel<String>> dao;
	 
	public CacheUnitService() 
	{
		 algo = new LRUAlgoCacheImpl<Long, DataModel<String>>(2);
		 cu = new CacheUnit<String>(algo);	
	     dao = new DaoFileImpl<>("file.txt",2);
	}
	
	@SuppressWarnings("unchecked")
	public boolean update(DataModel<T>[] dataModels) 
	{
		try {
			cu.putDataModels((DataModel<String>[]) dataModels);
			int dmSize = dataModels.length;
			for(int i =0; i < dmSize; i++) {
				dao.save((DataModel<String>) dataModels[i].getContent());
			}
		}
		  catch (Exception e) {
			  return false;
		  }
		return true;
	}
	
	@SuppressWarnings("unchecked")
	public boolean delete(DataModel<T>[] dataModels) 
	{
		try {
			int dmSize = dataModels.length;
			Long [] dmId = new Long[dmSize];
			for(int i =0; i < dmSize; i++) {
				dmId[i] = dataModels[i].getDataModelId();
				dao.delete((DataModel<String>) dataModels[i].getContent());
			}
			cu.removeDataModels(dmId);
		}
		  catch (Exception e) {
			  return false;
		  }
		return true;
	}
	
	@SuppressWarnings("unchecked")
	public DataModel<T>[] get(DataModel<T>[] dataModels) 
	{
		int dmSize = dataModels.length;
		Long [] dmId = new Long[dmSize];
		for(int i =0; i < dmSize; i++) 
		{
			dmId[i] = dataModels[i].getDataModelId();
			dao.find(dataModels[i].getDataModelId());
		}
		return (DataModel<T>[]) cu.getDataModels(dmId);
	}
	
}
