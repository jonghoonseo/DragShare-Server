package kr.dragshare.server;

import java.util.Date;
import java.util.Vector;

class SenderDB {
	int dbIdx;
	String sender;			// identifier
	Date time;
	// location etc
	
	public String toString() {
		return "index: " + dbIdx + "\n"
				+ "sender: " + sender + "\n"
				+ "time: " + time + "\n";
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
