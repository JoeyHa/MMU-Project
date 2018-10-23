package com.hit.memory;
import com.hit.algorithm.IAlgoCache;
import com.hit.dm.DataModel;


public class CacheUnit<T> extends java.lang.Object 
{
	
	private IAlgoCache<Long,DataModel<T>> algo;
	
	public CacheUnit(com.hit.algorithm.IAlgoCache<java.lang.Long,DataModel<T>> algo) 
	{
		super();
		this.algo =  algo;
	} 
	
	@SuppressWarnings("unchecked")
	public DataModel<T>[] getDataModels(java.lang.Long[] ids) 
	{
		int  idSize = ids.length;
		DataModel<T>[] dataModel = new DataModel[idSize];
		for(int i =0; i < idSize; i++) 
		{
			dataModel[i] = algo.getElement(ids[i]);
		}
		return dataModel;
	}
	
	@SuppressWarnings("unchecked")
	public DataModel<T>[] putDataModels(DataModel<T>[] dataModels) 
	{
		int count  = 0;
		int dmSize = dataModels.length;
		DataModel<T>[] dataModel = new DataModel[dmSize];
		for(int i =0; i < dmSize; i++) 
		{
			DataModel<T> temp= null; 
			temp = algo.putElement(dataModels[i].getDataModelId(), dataModels[i]);
			if(temp !=null)
				dataModel[count++]=new DataModel<T>(temp.getDataModelId(), temp.getContent());
		}
		return dataModel;
	}
	
	public void removeDataModels(java.lang.Long[] ids) 
	{
		int  idSize = ids.length;
		for(int i =0; i < idSize; i++) 
		{
			algo.removeElement(ids[i]);
		}
	}
			
}
