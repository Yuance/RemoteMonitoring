package com.tz.screent;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import com.sun.media.sound.Toolkit;

public class Server {
	public static void main(String[] args) {
		
		try {
			ServerSocket ss = new ServerSocket();
			System.out.println("Connecting...");
			Socket client = ss.accept();
			System.out.println("Server Connect successfully");
			OutputStream os = client.getOutputStream();
			//
			DataOutputStream doc = new DataOutputStream(os);		
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}

class ScreenThread extends Thread {
	
	private DataOutputStream dataout;
	
	public ScreenThread(DataOutputStream dataout) {
		this.dataout = dataout;
	}
	
	public void run() {
		
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension dm = tk.getScreenSize();
		
		try {
			//get Screen Resolution and send
			dataout.writeDouble(dm.getHeight());
			dataout.writeDouble(dm.getWidth());
			dataout.flush();
			//define shared area
			Rectangle rec = new Rectangle(dm);
			
			Robot robot = new Robot();
			while(true) {
				
				BufferedImage bufferedImage = robot.createScreenCapture(rec);
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				//compress image
				JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(baos);
				encoder.encode(bufferedImage);
				byte[] data = baos.toByteArray();
				dataout.writeInt(data.length);
				dataout.write(data);
				dataout.flush();
				
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}