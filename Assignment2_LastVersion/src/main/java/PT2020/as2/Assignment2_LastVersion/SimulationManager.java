package PT2020.as2.Assignment2_LastVersion;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;


public class SimulationManager implements Runnable {
	
	public int timeSimulation;
	public int nrQueues;
	public int nrClients;
	private Scheduler scheduler;
	private ArrayList<Client> clients; //lista de clienti generati random
	public String input;
	public String output;
	
	
	public SimulationManager( int timeSimulation, int nrQueues, int nrClients, ArrayList<Client> clients, String input) {
		super();
		this.timeSimulation = timeSimulation;
		this.nrQueues = nrQueues;
		this.nrClients = nrClients;
		this.scheduler = new Scheduler(nrQueues, nrClients);
		this.clients = clients;
		this.input = input;
		this.output = "Output\n\n";
		
	}

	public int getTimeSimulation() {
		return timeSimulation;
	}

	public void setTimeSimulation(int timeSimulation) {
		this.timeSimulation = timeSimulation;
	}

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

	public Scheduler getScheduler() {
		return scheduler;
	}

	public void setScheduler(Scheduler scheduler) {
		this.scheduler = scheduler;
	}

	public ArrayList<Client> getClients() {
		return clients;
	}

	public void setClients(ArrayList<Client> clients) {
		this.clients = clients;
	}
	public String getInput() {
		return input;
	}

	public void setInput(String input) {
		this.input = input;
	}

	public String getOutput() {
		return output;
	}

	public void setContents(String contents) {
		this.output = contents;
	}

	private void info(int time)
	{
		this.output+="Time	"+time+"\n";
		this.output+="Clients: ";
		if(this.clients.size()==0)
		{
			this.output+="is closed";
		}
		else
		{
			for(Client c:this.clients)
			{
				this.output+=c.toString();
			}
		}
		this.output+="\n";
		
		for(int i=0;i<scheduler.getQueues().size();i++)
		{
			this.output+="Queue "+i+": ";
			if(scheduler.getQueues().get(i).getClients().size()==0)
			{
				this.output+="is closed";
			}
			else
			{
				for(Client c:scheduler.getQueues().get(i).getClients())
				{
					this.output+=c.toString();
				}
			}
			this.output+="\n";
		}
		
		this.output+="\n";
	}
	
	private void printInFile()
	{
		try 
		{
			FileWriter fileWriter = new FileWriter(this.input);
			PrintWriter printWriter = new PrintWriter(fileWriter);
			
			printWriter.println(this.output);
			System.out.println(this.output);
			printWriter.close();
		} 
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	private boolean openedQueue()
	{
		boolean waitingClients=false;
		boolean isOpened=false;
		
		//verificam daca mai sunt clienti in asteptare
		if(this.clients.size()!=0)
		{
			waitingClients=true;
		}
		
		//pentru fiecare mai avem vreo coada deschisa pt procesare
		for(Coada i:this.scheduler.getQueues())
		{
			if(i.getClients().size()!=0)
			{
				isOpened=true;
			}
		}
		
		boolean result=false;
		if(waitingClients==true || isOpened==true)
		{
			result=true;
		}
		
		return result;
	}

	public void run()
	{
		int currentTime = 0;
		//daca nu s-a terminat simularea si exista cozi in care avem clienti(indiferent daca e coada de clienti generati random sau o coada de procesare)
		while(openedQueue()) //while(true)
		{
			//verificam arrivalTime pentru primul client din coada generata random
			while(currentTime<=timeSimulation && this.clients.size()>0 && this.clients.get(0).getArrivalTime().intValue()==currentTime)
			{
				int i=scheduler.getStrategy().addClient(this.scheduler.getQueues(), this.clients.get(0));
				System.out.println("Indicele cozii in care adaugam clientul: "+i);
				
				this.clients.get(0).increment();
				if(this.scheduler.getQueues().get(i).getQueueProcessingTime().intValue()==0)
				{
					Thread t=new Thread(scheduler.getQueues().get(i)); //thread pe coada in care am adaugat
					this.scheduler.doYourJob(this.clients.get(0));
					t.start();
				}
				
				else if(this.scheduler.getQueues().get(i).getQueueProcessingTime().intValue()>0)
				{
					this.scheduler.doYourJob(this.clients.get(0));
				}
				//scoatem clientul din coada de clienti generati random
				this.clients.remove(this.clients.get(0));
			}
			try
			{
				Thread.sleep(1000);
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
			
			
			info(currentTime);
			currentTime++;
		}
		printInFile();
		System.out.println("End of this Queue Simulator.");
	}
	
	public float averageTime()
	{
		float value=(float) 0;
		int suma=0;
		for(Client q: clients)
		{
			suma=suma+q.getServiceTime().intValue();
		}
		
		value=(float)suma/nrClients;
		return value;
	}
	
	
	
}
