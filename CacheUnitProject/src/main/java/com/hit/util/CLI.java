package com.hit.util;

import java.beans.PropertyChangeSupport;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class CLI extends java.lang.Object implements java.lang.Runnable {
	
	private PropertyChangeSupport pcs = new PropertyChangeSupport(this);
	
	InputStream in;
	OutputStream out;
	Scanner reader;
	private Socket socket;

	public CLI(java.io.InputStream in, java.io.OutputStream out) {
		reader = new Scanner (new InputStreamReader(in));
		this.in = in;
		this.out = out;
		
	}
	
	public void addPropertyChangeListener(java.beans.PropertyChangeListener pcl) {
		pcs.addPropertyChangeListener(pcl);
	}
	public void removePropertyChangeListener(java.beans.PropertyChangeListener pcl) {
		pcs.removePropertyChangeListener(pcl);
	}
	
	public void write(java.lang.String string) {
		System.out.println(string);
	}
	
	
	public void run() {
		try
		{
			 write("Please enter Command:");
			 String command;
			 while(true) 
			 {
				 command = reader.nextLine().toLowerCase();
				 if (command.equals("start") || command.equals("shutdown")) 
				 {
					 pcs.firePropertyChange("Command" , null , command);
				 }
				 else 
				 {
					 write("Please enter start / shutdown only");
				 }
			 }
		}catch (Exception e) {
			
		}
	}

}
	

