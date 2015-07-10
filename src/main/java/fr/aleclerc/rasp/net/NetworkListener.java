package fr.aleclerc.rasp.net;

import java.io.IOException;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;
import com.pi4j.system.NetworkInfo;

public class NetworkListener implements NetworkListenerInterface {
	private final GpioController gpio = GpioFactory.getInstance();
	private GpioPinDigitalOutput ledV;
	private GpioPinDigitalOutput ledR;

	public NetworkListener() {
		super();

		ledV = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_25, "LEDV",
				PinState.LOW);
		ledR = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_28, "LEDR",
				PinState.LOW);
		ledV.setShutdownOptions(true, PinState.LOW);
		ledR.setShutdownOptions(true, PinState.LOW);
		ledR.high();
        System.out.println("----------------------------------------------------");
        System.out.println("NETWORK INFO");
        System.out.println("----------------------------------------------------");
        
        // display some of the network information
       
        try {
        	 System.out.println("Hostname          :  " + NetworkInfo.getHostname());
             for (String ipAddress : NetworkInfo.getIPAddresses())
                 System.out.println("IP Addresses      :  " + ipAddress);
             for (String fqdn : NetworkInfo.getFQDNs())
                 System.out.println("FQDN              :  " + fqdn);
			for (String nameserver : NetworkInfo.getNameservers())
			    System.out.println("Nameserver        :  " + nameserver);
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void sendNetworkStatus(String status) {
		System.out.println("status "+status);
		
		if (status.equals("OK")) {
			ledV.high();
			ledR.low();
		}else{
			ledR.high();
			ledV.low();
		}
			

	}

}
