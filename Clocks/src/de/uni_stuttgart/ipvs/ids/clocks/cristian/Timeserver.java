package de.uni_stuttgart.ipvs.ids.clocks.cristian;

import java.io.IOException;
import java.net.DatagramPacket;
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
		//Declare communication channels
		byte[] recvBuff = new byte[128];
		byte[] sendBuff = new byte[128];

		while (true) {
			//Declare communication channel
			DatagramPacket receivePacket = new DatagramPacket(recvBuff,
					recvBuff.length);
			//Wait for income messages
			serverSocket.receive(receivePacket);
			//Get time from master
			sendBuff = ("" + clock.getTime()).getBytes();
			//Declare send packet
			DatagramPacket sendPacket = new DatagramPacket(sendBuff, sendBuff.length,
					receivePacket.getAddress(), receivePacket.getPort());
			try {
				//Send time to slave
				serverSocket.send(sendPacket);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("server to " + (new String(receivePacket.getData())) + " "
					+ clock.getTime());
		}
	}

}
