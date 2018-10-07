package com.hit.dao;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


import com.hit.dm.DataModel;


public class DaoFileImpl<T> extends java.lang.Object implements IDao<java.lang.Long,DataModel<T>> {

	private  String filePath;
	int capacity;
	private DataModel<T> entity;
	Map<Long, T> hm;
	ObjectInputStream in;
	ObjectOutputStream out;
	
	public DaoFileImpl(java.lang.String filePath,int capacity) {
		this.capacity = capacity;
		this.filePath=filePath;
		hm = new HashMap<Long, T>(capacity);
		
	}
	public DaoFileImpl(java.lang.String filePath) {
		this.filePath = filePath;
		hm = new HashMap<Long, T>();
		
	}
	
	@SuppressWarnings({ "unchecked", "resource" })
	@Override
	public void save(DataModel<T> t) { 
		try {
			ObjectInputStream in= new ObjectInputStream(new FileInputStream(filePath));
			if(in.available() != 0) {
				hm = (Map<Long, T>) in.readObject();
			}
			else {
				ObjectOutputStream out= new ObjectOutputStream(new FileOutputStream(filePath));
				hm.put(t.getDataModelId(),t.getContent());
				out.writeObject(hm);
				in= new ObjectInputStream(new FileInputStream(filePath));
				hm = (Map<Long, T>) in.readObject();				
				System.out.println(hm);
				in.close();
				out.close();
			}			
		}
		catch (Exception ex){
			ex.printStackTrace();
			}
	}
	@SuppressWarnings("unchecked")
	@Override
	public DataModel<T> find(Long id) {
		try {
			ObjectInputStream in= new ObjectInputStream(new FileInputStream(filePath));
			hm = (Map<Long, T>) in.readObject();
			T t = hm.get(id);
			in.close();
			if (t != null) {
				entity = new DataModel<T>(id,t);
				return entity;
			}
			else return null;
		}
		catch (Exception ex){
			ex.printStackTrace();
		}
		return null;
}
	@SuppressWarnings({ "unchecked", "resource" })
	@Override
	public void delete(DataModel<T> entity) {//Delete from data source.txt 
		try {
			ObjectInputStream in= new ObjectInputStream(new FileInputStream(filePath));
			hm = (Map<Long, T>) in.readObject();
			hm.remove(entity.getDataModelId());
			ObjectOutputStream out= new ObjectOutputStream(new FileOutputStream(filePath));
			out.writeObject(hm);
			in= new ObjectInputStream(new FileInputStream(filePath));
			hm = (Map<Long, T>) in.readObject();				
			System.out.println(hm);
			in.close();
			out.close();
			
		}
		catch (Exception ex){
			ex.printStackTrace();
		}

	}
}