package com.hit.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeSupport;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import com.hit.client.CacheUnitClientObserver;

public class CacheUnitView {
	
	    private PropertyChangeSupport pcs = new PropertyChangeSupport(this);

	
		public CacheUnitView() {}

		public void addPropertyChangeListener(java.beans.PropertyChangeListener pcl) {
			pcs.addPropertyChangeListener(pcl);
		}
		public void removePropertyChangeListener(java.beans.PropertyChangeListener pcl) {
			pcs.removePropertyChangeListener(pcl);
		}
		
	public void start() 
	{
		JFrame frame = new JFrame();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("MMU Project");
		frame.setBounds(900, 900, 900, 900);
	            JPanel panel = new JPanel();
				panel.setBorder(new EmptyBorder(5, 5, 5, 5));
				frame.setContentPane(panel);
				panel.setLayout(null);
				JTextArea ta = new JTextArea();
				ta.setBounds(80, 120, 700, 600);
				ta.setSelectedTextColor(Color.WHITE);
				ta.setForeground(Color.cyan);
				ta.setFont(new Font("Comic Sans MS", Font.BOLD, 28));
				panel.add(ta);
				//mmu-headline
				JLabel label1 = new JLabel("MMU");
				label1.setForeground(Color.WHITE);
				label1.setFont(new Font("Comic Sans MS", Font.BOLD, 72));
				label1.setBounds(335, 11, 500, 68);
				panel.add(label1);
				//made by
				JLabel label2 = new JLabel("\u00A9 Sarai Israeli & Joey Havia \u00A9");
				label2.setForeground(Color.white);
				label2.setBounds(310, 580, 513, 350);
				label2.setFont(new Font("Comic Sans MS", Font.ITALIC, 22));
				panel.add(label2);
				//stats
				JButton statButton = new JButton("Show Statistics");
				statButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 24));
				statButton.setIcon(new ImageIcon("images/stat.png"));
				statButton.setBackground(Color.WHITE);
				statButton.setBounds(550, 11, 300, 78);
				panel.add(statButton);
				//request
				JButton reqButton = new JButton("Load a Request");
				reqButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 24));
				reqButton.setIcon(new ImageIcon("images/upload.png"));
				reqButton.setBackground(Color.WHITE);
				reqButton.setBounds(10,11,300, 78);
				panel.add(reqButton);
				//background
				JLabel wp = new JLabel("");
				wp.setIcon(new ImageIcon("images/bg.png"));
				wp.setBounds(0, 0, screenSize.width, screenSize.height);
				panel.add(wp);
				//
				frame.setLocationRelativeTo(null);
				frame.setVisible(true);
			}
	
	
	   public <T> void updateUIData(T t) 
	        {
	        	if (t.toString().equals("true")) //load succeeded
	        	{
	        		ta.setText("Succeeded :) ");
	        		ta.setSelectedTextColor(Color.GREEN);
	        	}
	        	else if (t.toString().equals("false")) // load failed 
	        	{
	        		ta.setText("Failed :( ");
	        		ta.setSelectedTextColor(Color.RED);
	        	}
	        	else ta.setText(t.toString());  // stat
	        	ta.invalidate();
	        }

}
