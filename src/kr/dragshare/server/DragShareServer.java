/**
 * 
 */
package kr.dragshare.server;

import java.io.IOException;
import java.io.InputStream;
import java.net.SocketException;
import java.util.Properties;

import com.illposed.osc.OSCPortIn;

/**
 * @author Jonghoon_Seo
 *
 */
public class DragShareServer {
	OSCPortIn server;
	
	int port;
	
	FakeDB db;
	
	// Constructor
	//--------------------------------
	public DragShareServer() throws IOException {
		db = new FakeDB();
		
		getServerConfiguration();
		
		initializeServer();
		
		System.out.println("DragShare server is opened");
	}
	
	public void finalize() {
		System.out.println("DragShare server is closed");
	}


	private void initializeServer() throws SocketException {
		System.out.println("DragShare server: " + port + " port is opening");
		server = new OSCPortIn(port);
		
		server.addListener(OSCPacketAddresses.OSC_SENDER_ID_PACKET, 	new SenderPacketListener(this));
		server.addListener(OSCPacketAddresses.OSC_RECEIVER_ID_PACKET, 	new ReceiverPacketListener(this));
		server.addListener(OSCPacketAddresses.OSC_RECEIVER_FINISH_PACKET, new ReceiverFinishPacketListener(this));
		server.startListening();
	}


	/**
	 * @throws IOException 
	 * 
	 */
	private void getServerConfiguration() throws IOException {
		InputStream input;
		Properties prop = new Properties();

		input = getClass().getClassLoader().getResourceAsStream("kr/dragshare/server/config.properties");
		
		if(input == null) {
			System.err.println("error: can not load a property file");
			System.exit(-1);
		}
		
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
