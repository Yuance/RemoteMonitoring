package com.tz.util;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import com.sun.media.sound.Toolkit;

public class LocationScreen {
	public static void main(String[] args) {
		
		int choice = JOptionPane.showConfirmDialog(null, "Request control PC?","Yuance",JOptionPane.YES_NO_CANCEL_OPTION);
		
		if (choice == JOptionPane.NO_OPTION) {
			return;
		}
		
		JOptionPane.showInputDialog("Enter the target server ip address and port","127.0.0.1:10000");
		
		JFrame jFrame = new JFrame("Java Remote Desktop Monitoring");
		jFrame.setSize(600,600);
		jFrame.setVisible(true);   // visibility
		jFrame.setLocationRelativeTo(null); //location
		jFrame.setAlwaysOnTop(true);
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel imageJLabel = new JLabel();
		
		jFrame.add(imageJLabel);
		
		try {
			Robot robot = new Robot();
			
			//Get Request End Screen size
			Toolkit tk = Toolkit.getDefaultToolkit();
			Dimension dm = tk.getScreenSize();
			
			//Assign shared area
			
			Rectangle rec = new Rectangle(jFrame.getWidth(), 0, (int)jFrame.getWidth()-jFrame.getWidth(),(int)jFrame.getHeight());
			BufferedImage bufImg = robot.createScreenCapture(rec);
			
			imageJLabel.setIcon(new ImageIcon(bufImg));
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		
	}
}