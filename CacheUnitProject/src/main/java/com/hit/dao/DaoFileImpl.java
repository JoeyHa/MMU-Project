package com.hit.dao;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


import com.hit.dm.DataModel;

public class DaoFileImpl<T> extends java.lang.Object implements IDao<java.lang.Long,DataModel<T>> {

	int capacity;
	private DataModel<T> entity;
	Map<Long, T> hm;
	File file;
	ObjectInputStream in;
	ObjectOutputStream out;
	String filePath;
	
	public DaoFileImpl(java.lang.String filePath,int capacity) {
		this.capacity = capacity;
		this.filePath=filePath;
	    file = new File (filePath);
		if (file.exists()) 
		{
			file.delete();
		}
		try {
			file.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		hm = new HashMap<Long, T>(capacity);		
	}
	public DaoFileImpl(java.lang.String filePath) {
		 file = new File (filePath);
			if (file.exists()) 
			{
				file.delete();
			}
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();			
			}
		hm = new HashMap<Long, T>();	
	}
	@Override
	public void save(DataModel<T> t) { 
		try {
				readFromFile();
				hm.put(t.getDataModelId(),t.getContent());
				writeToFile();		
		}
		catch (Exception ex){
			ex.printStackTrace();
			}
	}
	@Override
	public DataModel<T> find(Long id) {
		try {
			if (id != null) {
				readFromFile();
				T t = hm.get(id);
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
	public void delete(DataModel<T> entity) {//Delete from data source.txt
			 readFromFile();
		     hm.remove(entity.getDataModelId());
		     writeToFile();
			}
		
	@SuppressWarnings("unchecked")
	public void readFromFile ()
	{
		if (file.exists()) 
		{
		try {
			FileInputStream in = new FileInputStream(filePath);
			if (in.available()>0)
			{
			ObjectInputStream oin = new ObjectInputStream(in);
			hm = (Map<Long, T>)oin.readObject();
			oin.close();
			in.close();
			}
		   }
		catch (Exception ex){
				ex.printStackTrace();
			}
	}
	}
	public void writeToFile()
	{
		ObjectOutputStream out;
		try {
			out = new ObjectOutputStream(new FileOutputStream(filePath));
			out.writeObject(hm);
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e)
				{
			e.printStackTrace();
		}
	}
	
}
	
	