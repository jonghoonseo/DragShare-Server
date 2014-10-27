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

/**
 * @author Jonghoon Seo
 *
 */
public class SenderPacketListener implements OSCListener {
	
	DragShareServer dragShareServer;
	
	public SenderPacketListener(DragShareServer server) {
		dragShareServer = server;
	}

	/* (non-Javadoc)
	 * @see com.illposed.osc.OSCListener#acceptMessage(java.util.Date, com.illposed.osc.OSCMessage)
	 */
	@Override
	public void acceptMessage(Date time, OSCMessage message) {
		Object[] arguments = message.getArguments();
		
		SenderDB sender = new SenderDB();
		sender.sender = (String)arguments[0];

		// Date ����
		//---------------------------
		try {
			String timeString = (String)arguments[1];
			DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss:SSS");
			sender.time = df.parse(timeString);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// ����
		//-----------------------------
		dragShareServer.db.storeSender(sender);
		
		System.out.println(sender.sender);
	}

}
