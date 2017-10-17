package com.tz.screent;


import java.awt.Dimension;
import java.awt.HeadlessException;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class Client {
	public static void main(String[] args) {
		try {
			int i = JOptionPane.showConfirmDialog(null, "","",1);
			
			if (i==1);
			String input = JOptionPane.showInputDialog("");
			String host = input.substring(0,input.indexOf(":"));
			String post = input.substring(input.indexOf(":")+1);
			
			Socket client = new Socket(host,Integer.parseInt(post));
			
			DataInputStream dis = new DataInputStream(client.getInputStream());
			
			JFrame jFrame = new JFrame();
			jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			jFrame.setTitle("Java Remote Control");
			jFrame.setSize(1024,768);
			
			//get Screen resolution
			double height = dis.readDouble();
			double width = dis.readDouble();
			Dimension ds = new Dimension((int)width, (int)height);
			jFrame.setSize(ds);
			//panel
			JLabel backImage = new JLabel();
			JPanel panel = new JPanel();
			//ScrollPane
			JScrollPane scrollPane = new JScrollPane(panel);
			panel.setLayout(new FlowLayout());
			panel.add(backImage);
			
			jFrame.add(panel);
			jFrame.setVisible(true);
			jFrame.setLocationRelativeTo(null);
			jFrame.setAlwaysOnTop(true);
			
			while(true) {
				int len = dis.readInt();
				byte[] imageData = new byte[len];
				dis.readFully(imageData);
				
				ImageIcon image = new ImageIcon(imageData);
				backImage.setIcon(image);
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		
	
	
	}
}

	