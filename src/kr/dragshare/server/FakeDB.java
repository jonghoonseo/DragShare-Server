package kr.dragshare.server;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Vector;

class SenderDB {
	int dbIdx;
	String sender;			// identifier
	Date time;
	// location etc
}

class ReceiverDB {
	int dbIdx;
	String receiver;		// identifier
	Date time;
	// location etc
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
