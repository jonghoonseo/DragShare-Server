/**
 * 
 */
package kr.dragshare;

import java.io.IOException;
import java.io.InputStream;
import java.net.SocketException;
import java.util.Date;
import java.util.Properties;

import com.illposed.osc.OSCListener;
import com.illposed.osc.OSCMessage;
import com.illposed.osc.OSCPortIn;

/**
 * @author Jonghoon_Seo
 *
 */
public class DragShareServer implements OSCListener {
	OSCPortIn server;
	
	int port;
	
	// Constructor
	//--------------------------------
	public DragShareServer() throws IOException {
		getServerConfiguration();
		
		initializeServer();
	}


	private void initializeServer() throws SocketException {
		server = new OSCPortIn(port);
		
		OSCListener listener = new OSCListener() {
			public void acceptMessage(java.util.Date time, OSCMessage message) {
				System.out.println("Message received!");
			}
		};
		
		server.addListener("/dragshare/*", listener);
		server.startListening();
	}


	/**
	 * @throws IOException 
	 * 
	 */
	private void getServerConfiguration() throws IOException {
		InputStream input;
		Properties prop = new Properties();

		input = getClass().getClassLoader().getResourceAsStream("kr/dragshare/config.properties");
		
		if(input == null)
			System.err.println("error: can not load a property file");
		
		// load a properties file
		prop.load(input);
		
		port = Integer.valueOf(prop.getProperty("port", "9999")).intValue();
	}
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			new DragShareServer();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	@Override
	public void acceptMessage(Date time, OSCMessage message) {
		// TODO Auto-generated method stub
		
	}

}
