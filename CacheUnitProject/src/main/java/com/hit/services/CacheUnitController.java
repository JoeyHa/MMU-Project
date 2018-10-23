package com.hit.services;
import com.hit.dm.DataModel;

public class CacheUnitController<T> extends java.lang.Object {
	
	CacheUnitService<T> cus;
	
	public CacheUnitController() {
		
		cus=new CacheUnitService<T>();
	}
	
	public boolean update(DataModel<T>[] dataModels)
	{return cus.update(dataModels);}
	
	public boolean delete(DataModel<T>[] dataModels) 
	{return cus.delete(dataModels);}
	
	public DataModel<T>[] get(DataModel<T>[] dataModels)
	{return cus.get(dataModels);}

	public String getStats() 
	{return cus.showStats();}

}