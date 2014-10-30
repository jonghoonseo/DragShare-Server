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
	}
	
	public void finalize() {
		System.out.println("서버 종료");
	}


	private void initializeServer() throws SocketException {
		server = new OSCPortIn(port);
		
		server.addListener(OSCPacketAddresses.OSC_SENDER_ID_PACKET, new SenderPacketListener(this));
		server.addListener(OSCPacketAddresses.OSC_RECEIVER_ID_PACKET, new ReceiverPacketListener(this));
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
