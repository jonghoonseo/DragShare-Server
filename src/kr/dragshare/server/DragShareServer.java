/**
 * 
 */
package kr.dragshare.server;

import java.io.IOException;
import java.io.InputStream;
import java.net.SocketException;
import java.util.Properties;

import com.illposed.osc.OSCListener;
import com.illposed.osc.OSCMessage;
import com.illposed.osc.OSCPortIn;

/**
 * @author Jonghoon_Seo
 *
 */
public class DragShareServer {
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
		
		server.addListener("/dragshare/sender", new SenderPacketListener());
		server.addListener("/dragshare/receiver", new SenderPacketListener());
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
}
