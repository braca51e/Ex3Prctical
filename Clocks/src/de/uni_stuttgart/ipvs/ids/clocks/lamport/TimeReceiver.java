package de.uni_stuttgart.ipvs.ids.clocks.lamport;

import de.uni_stuttgart.ipvs.ids.clocks.Clock;
import de.uni_stuttgart.ipvs.ids.communicationLib.VSDatagramSocket;

public class TimeReceiver implements Runnable {

	private Clock clock;
	private VSDatagramSocket receiverSocket;
	private final static long minimum_delay = 100;

	public TimeReceiver(Clock clock, VSDatagramSocket socket) {
		this.clock = clock;
		receiverSocket = socket;
	}

	public void run() {
		// TODO: Implement me!
	}

}
