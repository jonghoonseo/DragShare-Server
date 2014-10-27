package kr.dragshare.clients;

import java.io.IOException;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.illposed.osc.OSCMessage;
import com.illposed.osc.OSCPortOut;

public class Sender {
	
	OSCPortOut osc;
	
	public Sender() {
		try {
			osc = new OSCPortOut(InetAddress.getByName("localhost"), 3746);
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String id = "id";
		
		DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss:SSS");
		Date dateobj = new Date();
		
		Object[] arguments = {id, df.format(dateobj)};
		
		OSCMessage msg = new OSCMessage("/dragshare/sender", arguments);
		
		try {
			osc.send(msg);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new Sender();
	}

}
