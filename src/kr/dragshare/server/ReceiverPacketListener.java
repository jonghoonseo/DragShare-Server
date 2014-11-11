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

		// Date ����
		//---------------------------
		try {
			String timeString = (String)arguments[1];
			DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss:SSS");
			receiver.time = df.parse(timeString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		// ����
		//-----------------------------
		dragShareServer.db.storeReceiver(receiver);
		
		System.out.println(receiver);
		
		
		// 4. FTP ���������� SenderID ��������
		sendFtpInfor(receiver);
	}

	
	private void sendFtpInfor(ReceiverDB receiver) {
		System.out.println("Sending to " + receiver.receiver);
		
		try {
			OSCPortOut osc = new OSCPortOut(InetAddress.getByName(receiver.receiver),  9097);
			OSCMessage msg = new OSCMessage(OSCPacketAddresses.OSC_SERVER_PATH_PACKET, new Object[] {dragShareServer.db.senders.lastElement().sender});
			System.out.println("Receive from "+ dragShareServer.db.senders.lastElement().sender);
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
