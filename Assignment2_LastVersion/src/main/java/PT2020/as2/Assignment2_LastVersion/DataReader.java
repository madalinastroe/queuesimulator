package PT2020.as2.Assignment2_LastVersion;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.io.BufferedReader;
import java.io.FileReader;

public class DataReader {
	private int nrClients;
	private int nrQueues;
	private int timeSimulation;
	private int minArrival;
	private int maxArrival;
	private int minService;
	private int maxService;
	private ArrayList<Client> clients;
	
	public DataReader()
	{
		
	}
	
	public DataReader(int nrClients, int nrQueues, int timeSimulation, int minArrival, int maxArrival, int minService, int maxService,ArrayList<Client> clients) {
		super();
		this.nrClients = nrClients;
		this.nrQueues = nrQueues;
		this.timeSimulation = timeSimulation;
		this.minArrival = minArrival;
		this.maxArrival = maxArrival;
		this.minService = minService;
		this.maxService = maxService;
		this.clients = clients;
	}

	
	public ArrayList<Client> getClients() {
		return clients;
	}

	public void setClients(ArrayList<Client> clients) {
		this.clients = clients;
	}

	public int getNrClients() {
		return nrClients;
	}

	public void setNrClients(int nrClients) {
		this.nrClients = nrClients;
	}

	public int getNrQueues() {
		return nrQueues;
	}

	public void setNrQueues(int nrQueues) {
		this.nrQueues = nrQueues;
	}

	public int getTimeSimulation() {
		return timeSimulation;
	}


	public void setTimeSimulation(int timeSimulation) {
		this.timeSimulation = timeSimulation;
	}


	public int getMinArrival() {
		return minArrival;
	}

	public void setMinArrival(int minArrival) {
		this.minArrival = minArrival;
	}

	public int getMaxArrival() {
		return maxArrival;
	}

	public void setMaxArrival(int maxArrival) {
		this.maxArrival = maxArrival;
	}

	public int getMinService() {
		return minService;
	}

	public void setMinService(int minService) {
		this.minService = minService;
	}

	public int getMaxService() {
		return maxService;
	}


	public void setMaxService(int maxService) {
		this.maxService = maxService;
	}

	public ArrayList<Client> generateClients(ArrayList<Client> clients)
	{
		int i=0;
		for(i=1;i<=nrClients;i++)
		{
			Random random = new Random();
			AtomicInteger arrivalTime=new AtomicInteger(random.nextInt(this.maxArrival-this.minArrival+1)+this.minArrival);
			AtomicInteger serviceTime=new AtomicInteger(random.nextInt(this.maxService-this.minService+1)+this.minService);
			Client c=new Client(i,arrivalTime,serviceTime);
			clients.add(c);
		}
		
		return clients;
	}
	
	public void readFromFile(String path)
	{

		BufferedReader buff= null;
		try
		{
			buff=new BufferedReader(new FileReader(path));
			String s=" ";
			while((s = buff.readLine())!=null)
			{
				this.nrClients=Integer.parseInt(s);
				this.nrQueues=Integer.parseInt(buff.readLine());
				
				this.timeSimulation=Integer.parseInt(buff.readLine());
				
				s=buff.readLine();
				String x[]=s.split(",");;
				
				//System.out.println(x[0]+" "+x[1]);
				this.minArrival=Integer.parseInt(x[0]);
				this.maxArrival=Integer.parseInt(x[1]);
				
				s=buff.readLine();
				x=s.split(",");
				
				//System.out.println(x[0]+" "+x[1]);
				this.minService=Integer.parseInt(x[0]);
				this.maxService=Integer.parseInt(x[1]);
				
				//System.out.println(nrClients+" "+nrQueues+" "+timeSimulation);
				//System.out.println(minArrival+" "+maxArrival);
				//System.out.println(minService+" "+maxService);
			}
		
			
		}catch(IOException e){
			e.printStackTrace();
			
		}finally {
			try {
				buff.close();
			} catch (IOException e) {
				
				e.printStackTrace();
			} //close file
		}
	}
}
