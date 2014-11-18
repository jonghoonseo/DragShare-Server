/**
 * 
 */
package kr.dragshare.server;

import java.io.IOException;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.illposed.osc.OSCListener;
import com.illposed.osc.OSCMessage;
import com.illposed.osc.OSCPortOut;

/**
 * @author Jonghoon Seo
 *
 */
public class ReceiverPacketListener implements OSCListener {
	
	OSCPortOut osc;

	DragShareServer dragShareServer;
	
	public ReceiverPacketListener(DragShareServer server) {
		dragShareServer = server;
	}
	
	/* (non-Javadoc)
	 * @see com.illposed.osc.OSCListener#acceptMessage(java.util.Date, com.illposed.osc.OSCMessage)
	 */
	@Override
	public void acceptMessage(Date time, OSCMessage message) {
		System.out.println("Received");
		
		
		Object[] arguments = message.getArguments();
		
		ReceiverDB receiver = new ReceiverDB();
		receiver.receiver = (String)arguments[0];

		// Date 파싱
		//---------------------------
		try {
			String timeString = (String)arguments[1];
			DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss:SSS");
			receiver.time = df.parse(timeString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		// 저장
		//-----------------------------
		dragShareServer.db.storeReceiver(receiver);
		
		System.out.println(receiver);
		
		
		// 4. 전송받을 UUID 전송
		sendFileInfor(receiver);
	}

	
	private void sendFileInfor(ReceiverDB receiver) {
		System.out.println("Sending to " + receiver.receiver);
		
		try {
			OSCPortOut osc = new OSCPortOut(InetAddress.getByName(receiver.receiver),  9097);
			SenderDB sender = dragShareServer.db.senders.lastElement();
			Object[] arguments = new Object[sender.uuidStrings.size()];
			
			for(int i=0; i<arguments.length; ++i){
				arguments[i] = sender.uuidStrings.get(i);
			}
			
			OSCMessage msg = new OSCMessage(OSCPacketAddresses.OSC_SERVER_PATH_PACKET, arguments);
			System.out.println("to Receiver: " + sender);
			osc.send(msg);
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
