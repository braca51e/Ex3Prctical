package de.uni_stuttgart.ipvs.ids.clocks.lamport;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetSocketAddress;
import java.util.Set;

import de.uni_stuttgart.ipvs.ids.clocks.Clock;
import de.uni_stuttgart.ipvs.ids.communicationLib.VSDatagramSocket;

public class TimeSender implements Runnable {

	private Clock clock;
	private VSDatagramSocket senderSocket;
	private Set<InetSocketAddress> neighbourSet;
	private final static long delta = 1000;
	private final static double maximal_drift_rate;

	public TimeSender(Clock clock, VSDatagramSocket socket,
			Set<InetSocketAddress> neighbourSet) {
		this.clock = clock;
		this.neighbourSet = neighbourSet;
		this.senderSocket = socket;

	}

	public void run() {
		// TODO: Implement me!
	}

}
