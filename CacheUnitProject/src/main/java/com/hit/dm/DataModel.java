package com.hit.dm;



public class DataModel<T> extends java.lang.Object implements java.io.Serializable {
	/**
	 * 
	 */
	public java.lang.Long id;
	public  T content;
	private static final long serialVersionUID = 1L;

	public DataModel(java.lang.Long id,T content) {
	//	super();
		this.id = id;
		this.content = content;
	}
	public T getContent() { 
		return this.content;
	}
	public void setContent(T content) {
		this.content = content;
	}
	public java.lang.Long getDataModelId() {
		return id;
	}
	public void setDataModelId(java.lang.Long id) {
		this.id = id;
	}
	
	@Override 
	public java.lang.String toString(){
		return "("+id+")";
	}
	
	@SuppressWarnings("unchecked")

	@Override
	public boolean equals(java.lang.Object obj) {
		if (obj == null) return false;
		return this.id == ((DataModel<T>) obj).getDataModelId();
	}
	@Override
	public int hashCode() {
		return id.hashCode();
	}
}