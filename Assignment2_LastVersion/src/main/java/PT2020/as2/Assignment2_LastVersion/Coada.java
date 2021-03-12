package PT2020.as2.Assignment2_LastVersion;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class Coada implements Runnable {
	private int ID;
	private ArrayBlockingQueue<Client> clients;
	private AtomicInteger queueProcessingTime;
	
	public Coada(int ID, int maxClientsPerServer)
	{
		this.clients = new ArrayBlockingQueue<Client> (maxClientsPerServer);
		this.queueProcessingTime = new AtomicInteger(0);
		this.ID = ID;
	}
	
	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public AtomicInteger getQueueProcessingTime() {
		return queueProcessingTime;
	}

	public void setQueueProcessingTime(AtomicInteger queueProcessingTime) {
		this.queueProcessingTime = queueProcessingTime;
	}

	public void setClients(ArrayBlockingQueue<Client> clients) {
		this.clients = clients;
	}

	public ArrayBlockingQueue<Client> getClients()
	{
		return this.clients;
	}
	
	public void addClient(Client c)
	{
		this.clients.add(c); //put(c)
		this.queueProcessingTime.addAndGet(c.getServiceTime().intValue());
	}
	
	public void run()
	{
		int ok=0;
		int endQueue=0;
		while(true)
		{
			Client c = this.clients.peek();
			if(c!=null)
				ok=1;
				endQueue=0;
			while(ok==1 && endQueue==0)
			{
				try
				{
					Thread.sleep(1000);
				}
				catch (InterruptedException e)
				{
					e.printStackTrace();
				}
				
				this.queueProcessingTime.getAndDecrement();
				c.decreaseTimeService();
				
				if(c.getServiceTime().intValue() == 0)
				{
					
					this.clients.poll();
					ok=0;
					if(this.queueProcessingTime.intValue() == 0)
					{
						return;
					}
					else
						endQueue=1;
				}
			}
		}
	}

	@Override
	public String toString() {
		return "Coada [ID=" + ID + ", clients=" + clients + ", queueProcessingTime=" + queueProcessingTime + "]";
	}
	
	
}
