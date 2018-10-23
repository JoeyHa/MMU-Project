package com.hit.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Observable;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import com.hit.services.CacheUnitController;

public class Server extends java.lang.Object implements java.beans.PropertyChangeListener, java.lang.Runnable {
	
	public int port = 12345;
	private boolean state = false;
	private  ServerSocket server;
	
	public void propertyChange(java.beans.PropertyChangeEvent evt) {
		String command = evt.getNewValue().toString();
		switch (command) 
		{
			case "start":
				if (state == false)
				{
				  state=true;
				  new Thread(this).start();
				}
				else System.out.println("Server is already up\n");
				break;
				 
			case "shutdown":
				if(state == false)
					System.out.println("Server is already down\n");
				else
				{
						try {
							state = false;
							server.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					System.out.println("Shutdown server ");
				}
				break;
			    default:
				System.out.println("Not a valid commnand");
				break;
		}
	}
	
	@SuppressWarnings("deprecation")
	public void run() 
	{
				try 
				{
					server=new ServerSocket(port);
					System.out.println("Starting server.......");
					Executor ex = Executors.newFixedThreadPool(5); // pool of threads and execute multiple tasks 
					CacheUnitController<String> cuc= new CacheUnitController<String>();
					 while(state) 
					 {
						 Socket someClient = server.accept();
					     HandleRequest<String> RequesterHandler = new HandleRequest<String>(someClient,cuc);  
					     ex.execute(RequesterHandler);
					 } 
				}
			catch(SocketException e) { }
			catch (IOException e) {
			e.printStackTrace();
			}
			finally {
					try {
					if(server != null)
						server.close();
				}catch (IOException e) {
					e.printStackTrace();
				}
			}
	}
}
	
