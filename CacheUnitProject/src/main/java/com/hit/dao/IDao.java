package com.hit.dao;

import com.hit.dm.DataModel;

public interface IDao<ID extends java.io.Serializable,T> {
	
	void save(T t);
	void delete(T t);
	T find(ID id);
}
