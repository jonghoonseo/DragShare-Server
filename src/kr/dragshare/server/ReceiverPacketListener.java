/**
 * 
 */
package kr.dragshare.server;

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
		Object[] arguments = message.getArguments();
		
		ReceiverDB receiver = new ReceiverDB();
		receiver.receiver = (String)arguments[0];

		// Date 처리
		//---------------------------
		try {
			String timeString = (String)arguments[1];
			DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss:SSS");
			receiver.time = df.parse(timeString);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// 저장
		//-----------------------------
		dragShareServer.db.storeReceiver(receiver);
		
		
		// 4. FTP 다운받도록 SenderID 내려주기
		sendFtpInfor(receiver);
	}

	
	private void sendFtpInfor(ReceiverDB receiver) {
		// TODO 4. FTP 다운받도록 SenderID 내려주기
		
	}

}
