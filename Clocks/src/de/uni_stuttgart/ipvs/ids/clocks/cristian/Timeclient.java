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
					//t0 to stimate P
					long t0 = clock.getTime();
					//Send socket teh request
					clientSocket.receive(receivePacket);
					//Get current clock master clock
					long Cm = new Long((new String(receivePacket.getData()).trim()));
					//t1 to stimate P
					long t1 = clock.getTime();
					//Compute P
					long P = (t1 - t0) / 2;
					//Stimate master's current time
					long currentTime = Cm + P;
	
					// Chech if slave's clock is faster 
					if (clock.getTime() <= Cm) {
						clock.setTime(currentTime);
					} else {
					//Let interrupt pass with out increment clock
						clock.setTime(t0);
					}
	
					System.out.println("clock " + name + " " + currentTime
							+ " delay " + P);
	
					Thread.sleep(tau);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

}
