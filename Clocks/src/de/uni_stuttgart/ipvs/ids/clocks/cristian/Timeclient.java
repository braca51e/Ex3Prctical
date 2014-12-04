package de.uni_stuttgart.ipvs.ids.clocks.cristian;

import java.time.*;
import java.net.DatagramPacket;
import java.net.InetSocketAddress;

import de.uni_stuttgart.ipvs.ids.clocks.Clock;
import de.uni_stuttgart.ipvs.ids.communicationLib.VSDatagramSocket;

public class Timeclient implements Runnable {

	private Clock clock;
	private VSDatagramSocket clientSocket;
	private String name;
	private final static long delta = 1000;
	private final static double maximal_drift_rate = 0.1;
	private final static long tau = (long) (((double) delta) / (maximal_drift_rate * 2));
	
	InetSocketAddress server_address = new InetSocketAddress("localhost", 4000);

	public Timeclient(Clock clock, InetSocketAddress address, String name) {
		this.clock = clock;
		clientSocket = new VSDatagramSocket(address);
		this.name = name;
	}

	public void run() {
		// TODO: Implement me!
		//Buffers for channel communication
		byte[] recvBuff = new byte[128];
		byte[] sendBuff = new byte[128];

		while (true) {

			sendBuff = name.getBytes();
			DatagramPacket sendPacket = new DatagramPacket(sendBuff, sendBuff.length,
					server_address.getAddress(), server_address.getPort());
			try {
					clientSocket.send(sendPacket);
					
					DatagramPacket receivePacket = new DatagramPacket(recvBuff,
							recvBuff.length);
	
					long t0 = clock.getTime();
					System.out.println(clock.getTime() + " puto");
					clientSocket.receive(receivePacket);
					long cm = new Long((new String(receivePacket.getData()).trim()));
					long t1 = clock.getTime();
					long delay = (t1 - t0) / 2;
					long currenttime = cm + delay;
	
					// Check the velocity of the clock. If it is faster we set t0.
	
					if (clock.getTime() <= cm) {
						clock.setTime(currenttime);
					} else {
						clock.setTime(t0);
					}
	
					System.out.println("clock " + name + " " + currenttime
							+ " delay " + delay);
	
					Thread.sleep(tau);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

}
