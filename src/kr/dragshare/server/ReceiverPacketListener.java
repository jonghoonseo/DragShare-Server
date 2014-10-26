/**
 * 
 */
package kr.dragshare.server;

import java.util.Date;

import com.illposed.osc.OSCListener;
import com.illposed.osc.OSCMessage;

/**
 * @author Jonghoon Seo
 *
 */
public class ReceiverPacketListener implements OSCListener {

	/* (non-Javadoc)
	 * @see com.illposed.osc.OSCListener#acceptMessage(java.util.Date, com.illposed.osc.OSCMessage)
	 */
	@Override
	public void acceptMessage(Date time, OSCMessage message) {
		
	}

}
