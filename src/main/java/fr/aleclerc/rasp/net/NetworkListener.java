package fr.aleclerc.rasp.net;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;

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
	}

	@Override
	public void sendNetworkStatus(String status) {
		if (status.equals("OK")) {
			ledV.high();
			ledR.low();
		}else{
			ledR.high();
			ledV.low();
		}
			

	}

}
