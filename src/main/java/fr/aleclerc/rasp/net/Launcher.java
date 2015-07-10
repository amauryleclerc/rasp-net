package fr.aleclerc.rasp.net;

public class Launcher {

	public static void main(String[] args) {
		System.out.println("lancement");
		NetworkStatusThread netStatus = new NetworkStatusThread();
		netStatus.addNetworkListener(new NetworkListener());
		Thread t = new Thread(netStatus);
		t.start();

	}



}
