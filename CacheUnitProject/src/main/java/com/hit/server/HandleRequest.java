package com.hit.server;


import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Reader;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import com.google.gson.Gson;
import java.lang.reflect.Type;
import com.google.gson.reflect.TypeToken;
import com.hit.dm.DataModel;
import com.hit.services.CacheUnitController;

public class HandleRequest<T>extends java.lang.Object implements java.lang.Runnable {
	  Socket s; 
	  CacheUnitController<T> controller;
	  InputStream in=null;
	  String req,str;
	  boolean res=false;
	  Scanner reader;
	  PrintWriter writer;
	  Map <String,String> headers;
	  
	public HandleRequest(java.net.Socket s,CacheUnitController<T> controller) 
	{
		this.s=s;
		this.controller=controller;
	}
	public void run() 
	{
		try 
		{
		     reader = new Scanner(new InputStreamReader(s.getInputStream()));
			 writer = new PrintWriter(new OutputStreamWriter(s.getOutputStream()));
        } catch (IOException e) 
		{
            e.printStackTrace();
        }
		req = reader.nextLine();
		System.out.println(req);
		Type ref = new TypeToken<Request<DataModel<T>[]>>(){}.getType();
		Request<DataModel<T>[]> request = new Gson().fromJson(req,ref);
		headers=request.getHeaders();
		  switch (headers.get("action"))
	      {
	            case "UPDATE":
                {
                   res=controller.update(request.getBody());
                   break;
                }
            
	            case "DELETE":
	            {
	            	res=controller.delete(request.getBody());
	                break;
	            }
	            case "GET":
	            {
	            	res=controller.get(request.getBody()) != null;
	                break;
	            }
	            case "STATS":
	            {
	            	str = controller.getStats();
					break;
	            }
	            default:break;
	        }
		      if (!headers.get("action").equals("STATS"))
		      {
		    	  if (res)
		    		  str="true";
		    	  else str="false";
		      }
		    writer.println(str);
			writer.flush();
	}
	
}