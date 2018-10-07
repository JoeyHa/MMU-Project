package com.hit.server;
import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.hit.dm.DataModel;


public class Request<T> extends java.lang.Object implements java.io.Serializable{

	private static final long serialVersionUID = 1L;
	private java.util.Map<java.lang.String,java.lang.String> headers;
	private T body;
	
	public Request(java.util.Map<java.lang.String,java.lang.String> headers,T body)
	{
		this.headers = headers;
		this.body = body;
	}
	
	public java.util.Map<java.lang.String,java.lang.String> getHeaders() 
	{return headers;}
	
	
	public void setHeaders(java.util.Map<java.lang.String,java.lang.String> headers) 
	{this.headers=headers;}
	
	public T getBody() 
	{return body;}
	
	public void setBody(T body) 
	{this.body=body;}
	
	public java.lang.String toString() 
	{ return "Action = " + getHeaders().toString() + " Content = " + getBody().toString();}
	
}