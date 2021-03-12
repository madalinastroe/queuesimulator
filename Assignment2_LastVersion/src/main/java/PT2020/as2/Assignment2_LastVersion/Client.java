package PT2020.as2.Assignment2_LastVersion;

import java.util.concurrent.atomic.AtomicInteger;

public class Client implements Comparable<Client> {
	
	private int ID;
	private AtomicInteger arrivalTime;
	private AtomicInteger serviceTime;
	
	
	public Client(int iD, AtomicInteger arrivalTime, AtomicInteger serviceTime) {
		super();
		ID = iD;
		this.arrivalTime = arrivalTime;
		this.serviceTime=serviceTime;
		//this.serviceTime.getAndIncrement();
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public AtomicInteger getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(AtomicInteger arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public AtomicInteger getServiceTime() {
		return serviceTime;
	}

	public void setServiceTime(AtomicInteger serviceTime) {
		this.serviceTime = serviceTime;
	}

	public void decreaseTimeService()
	{
		this.serviceTime.getAndDecrement();
	}
	
	public void increment()
	{
		this.serviceTime.getAndIncrement();
	}
	
	public String toString()
	{
		return "(" + this.ID+ " , " + this.arrivalTime + " , " + this.serviceTime + ") ";
	}

	public int compareTo(Client c)
	{
		return this.arrivalTime.intValue() - c.arrivalTime.intValue();
	}
	
}
