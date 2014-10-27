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
 * Receiver에서 종료 메시지 오는걸 처리하는 객체
 * 
 * @author Jonghoon Seo
 *
 */
public class ReceiverFinishPacketListener implements OSCListener {

	DragShareServer dragShareServer;
	
	public ReceiverFinishPacketListener(DragShareServer server) {
		dragShareServer = server;
	}
	
	/* (non-Javadoc)
	 * @see com.illposed.osc.OSCListener#acceptMessage(java.util.Date, com.illposed.osc.OSCMessage)
	 */
	@Override
	public void acceptMessage(Date time, OSCMessage message) {
		String senderID = (String)message.getArguments()[0];
		
		System.out.println(senderID + "will be deleted.");
		
		// 5. 다 끝났으면 디렉토리 제거
		deleteFtpDirectory(senderID);
	}

	private void deleteFtpDirectory(String senderID) {
		// TODO 5. 다 끝났으면 디렉토리 제거
		
	}

}
