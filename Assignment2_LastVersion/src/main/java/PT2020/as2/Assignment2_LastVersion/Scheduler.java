package PT2020.as2.Assignment2_LastVersion;
import java.util.ArrayList;

public class Scheduler {
	
	private int nrQueues;
	private int nrClients;
	private ArrayList<Coada> queues= new ArrayList<Coada>();
	private Strategy strategy;
	


	public int getNrQueues() {
		return nrQueues;
	}

	public void setNrQueues(int nrQueues) {
		this.nrQueues = nrQueues;
	}

	public int getNrClients() {
		return nrClients;
	}

	public void setNrClients(int nrClients) {
		this.nrClients = nrClients;
	}

	public void setStrategy(Strategy strategy) {
		this.strategy = strategy;
	}

	public Scheduler(int nrQueues, int nrClients) {
		super();
		this.strategy = new ConcreteStrategyTime();
		this.queues = new ArrayList<Coada>(this.nrClients);
		this.nrQueues = nrQueues;
		this.nrClients = nrClients;
		for(int i=0;i<this.nrQueues;i++)
		{
			Coada c=new Coada(i+1, this.nrClients);
			queues.add(c);
		}
	}

	public ArrayList<Coada> getQueues() {
		return queues;
	}

	public void setQueues(ArrayList<Coada> queues) {
		this.queues = queues;
	}

	public Strategy getStrategy()
	{
		return this.strategy;
	}
	
	public void doYourJob(Client c)
	{
		int index = strategy.addClient(queues, c);
		this.queues.get(index).addClient(c);
	}

	@Override
	public String toString() {
		return "Scheduler [strategy=" + strategy + ", queues=" + queues + ", nrQueues=" + nrQueues + ", nrClients="
				+ nrClients + "]";
	}	
	
}
