package de.uni_stuttgart.ipvs.ids.clocks.cristian;

import java.net.InetSocketAddress;

import de.uni_stuttgart.ipvs.ids.clocks.Clock;
import de.uni_stuttgart.ipvs.ids.communicationLib.VSDatagramSocket;

public class Timeserver implements Runnable {

	private Clock clock;
	private VSDatagramSocket serverSocket;

	public Timeserver(Clock clock, InetSocketAddress address) {

		this.clock = clock;
		serverSocket = new VSDatagramSocket(address);

	}

	public void run() {
		// TODO: Implement me
	}

}
