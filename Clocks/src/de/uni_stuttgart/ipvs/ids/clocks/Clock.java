package de.uni_stuttgart.ipvs.ids.clocks;


/**
 * This Class simulates a hardware clock.
 */
public class Clock extends Thread {

	private final long incrementTime;
	private final double drift;
	private long counter;
	private final String name;

	public Clock(String name, long incrementTime, double drift) {
		super("Clock " + name);
		counter = 0L;
		this.incrementTime = incrementTime;
		this.drift = drift;
		this.name = name;
		this.start();
	}

	public synchronized long getTime() {
		// TODO: Implement me!
		//Return the time that has passed
		return counter;
	}

	public synchronized void setTime(long time) {
		// TODO: Implement me!
		System.out.println("clock "+ name + " " + counter);
		counter = time; 
	}

	public void run() {
		// TODO: Implement me!
		while(true){
			//Time elapsed in one tick is equal to incrementTime/2 plus drift 	
			counter += (long)(drift*incrementTime)+(long)incrementTime; 
			System.out.println("Current time Clock " + name + " Time:" + counter);
			try {
				//Sleep for one tick
				Thread.sleep(incrementTime);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
		
	}
}
