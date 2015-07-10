package fr.aleclerc.rasp.net;

import java.io.IOException;
import java.net.InetAddress;
import java.util.List;
import java.util.ListIterator;
import java.util.Vector;

public class NetworkStatusThread implements Runnable {
	List<NetworkListenerInterface> listenerList = new Vector<NetworkListenerInterface>();


	public synchronized void addNetworkListener(NetworkListenerInterface nl) {
		listenerList.add(nl);
	}

	public synchronized void removeNetworkListener(NetworkListenerInterface nl) {
		listenerList.remove(nl);
	}

	private synchronized void sendNetworkStatus(String status) {
		// send it to subscribers

		ListIterator<NetworkListenerInterface> iterator = listenerList.listIterator();
		while (iterator.hasNext()) {
			NetworkListenerInterface rl = (NetworkListenerInterface) iterator.next();
			rl.sendNetworkStatus(status);
		}
	}

	public void run() {
		 int timeout = 2000;
		  InetAddress[] addresses;
		try {
			addresses = InetAddress.getAllByName("www.google.com");
		
		  for (InetAddress address : addresses) {
		    if (address.isReachable(timeout))
		       this.sendNetworkStatus("OK");
		    else
		       this.sendNetworkStatus("BAD");
		  }
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
