package com.hit.server;


import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import com.google.gson.Gson;
import java.lang.reflect.Type;
import com.google.gson.reflect.TypeToken;
import com.hit.dm.DataModel;
import com.hit.services.CacheUnitController;

public class HandleRequest<T>extends java.lang.Object implements java.lang.Runnable {
	  Socket s; 
	  CacheUnitController<T> controller;
	  InputStream in=null;
	  Reader req;
	  Map <String,String> headers;
	  
	public HandleRequest(java.net.Socket s,CacheUnitController<T> controller) {
		this.s=s;
		this.controller=controller;
	}
	public void run() 
	{
		try {
        	in = s.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
		req = new InputStreamReader(in);
		Type ref = new TypeToken<Request<DataModel<T>[]>>(){}.getType();
		Request<DataModel<T>[]> request = new Gson().fromJson(req,ref);
		headers=request.getHeaders();
		  switch (headers.get("action"))
	        {
	            case "UPDATE":
                {
            	   controller.update(request.getBody());
                   break;
                }
            
	            case "DELETE":
	            {
	            	controller.delete(request.getBody());
	                break;
	            }
	            case "GET":
	            {
	            	controller.get(request.getBody());
	                break;
	            }
	            
	            default:break;
	        }
	}
	
}