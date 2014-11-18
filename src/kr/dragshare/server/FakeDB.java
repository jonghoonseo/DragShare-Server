package kr.dragshare.server;

import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

class SenderDB {
	int dbIdx;
	ArrayList<String>	uuidStrings = new ArrayList<String>();
	Date time;
	
	// location etc
	
	public String toString() {
		StringBuffer str;
		
		str = new StringBuffer("Total: " + uuidStrings.size() + " ids\n");
		for(String uuidString : uuidStrings)
			str.append(uuidString + "\n");
		
		return str.toString();
	}
}

class ReceiverDB {
	int dbIdx;
	String receiver;		// identifier
	Date time;
	// location etc

	public String toString() {
		return "index: " + dbIdx + "\n"
				+ "receiver : " + receiver + "\n"
				+ "time: " + time + "\n";
	}
}

public class FakeDB {


	Vector<SenderDB> 	senders;
	Vector<ReceiverDB> 	receivers;
	
	public FakeDB() {
		senders = new Vector<SenderDB>();
		receivers = new Vector<ReceiverDB>();
	}
	
	public void storeSender(SenderDB s) {
		senders.add(s);
	}
	
	public void storeReceiver(ReceiverDB r) {
		receivers.add(r);
	}
}
