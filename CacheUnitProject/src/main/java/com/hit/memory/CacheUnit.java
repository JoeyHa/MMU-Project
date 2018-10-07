package com.hit.memory;
import com.hit.algorithm.IAlgoCache;
import com.hit.dm.DataModel;


public class CacheUnit<T> extends java.lang.Object {
	
	private IAlgoCache<Long,DataModel<T>> algo;
	
	public CacheUnit(com.hit.algorithm.IAlgoCache<java.lang.Long,DataModel<T>> algo) {
		super();
		this.algo =  algo;
	} 
	
	@SuppressWarnings("unchecked")
	public DataModel<T>[] getDataModels(java.lang.Long[] ids) {
		int  idSize = ids.length;
		DataModel<T>[] dataModel = new DataModel[idSize];
		for(int i =0; i < idSize; i++) {
			dataModel[i] = algo.getElement(ids[i]);
		}
		return dataModel;
	}
	
	@SuppressWarnings("unchecked")
	public DataModel<T>[] putDataModels(DataModel<T>[] datamodels) {
		int dmSize = datamodels.length;
		DataModel<T>[] dataModel = new DataModel[dmSize];
		for(int i =0; i < dmSize; i++) {
			dataModel[i]= algo.putElement(datamodels[i].getDataModelId(), datamodels[i]);
		}
		return dataModel;
	}
	
	public void removeDataModels(java.lang.Long[] ids) {
		int  idSize = ids.length;
		for(int i =0; i < idSize; i++) {
			algo.removeElement(ids[i]);
		}
	}
		

	
}
