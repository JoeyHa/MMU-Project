package com.hit.client;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hit.dm.DataModel;
import com.hit.server.Request;

public class CacheUnitClient extends java.lang.Object {
	private InetAddress host;
	private Socket socket;
	private int ipPort= 1324;
	
	public CacheUnitClient() {}
	
	public java.lang.String send(java.lang.String request)
	{
		try 
		{
			//create a TCP Connection to server over "localhost"
			host = InetAddress.getByName("localhost");
			socket = new Socket (host,ipPort);
			//Convert all JSON file to String  
			Scanner reader = new Scanner(new InputStreamReader(socket.getInputStream()));
			Type ref = new TypeToken<Request<DataModel<String>[]>>(){}.getType();
			request = new Gson().fromJson(request, ref); // deserializes JSON into request
			//PrintWriter writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(),request));
			
		}
		catch (UnknownHostException ex) {
			ex.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}

